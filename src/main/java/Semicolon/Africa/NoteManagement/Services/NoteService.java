package Semicolon.Africa.NoteManagement.Services;


import Semicolon.Africa.NoteManagement.Dtos.request.*;
import Semicolon.Africa.NoteManagement.Dtos.response.CreateNoteResponse;
import Semicolon.Africa.NoteManagement.Dtos.response.DeleteNoteResponse;
import Semicolon.Africa.NoteManagement.Dtos.response.EditNoteResponse;
import Semicolon.Africa.NoteManagement.Dtos.response.ShareNoteResponse;
import Semicolon.Africa.NoteManagement.data.model.Note;
import Semicolon.Africa.NoteManagement.data.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
    public interface NoteService {
        CreateNoteResponse writeNote (CreateNoteRequest createNoteRequest) ;

        EditNoteResponse editNote(EditNoteRequest editNoteRequest);
        DeleteNoteResponse deleteNote(DeleteNoteRequest deleteNoteRequest);

         ShareNoteResponse shareNote(ShareNoteRequest shareNoteRequest);

         List<Note> viewAllNote();

         Note findNoteByTitle(String title);

         Note viewNoteByTitle(ViewNoteByTitleRequest viewTaskByTitleRequest);


}
