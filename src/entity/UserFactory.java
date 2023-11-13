package entity;

public interface UserFactory {
    User create(String name, String location, String hometown, Group group);
}
