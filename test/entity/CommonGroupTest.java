package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;


class CommonGroupTest {
    private CommonGroup group;
    private CommonUser TestUser1;
    private CommonUser TestUser2;
    private LocalDateTime now;


    @BeforeEach
    void init() {
        TestUser1 = new CommonUser(
                "TestUser1", "Toronto", "Calgary", group);

        TestUser2 = new CommonUser(
                "TestUser2", "Vancouver", "Saskatoon", group);

        now = LocalDateTime.now();

        group = new CommonGroup(
                "TestGroup", TestUser1, now);
    }

    @Test
    void getName() {
        assertEquals("TestUser1", group.getName());
    }

    @Test
    void getMembers() {
        assertEquals("TestUser1", group.getMembers());
    }

    @Test
    void getCreationTime() {
        assertEquals(now, group.getCreationTime());
    }
}