package Semicolon.Africa.NoteManagement.Dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateNoteRequest {
    private String username;
    private String title;
    private String content;
}