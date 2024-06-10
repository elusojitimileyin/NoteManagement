package Semicolon.Africa.NoteManagement.Services;

import Semicolon.Africa.NoteManagement.Dtos.request.LoginUserRequest;
import Semicolon.Africa.NoteManagement.Dtos.request.LogoutUserRequest;
import Semicolon.Africa.NoteManagement.Dtos.request.RegisterUserRequest;
import Semicolon.Africa.NoteManagement.Dtos.response.LoginUserResponse;
import Semicolon.Africa.NoteManagement.Dtos.response.LogoutUserResponse;
import Semicolon.Africa.NoteManagement.Dtos.response.RegisterUserResponse;
import Semicolon.Africa.NoteManagement.data.model.User;

import java.util.List;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest signUpRequest);

    LoginUserResponse login(LoginUserRequest loginUserRequest);

    LogoutUserResponse logout(LogoutUserRequest logoutUserRequest);
    List<User> getAllUser();


}
