package entity;

public class CommonUserFactory implements UserFactory{
    /**
     * @param name
     * @param location
     * @param hometown
     * @param group
     * @return
     */
    @Override
    public User create(String name, String location, String hometown, Group group) {
        return new CommonUser(name,location,hometown, group);
    }
}
