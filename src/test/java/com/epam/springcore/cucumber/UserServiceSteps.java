//package com.epam.springcore.cucumber;
//
//import com.epam.springcore.entity.User;
//import com.epam.springcore.exception.UserNotFoundException;
//import com.epam.springcore.repository.UserRepository;
//import com.epam.springcore.service.impl.UserServiceImpl;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class UserServiceSteps {
//
//    private UserServiceImpl userService;
//    private UserRepository userRepository;
//    private User result;
//    private List<User> resultList;
//    private Throwable exception;
//    private String username;
//
//    @Given("a user with ID {long} exists")
//    public void a_user_with_ID_exists(Long userId) {
//        userRepository = mock(UserRepository.class);
//        userService = new UserServiceImpl(userRepository);
//        User mockUser = mock(User.class);
//        Optional optional = Optional.of(mockUser);
//        when(userRepository.findById(eq(userId))).thenReturn(optional);
//    }
//
//    @When("the user tries to find a user by ID")
//    public void the_user_tries_to_find_a_user_by_ID() {
//        result = userService.getUserById(1L);
//    }
//
//    @Then("the user should get the expected user")
//    public void the_user_should_get_the_expected_user() {
//        assertNotNull(result);
//    }
//
//    @Given("there are multiple users in the system")
//    public void there_are_multiple_users_in_the_system() {
//        userRepository = mock(UserRepository.class);
//        userService = new UserServiceImpl(userRepository);
//
//        List<User> mockUsers = new ArrayList<>();
//        when(userRepository.findAll()).thenReturn(mockUsers);
//    }
//
//    @When("the user tries to find all users")
//    public void the_user_tries_to_find_all_users() {
//        resultList = userService.getAllUsers();
//    }
//
//    @Then("the user should get a list of all users")
//    public void the_user_should_get_a_list_of_all_users() {
//        assertNotNull(resultList);
//    }
//
//    @Given("there is an existing user with username {string}")
//    public void there_is_an_existing_user_with_username(String username) {
//        this.username = username;
//        userRepository = mock(UserRepository.class);
//        userService = new UserServiceImpl(userRepository);
//
//        User existingUser = new User(1L, "John", "Doe", username, "password", true);
//        Optional<User> optional = Optional.of(existingUser);
//        when(userRepository.findUserByUsername(username)).thenReturn(optional);
//    }
//
//    @When("the user tries to find the user by username {string}")
//    public void the_user_tries_to_find_the_user_by_username(String username) {
//        result = userService.findUserByUsername(username);
//    }
//
//    @Then("the user should get the existing user")
//    public void the_user_should_get_the_existing_user() {
//        assertNotNull(result);
//        assertEquals(username, result.getUsername());
//    }
//
//    @Given("there is no user with username {string}")
//    public void there_is_no_user_with_username(String username) {
//        this.username = username;
//        userRepository = mock(UserRepository.class);
//        userService = new UserServiceImpl(userRepository);
//
//        when(userRepository.findUserByUsername(username)).thenReturn(Optional.empty());
//    }
//
//    @When("the user tries to find the user by not existing username {string}")
//    public void the_user_tries_to_find_the_user_by_not_existing_username(String username) {
//        try {
//            result = userService.findUserByUsername(username);
//        } catch (Throwable e) {
//            exception = e;
//        }
//    }
//
//    @Then("the user should get a UserNotFoundException")
//    public void the_user_should_get_a_UserNotFoundException() {
//        assertTrue(exception instanceof UserNotFoundException);
//        assertNull(result);
//    }
//}
