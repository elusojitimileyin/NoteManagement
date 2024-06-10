package Semicolon.Africa.NoteManagement.controller;

import Semicolon.Africa.NoteManagement.Dtos.request.*;
import Semicolon.Africa.NoteManagement.Dtos.response.*;
import Semicolon.Africa.NoteManagement.Utils.NoteManagementException;
import Semicolon.Africa.NoteManagement.Services.NoteService;
import Semicolon.Africa.NoteManagement.data.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/Note")
public class NoteController {
    @Autowired
    private NoteService noteService;


    @PostMapping("/write_note")
    public ResponseEntity<?> writeNote(@RequestBody CreateNoteRequest createNoteRequest) {
        try {
            CreateNoteResponse result = noteService.writeNote(createNoteRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (NoteManagementException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("/edit_note")
    public ResponseEntity<?> editNote(@RequestBody EditNoteRequest editNoteRequest) {
        try {
            EditNoteResponse result = noteService.editNote(editNoteRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (NoteManagementException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete_note")
    public ResponseEntity<?> deleteNote(@RequestBody DeleteNoteRequest deleteNoteRequest) {
        try {
            DeleteNoteResponse result = noteService.deleteNote(deleteNoteRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (NoteManagementException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }
    @GetMapping("/get_notes")
    public ResponseEntity<?>viewAllNote() {
        try {
            List<Note> result = noteService.viewAllNote();
            return new ResponseEntity<>(new ApiResponse(true,result ), CREATED);
        } catch (NoteManagementException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }
    @PatchMapping("/share_note")
    public ResponseEntity<?> shareNote(@RequestBody ShareNoteRequest shareNoteRequest) {
        try {
            ShareNoteResponse result = noteService.shareNote(shareNoteRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (NoteManagementException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("view_note_by_title")
    public ResponseEntity<?> viewNoteByTitle(@RequestBody ViewNoteByTitleRequest viewNoteByTitleRequest) {
        try {
          List<Note> result = Collections.singletonList(noteService.viewNoteByTitle(viewNoteByTitleRequest));
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (NoteManagementException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }

}