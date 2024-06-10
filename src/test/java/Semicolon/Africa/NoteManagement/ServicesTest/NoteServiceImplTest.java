package Semicolon.Africa.NoteManagement.ServicesTest;

import Semicolon.Africa.NoteManagement.Dtos.request.*;
import Semicolon.Africa.NoteManagement.Services.NoteService;
import Semicolon.Africa.NoteManagement.Services.UserService;
import Semicolon.Africa.NoteManagement.Utils.NoteManagementException;
import Semicolon.Africa.NoteManagement.data.repository.NoteRepository;
import Semicolon.Africa.NoteManagement.data.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NoteServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    NoteService noteService;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        noteRepository.deleteAll();

        RegisterUserRequest userRegisterRequest = new RegisterUserRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("@Twi1234");
        userService.registerUser(userRegisterRequest);

    }

    @Test
    public void registerOneUser_userIsOneNotLogin_CreateANote() {

        assertEquals(1, userRepository.count());
        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setUsername("username");
        createNoteRequest.setTitle("Get Rich");
        createNoteRequest.setContent("that is why i am here");

        assertThrows(NoteManagementException.class, () -> noteService.writeNote(createNoteRequest));
    }


    @Test
    public void registerOneUser_userIsLogin_CreateANote() {

        assertEquals(1, userRepository.count());

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("username");
        loginUserRequest.setPassword("@Twi1234");
        userService.login(loginUserRequest);


        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setUsername("username");
        createNoteRequest.setTitle("Get Rich");
        createNoteRequest.setContent("that is why i am here");

       noteService.writeNote(createNoteRequest);

        assertEquals(1, noteRepository.count());
    }


    @Test
    public void registerMoreThanOneUser_MoreUserIsLogin_MoreCreateANote() {

        RegisterUserRequest userRegisterRequest1 = new RegisterUserRequest();
        userRegisterRequest1.setUsername("username1");
        userRegisterRequest1.setPassword("@Twi1234");
        userService.registerUser(userRegisterRequest1);
        assertEquals(2, userRepository.count());

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("username");
        loginUserRequest.setPassword("@Twi1234");
        userService.login(loginUserRequest);

        LoginUserRequest loginUserRequest1 = new LoginUserRequest();
        loginUserRequest1.setUsername("username1");
        loginUserRequest1.setPassword("@Twi1234");
        userService.login(loginUserRequest1);

        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setUsername("username");
        createNoteRequest.setTitle("Get Rich 00");
        createNoteRequest.setContent("that is why i am here");
        noteService.writeNote(createNoteRequest);

        CreateNoteRequest createNoteRequest1 = new CreateNoteRequest();
        createNoteRequest1.setUsername("username1");
        createNoteRequest1.setTitle("Get Rich in tech 001");
        createNoteRequest1.setContent("that is why i am here to get rich");
        noteService.writeNote(createNoteRequest1);
        assertEquals(2, noteRepository.count());
    }

    @Test
    void registerOneUser_userIsOneLogin_CreateANote_deleteNote() {
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("username");
        loginUserRequest.setPassword("@Twi1234");
        userService.login(loginUserRequest);

        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setUsername("username");
        createNoteRequest.setTitle(" Note");
        createNoteRequest.setContent(" content");
        noteService.writeNote(createNoteRequest);
        assertEquals(1, noteRepository.count());

        DeleteNoteRequest deleteNoteRequest = new DeleteNoteRequest();
        deleteNoteRequest.setTitle(" Note");
        noteService.deleteNote(deleteNoteRequest);
        assertEquals(0, noteRepository.count());

    }

        @Test
    public void registerMoreThanOneUser_MoreUserIsLogin_MoreCreateANote_DeleteNote() {

        RegisterUserRequest userRegisterRequest1 = new RegisterUserRequest();
        userRegisterRequest1.setUsername("username1");
        userRegisterRequest1.setPassword("@Twi1234");
        userService.registerUser(userRegisterRequest1);
        assertEquals(2, userRepository.count());

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("username");
        loginUserRequest.setPassword("@Twi1234");
        userService.login(loginUserRequest);

        LoginUserRequest loginUserRequest1 = new LoginUserRequest();
        loginUserRequest1.setUsername("username1");
        loginUserRequest1.setPassword("@Twi1234");
        userService.login(loginUserRequest1);

        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setUsername("username");
        createNoteRequest.setTitle("Be Rich Soon");
        createNoteRequest.setContent("that is why i am here");
        noteService.writeNote(createNoteRequest);

        CreateNoteRequest createNoteRequest1 = new CreateNoteRequest();
        createNoteRequest1.setUsername("username1");
        createNoteRequest1.setTitle("Get Rich in tech 001");
        createNoteRequest1.setContent("that is why i am here to get rich");
        noteService.writeNote(createNoteRequest1);

        DeleteNoteRequest deleteNoteRequest = new DeleteNoteRequest();
        deleteNoteRequest.setTitle(createNoteRequest1.getTitle());
        noteService.deleteNote(deleteNoteRequest);

    assertEquals(1, noteRepository.count());
   }
    @Test
    public void registerOneUser_UserIsLogin_CreateANote_EditNote() {
    assertEquals(1, userRepository.count());

    LoginUserRequest loginUserRequest = new LoginUserRequest();
    loginUserRequest.setUsername("username");
    loginUserRequest.setPassword("@Twi1234");
    userService.login(loginUserRequest);


    CreateNoteRequest createNoteRequest = new CreateNoteRequest();
    createNoteRequest.setUsername("username");
    createNoteRequest.setTitle("Get Rich 001");
    createNoteRequest.setContent("that is why i am here");
    noteService.writeNote(createNoteRequest);

    EditNoteRequest editNoteRequest = new EditNoteRequest();
    editNoteRequest.setTitle("Get Rich 001");
    editNoteRequest.setContent("that is why i am here to get rich, else i will be out there.");
    noteService.editNote(editNoteRequest);

    assertEquals(1, noteRepository.count());
}

    @Test
    public void registerMoreThanOneUser_MoreUserIsLogin_MoreCreateANote_ShareNote() {

        RegisterUserRequest userRegisterRequest1 = new RegisterUserRequest();
        userRegisterRequest1.setUsername("username1");
        userRegisterRequest1.setPassword("@Twi1234");
        userService.registerUser(userRegisterRequest1);
        assertEquals(2, userRepository.count());

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("username");
        loginUserRequest.setPassword("@Twi1234");
        userService.login(loginUserRequest);

        LoginUserRequest loginUserRequest1 = new LoginUserRequest();
        loginUserRequest1.setUsername("username1");
        loginUserRequest1.setPassword("@Twi1234");
        userService.login(loginUserRequest1);

        CreateNoteRequest createNoteRequest = new CreateNoteRequest();
        createNoteRequest.setUsername("username");
        createNoteRequest.setTitle("Be Rich Soon");
        createNoteRequest.setContent("that is why i am here");
        noteService.writeNote(createNoteRequest);

        CreateNoteRequest createNoteRequest1 = new CreateNoteRequest();
        createNoteRequest1.setUsername("username1");
        createNoteRequest1.setTitle("Get Rich in tech 001");
        createNoteRequest1.setContent("that is why i am here to get rich");
        noteService.writeNote(createNoteRequest1);

        ShareNoteRequest shareNoteRequest = new ShareNoteRequest();
        shareNoteRequest.setSender("username");
        shareNoteRequest.setTitle(createNoteRequest.getTitle());
        shareNoteRequest.setReceiver("username1");
        noteService.shareNote(shareNoteRequest);

        assertEquals(2, noteRepository.count());
    }



}