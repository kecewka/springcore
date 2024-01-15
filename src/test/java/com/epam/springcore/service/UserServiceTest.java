package com.epam.springcore.service;

import com.epam.springcore.entity.User;
import com.epam.springcore.entity.TrainingType;
import com.epam.springcore.entity.User;
import com.epam.springcore.repository.UserRepository;
import com.epam.springcore.service.Impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllUsersTest() {
        List<User> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);
        System.out.println(users.size());

        List<User> allUsers = userService.getAllUsers();

        assertEquals(users, allUsers);
    }

    @Test
    public void getUserByIdTest() {
        Long userId = 1L;
        User user = new User(1L, "asd", "asd", "asd.asd", "asd", true);
        when(userRepository.findById(userId)).thenReturn(user);

        User foundUser = userService.getUserById(userId);

        assertEquals(user, foundUser);
    }

    @Test
    public void getUserByUsernameTest() {
        String username = "asd.asd";
        User user = new User(1L, "asd", "asd", "asd.asd", "asd", true);
        when(userRepository.getUserByUsername(username)).thenReturn(user);

        User foundUser = userService.getUserByUsername(username);

        assertEquals(user, foundUser);
    }

    @Test
    public void createUserTest() {
        User user = new User(1L, "asd", "asd", "asd.asd", "asd", true);
        userService.createUser(user);
        verify(userRepository, times(1)).saveUser(user);

    }

    @Test
    public void updateUserTest() {
        User user = new User(1L, "asd", "asd", "asd.asd", "asd", true);
        userService.updateUser(user);
        verify(userRepository, times(1)).updateUser(user);
    }

    @Test
    public void deleteUserTest() {
        Long userId = 1L;
        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteUser(userId);
    }

    @Test
    public void usernameExistsTest() {
        String username = "asd.asd";
        when(userRepository.usernameExists(username)).thenReturn(true);
        boolean exists = userService.usernameExists(username);
        verify(userRepository, times(1)).usernameExists(username);
    }

    @Test
    public void generatePasswordTest() {
        String generated = userService.generatePassword();
        assertNotNull("Generated password should not be null", generated);
        assertEquals("Generated password should have a length of 10", 10, generated.length());
        assertTrue("Generated password should only contain alphanumeric characters", generated.matches("[a-zA-Z0-9]+"));
    }
}
