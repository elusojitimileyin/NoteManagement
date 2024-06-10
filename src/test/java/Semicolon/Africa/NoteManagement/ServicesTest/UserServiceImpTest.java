 package Semicolon.Africa.NoteManagement.ServicesTest;

import Semicolon.Africa.NoteManagement.Dtos.request.LoginUserRequest;
import Semicolon.Africa.NoteManagement.Dtos.request.LogoutUserRequest;
import Semicolon.Africa.NoteManagement.Dtos.request.RegisterUserRequest;
import Semicolon.Africa.NoteManagement.Dtos.response.LoginUserResponse;
import Semicolon.Africa.NoteManagement.Dtos.response.LogoutUserResponse;
import Semicolon.Africa.NoteManagement.Utils.NoteManagementException;
import Semicolon.Africa.NoteManagement.Services.UserService;
import Semicolon.Africa.NoteManagement.data.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImpTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();

        RegisterUserRequest userRegisterRequest = new RegisterUserRequest();
        userRegisterRequest.setUsername("username");
        userRegisterRequest.setPassword("@Twi1234");

        userService.registerUser(userRegisterRequest);
    }

    @Test
    public void testThatUserCanRegisterOnce() {

        assertEquals(1, userRepository.count());
    }

    @Test
    public void testThatUserCannotRegisterWithEmptyString () {
        RegisterUserRequest userRegisterRequest = new RegisterUserRequest();
        userRegisterRequest.setUsername("");
        userRegisterRequest.setPassword("");

        assertThrows(NoteManagementException.class, () -> userService.registerUser(userRegisterRequest));


    }
    @Test
    void testThatUserCanLoginAfterRegistration() {

        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("@Twi1234");
        LoginUserResponse loginResponse = userService.login(loginRequest);

        assertNotNull(loginResponse);
        assertThat(loginResponse.getUsername(), is("username"));
    }



    @Test
    void testThatUserCanRegister_login_logout() {
        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("@Twi1234");
        userService.login(loginRequest);

        LogoutUserRequest logoutRequest = new LogoutUserRequest();
        logoutRequest.setUsername("username");
        LogoutUserResponse logoutResponse = userService.logout(logoutRequest);

        assertThat(logoutResponse.getUsername(), is("username"));
    }

    @Test
    void testThatICanNotLoginIfIDontRegister() {
        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("fela");
        loginRequest.setPassword("onigbo");

        assertThrows(NoteManagementException.class, () -> userService.login(loginRequest));

    }

    @Test
    void testThatICantLogoutIfIDontLogin() {
        LogoutUserRequest logoutRequest = new LogoutUserRequest();
        logoutRequest.setUsername("fela");

        assertThrows(NoteManagementException.class, () -> userService.logout(logoutRequest));
    }

    @Test
    void testThatUserCannotLoginWithWrongPasswordAfterRegistration() {
        LoginUserRequest loginRequest = new LoginUserRequest();
        loginRequest.setUsername("username");
        loginRequest.setPassword("wrongPassword");

        assertThrows(NoteManagementException.class, () -> userService.login(loginRequest),
                "Invalid password for user username");
    }



}
