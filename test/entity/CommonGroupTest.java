package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;


class CommonGroupTest {
    private CommonGroup group;
    private CommonUser TestUser1;
    private CommonUser TestUser2;
    private LocalDateTime now;


    @BeforeEach
    void init() {
        TestUser1 = new CommonUser(
                "TestUser1", "Toronto", "Calgary", "group1");

        TestUser2 = new CommonUser(
                "TestUser2", "Vancouver", "Saskatoon", "anothergroup");

        ArrayList<User> members = new ArrayList<>();

        members.add(TestUser1);
        members.add(TestUser2);

        now = LocalDateTime.now();

        group = new CommonGroup(
                "TestGroup", members, now);
    }

    @Test
    void getName() {
        assertEquals("TestGroup", group.getName());
    }

    @Test
    void getMembers() {
        assert(group.getMembers().contains(TestUser1));
    }

    @Test
    void getCreationTime() {
        assertEquals(now, group.getCreationTime());
    }
}