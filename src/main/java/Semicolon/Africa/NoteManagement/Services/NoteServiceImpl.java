package Semicolon.Africa.NoteManagement.Services;

import Semicolon.Africa.NoteManagement.Dtos.request.*;
import Semicolon.Africa.NoteManagement.Dtos.response.CreateNoteResponse;
import Semicolon.Africa.NoteManagement.Dtos.response.DeleteNoteResponse;
import Semicolon.Africa.NoteManagement.Dtos.response.EditNoteResponse;
import Semicolon.Africa.NoteManagement.Dtos.response.ShareNoteResponse;
import Semicolon.Africa.NoteManagement.Utils.NoteManagementException;
import Semicolon.Africa.NoteManagement.data.model.Note;
import Semicolon.Africa.NoteManagement.data.model.User;
import Semicolon.Africa.NoteManagement.data.repository.NoteRepository;
import Semicolon.Africa.NoteManagement.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


import static Semicolon.Africa.NoteManagement.Utils.Mapper.*;
import static Semicolon.Africa.NoteManagement.Utils.Validators.*;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public CreateNoteResponse writeNote(CreateNoteRequest createNoteRequest) {
        createNoteValidation(createNoteRequest);
        Note newNote = map(createNoteRequest);
        Note savedNote = noteRepository.save(newNote);
        validatedUserForNoteCreation(createNoteRequest, savedNote);
        return mapCreateNoteResponse(savedNote);
    }
    @Override
    public EditNoteResponse editNote(EditNoteRequest editNoteRequest) {
        Note note = findNoteByTitle(editNoteRequest.getTitle());
        if (note == null) throw new NoteManagementException("Note not found for user with: " + editNoteRequest.getTitle());
        note.setContent(editNoteRequest.getContent());
        Note updatedNote = noteRepository.save(note);
        return mapEditNoteResponse(updatedNote);

    }
    @Override
    public ShareNoteResponse shareNote(ShareNoteRequest shareNoteRequest) {
        shareNoteValidation(shareNoteRequest);
        Note note = findNoteByTitle(shareNoteRequest.getTitle());
        note.setReceiver(shareNoteRequest.getReceiver());
        noteRepository.save(note);
        assignNoteToUserConfirmation(shareNoteRequest, note);
        return getShareNoteResponse(note);
    }


    private void validatedUserForNoteCreation(CreateNoteRequest createNoteRequest, Note savedNote) {
        User user = findUserByUsername(createNoteRequest.getUsername());
        List<Note> userNotes = user.getNotes();
        userNotes.add(savedNote);
        user.setNotes(userNotes);
        userRepository.save(user);
    }

    private void assignNoteToUserConfirmation(ShareNoteRequest shareNoteRequest, Note note) { 
        User user = findUserByUsername(shareNoteRequest.getReceiver());
        List<Note> notes= user.getNotes();
        for (Note n : notes) {
            if (n.getTitle().equals(note.getTitle())) throw new NoteManagementException("Note already exist");}
        notes.add(note);
        user.setNotes(notes);
        userRepository.save(user);

    }

    @Override
    public Note findNoteByTitle(String title) {
        Note note = noteRepository.findByTitle(title);
        if (note == null) {
            throw new NoteManagementException("Note not found for user with: " +  title);
        }
        return note;
    }

    @Override
    public Note viewNoteByTitle(ViewNoteByTitleRequest viewNoteByTitleRequest) {
        Note note = noteRepository.findByTitle(viewNoteByTitleRequest.getTitle());
        if (note == null)
            throw new NoteManagementException
                    ("Note not found for user with:" + viewNoteByTitleRequest.getTitle());
        return note;
    }


    private User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new NoteManagementException("Username not found");
        }
        return user;
    }

    @Override
    public DeleteNoteResponse deleteNote(DeleteNoteRequest deleteNoteRequest) {
        Note note = findNoteByTitle(deleteNoteRequest.getTitle());
        if (note == null) {
            throw new NoteManagementException("Note not found for user with: " + deleteNoteRequest.getTitle());
        }
        noteRepository.delete(note);
        return new DeleteNoteResponse();
    }
    @Override
    public List<Note> viewAllNote() {
        return noteRepository.findAll();
    }

}




