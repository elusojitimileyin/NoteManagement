package Semicolon.Africa.NoteManagement.Dtos.request;



import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginUserRequest {
    private String username;
    private String password;
}

