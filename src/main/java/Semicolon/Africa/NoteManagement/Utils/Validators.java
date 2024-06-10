package Semicolon.Africa.NoteManagement.Utils;

import Semicolon.Africa.NoteManagement.Dtos.request.CreateNoteRequest;
import Semicolon.Africa.NoteManagement.Dtos.request.RegisterUserRequest;
import Semicolon.Africa.NoteManagement.Dtos.request.ShareNoteRequest;
import Semicolon.Africa.NoteManagement.data.model.User;
import Semicolon.Africa.NoteManagement.data.repository.NoteRepository;
import Semicolon.Africa.NoteManagement.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validators {

    private  static  UserRepository userRepositories;
    private  static  NoteRepository noteRepositories;

    @Autowired
    public Validators(UserRepository userRepository, NoteRepository noteRepository) {
        userRepositories = userRepository;
        noteRepositories = noteRepository;
    }

    public static boolean isEmptyOrNullString(String str) {
        return str == null || str.isEmpty() || str.isBlank();
    }

    public static boolean isUserRegistered(String username) {
        return userRepositories.existsByUsername(username);
    }


    public static boolean isUserLoggedIn(String username) {
        String lowerCaseUsername = username.toLowerCase();
        User user = userRepositories.findByUsername(lowerCaseUsername);
        return user.isLoggedIn();
    }

    public static  void registeredUserValidation(RegisterUserRequest registerUserRequest) {
        if (isUserRegistered(registerUserRequest.getUsername())) {
            throw new NoteManagementException("User Already Registered");
        }
        if (isEmptyOrNullString(registerUserRequest.getUsername())) {
            throw new NoteManagementException("User request cannot be empty, null, or blank");
        }
        String username = registerUserRequest.getUsername().toLowerCase();
        registerUserRequest.setUsername(username);

    }
    public static void shareNoteValidation(ShareNoteRequest shareNoteRequest) {
        if(!isUserRegistered(shareNoteRequest.getSender())){
            throw new NoteManagementException("User not registered");
        }
        if(!isUserLoggedIn(shareNoteRequest.getSender())){
            throw new NoteManagementException("User not logged in");
        }
    }

    public static void createNoteValidation(CreateNoteRequest createNoteRequest) {
        if(!isUserRegistered(createNoteRequest.getUsername())){
            throw new NoteManagementException("User not registered");
        }

        if(!isUserLoggedIn(createNoteRequest.getUsername())){
            throw new NoteManagementException("User not logged in");
        }
        if (isEmptyOrNullString(createNoteRequest.getUsername())) {
            throw new NoteManagementException("task request cannot be empty, null, or blank");
        }


        if (noteRepositories.existsByTitleAndUsername(createNoteRequest.getTitle(), createNoteRequest.getUsername())){
            throw new NoteManagementException(" Title already exists");
        }
    }

}
