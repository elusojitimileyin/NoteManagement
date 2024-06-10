package Semicolon.Africa.NoteManagement.Dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShareNoteResponse {
    private String title;
    private String content;
    private String sender;
    private String receiver;

}
