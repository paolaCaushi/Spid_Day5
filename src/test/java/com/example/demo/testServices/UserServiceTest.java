package com.example.demo.testServices;
import com.example.demo.Day5Application;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {Day5Application.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

    @Mock
    private UserService userService;

    @BeforeAll
    public void setup() {
        User user = new User();
        user.setId(1);

        when(userService.getUserById(1)).thenReturn(Optional.of(user));
        when(userService.getUserById(anyInt())).thenThrow(new RuntimeException("Exception"));
    }


    @Test
    public void testDatabaseRetrievalForUsers() {
        assertInstanceOf(User.class, userService.getUserById(1).get());
        assertThrows(Exception.class, () -> userService.getUserById(2).get());
    }

}