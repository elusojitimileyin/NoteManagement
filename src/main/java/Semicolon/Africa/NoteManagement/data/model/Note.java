package Semicolon.Africa.NoteManagement.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document("Notes")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Note {
    @Id
    private String noteId;
    private String username;
    private String title;
    private String content;
    private LocalDateTime dateTimeCreated = LocalDateTime.now();
    private LocalDateTime dateTimeUpdated = LocalDateTime.now();
    private  String sender;
    private  String receiver;
}


