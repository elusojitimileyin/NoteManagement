package Semicolon.Africa.NoteManagement.Services;
import Semicolon.Africa.NoteManagement.Dtos.request.LoginUserRequest;
import Semicolon.Africa.NoteManagement.Dtos.request.LogoutUserRequest;
import Semicolon.Africa.NoteManagement.Dtos.request.RegisterUserRequest;
import Semicolon.Africa.NoteManagement.Dtos.response.LoginUserResponse;
import Semicolon.Africa.NoteManagement.Dtos.response.LogoutUserResponse;
import Semicolon.Africa.NoteManagement.Dtos.response.RegisterUserResponse;
import Semicolon.Africa.NoteManagement.Utils.NoteManagementException;
import Semicolon.Africa.NoteManagement.data.model.User;
import Semicolon.Africa.NoteManagement.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

import static Semicolon.Africa.NoteManagement.Utils.Encryptor.*;
import static Semicolon.Africa.NoteManagement.Utils.Mapper.map;
import static Semicolon.Africa.NoteManagement.Utils.Validators.*;


@Service
    public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepositories;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        registeredUserValidation(registerUserRequest);
        User myUser = map(registerUserRequest);
        userRepositories.save(myUser);

        return map(myUser);
    }


    @Override
    public LoginUserResponse login(LoginUserRequest loginRequest) {
        User user = userRepositories.findByUsername(loginRequest.getUsername().toLowerCase());
        if (user == null || !checkPassword(loginRequest.getPassword(), user.getPassword()))
            throw new NoteManagementException("Invalid username or password");
        user.setLoggedIn(true);
        userRepositories.save(user);
        return new LoginUserResponse(user.getUsername().toLowerCase());
    }

    @Override
    public LogoutUserResponse logout(LogoutUserRequest logoutUserRequest) {
        User user = userRepositories.findByUsername(logoutUserRequest.getUsername().toLowerCase());
        if (user == null) throw new NoteManagementException("User not found");
        user.setLoggedIn(false);
        userRepositories.save(user);
        return new LogoutUserResponse(user.getUsername());
    }

    @Override
    public List<User> getAllUser() {
        return userRepositories.findAll();
    }
}
