package Semicolon.Africa.NoteManagement.Dtos.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateNoteResponse {
    private String title;
    private String content;
    private LocalDateTime dateCreated;
}
