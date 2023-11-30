package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonUserTest {

    private CommonUser user;
    private Group group;

    @BeforeEach
    void init() {
        user = new CommonUser(
                "TestUser", "Toronto", "Calgary", group);
    }

    @Test
    void getUsername() {
        assertEquals("TestUser", user.getUsername());
    }

    @Test
    void getLocation() {
        assertEquals("Toronto", user.getLocation());
    }

    @Test
    void change_location() {
        assertNull(user.change_location());
    }
}