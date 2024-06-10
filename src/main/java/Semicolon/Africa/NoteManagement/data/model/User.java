package Semicolon.Africa.NoteManagement.data.model;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Document("Users")
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class User {
    @Id
    private String userId;
    private String username;
    private String password;
    private boolean isLoggedIn;
    @DBRef
    private List<Note> notes = new ArrayList<>();
    private LocalDateTime dateRegistered = LocalDateTime.now();
    private LocalDateTime dateUpdated = LocalDateTime.now();


}

