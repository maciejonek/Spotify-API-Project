package com.projekt.backend.ServiceTests;

import com.projekt.backend.Entity.User;
import com.projekt.backend.Exceptions.UserNotFoundException;
import com.projekt.backend.Repository.UserRepository;
import com.projekt.backend.Service.PlaylistService;
import com.projekt.backend.Service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    private AutoCloseable openMocks;
    private UserService userService;

    @BeforeEach
    public void init(){
        openMocks = MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @AfterEach
    public void tearDown() throws Exception{
        openMocks.close();
    }
    @Test
    public void testGetUserByIdWhenUserExists() {
        User user = new User();
        user.setId(1L);
        user.setDisplayName("test_user");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserService userService = new UserService(userRepository);
        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("test_user", result.getDisplayName());
    }

    @Test
    public void testGetUserByIdWhenUserDoesNotExist() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        UserService userService = new UserService(userRepository);
        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(1L);
        });
    }
}
