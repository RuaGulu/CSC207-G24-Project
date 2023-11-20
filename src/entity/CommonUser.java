package entity;

public class CommonUser implements User{
    private final String username;
    private final String location;
    private final String hometown;
    private final Group group;

    public CommonUser(String username, String location, String hometown, Group group) {
        this.username = username;
        this.location = location;
        this.hometown = hometown;
        this.group = group;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getLocation(){
        return location;

    }

    @Override
    public String change_location() {
        return null;
    }
}
