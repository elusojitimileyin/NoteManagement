package Semicolon.Africa.NoteManagement.Dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ApiResponse {

    private boolean isSuccessful;
    private Object Response;
}
