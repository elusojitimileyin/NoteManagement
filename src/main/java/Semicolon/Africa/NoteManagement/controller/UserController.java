package Semicolon.Africa.NoteManagement.controller;

import Semicolon.Africa.NoteManagement.Dtos.request.LoginUserRequest;
import Semicolon.Africa.NoteManagement.Dtos.request.LogoutUserRequest;
import Semicolon.Africa.NoteManagement.Dtos.request.RegisterUserRequest;
import Semicolon.Africa.NoteManagement.Dtos.response.ApiResponse;
import Semicolon.Africa.NoteManagement.Utils.NoteManagementException;

import Semicolon.Africa.NoteManagement.Services.UserService;
import Semicolon.Africa.NoteManagement.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/NoteManagement")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest signUpRequest) {
        try {
            var result = userService.registerUser(signUpRequest);
            return new ResponseEntity<>(new ApiResponse(true,result),CREATED);
        } catch (NoteManagementException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest loginUserRequest) {
        try {
            var result = userService.login(loginUserRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }
        catch (NoteManagementException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutUserRequest logoutUserRequest) {
        try {
            var result = userService.logout(logoutUserRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        }
        catch (NoteManagementException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }
    @GetMapping("/get_all_users")
    public ResponseEntity<?>getAllUser() {
        try {
            List<User> result = userService.getAllUser();
            return new ResponseEntity<>(new ApiResponse(true,result ), CREATED);
        } catch (NoteManagementException message) {
            return new ResponseEntity<>(new ApiResponse(false, message.getMessage()), BAD_REQUEST);
        }
    }

}

