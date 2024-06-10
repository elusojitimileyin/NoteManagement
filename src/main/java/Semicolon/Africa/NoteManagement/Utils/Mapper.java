package Semicolon.Africa.NoteManagement.Utils;

import Semicolon.Africa.NoteManagement.Dtos.request.*;
import Semicolon.Africa.NoteManagement.Dtos.response.*;
import Semicolon.Africa.NoteManagement.data.model.Note;
import Semicolon.Africa.NoteManagement.data.model.User;
import java.time.LocalDateTime;
import static Semicolon.Africa.NoteManagement.Utils.Encryptor.encryptPassword;

public class Mapper {
    public static User map( RegisterUserRequest registerUserRequest ) {

        User user = new User();
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(encryptPassword(registerUserRequest.getPassword()));
        return user;
    }
    public static RegisterUserResponse map(User user){
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setUsername(user.getUsername());
        registerUserResponse.setDateTimeRegistered(LocalDateTime.now());
        return registerUserResponse;
    }


    public static Note map(CreateNoteRequest createNoteRequest) {
        Note newNote = new Note();
        newNote.setTitle(createNoteRequest.getTitle());
        newNote.setContent(createNoteRequest.getContent());
        newNote.setUsername(createNoteRequest.getUsername());
        return newNote;
    }

    public static CreateNoteResponse mapCreateNoteResponse(Note savedNote) {
        CreateNoteResponse response = new CreateNoteResponse();
        response.setTitle(savedNote.getTitle());
        response.setContent(savedNote.getContent());
        response.setDateCreated(savedNote.getDateTimeCreated());
        return response;
    }

    public static EditNoteResponse mapEditNoteResponse(Note updatedNote) {
        EditNoteResponse response = new EditNoteResponse();
        response.setTitle(updatedNote.getTitle());
        response.setContent(updatedNote.getContent());
        response.setDateUpdated(LocalDateTime.now());
        return response;
    }
    public static ShareNoteResponse getShareNoteResponse(Note note) {
        ShareNoteResponse response = new ShareNoteResponse();
        response.setTitle(note.getTitle());
        response.setContent(note.getContent());
        response.setReceiver(note.getReceiver());
        response.setSender(note.getSender());
        return response;

    }


}
