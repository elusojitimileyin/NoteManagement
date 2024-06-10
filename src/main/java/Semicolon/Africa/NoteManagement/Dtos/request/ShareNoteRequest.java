package Semicolon.Africa.NoteManagement.Dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShareNoteRequest {
    private String sender;
    private String title;
    private String receiver;


}
