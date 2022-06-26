package com.example.demo.testServices;

import com.example.demo.SpidApplication;
import com.example.demo.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {SpidApplication.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

    @Mock
    private UserService userService;

    @BeforeAll
    public void setup() {
        User user = new User();
        user.setId(1);

        //we test getting the user with id=1
        //In case sth goes wrong we throw an exception
        when(userService.getUserById(1)).thenReturn(Optional.of(user));
        when(userService.getUserById(anyInt())).thenThrow(new RuntimeException("Exception"));
    }

    //testing getting users from the database
    @Test
    public void testDatabaseRetrievalForUsers() {
        assertInstanceOf(User.class, userService.getUserById(1).get());
        assertThrows(Exception.class, () -> userService.getUserById(2).get());
    }

}