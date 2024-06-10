package Semicolon.Africa.NoteManagement.Dtos.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserRequest {
    private String username;
    private String password;
}
