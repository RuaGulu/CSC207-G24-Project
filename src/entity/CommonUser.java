package entity;

import java.util.ArrayList;
import java.util.List;

public class CommonUser implements User{
    private final String username;
    private final String location;
    private final String hometown;
    private final List<Group> group = new ArrayList<>();

    public CommonUser(String username, String location, String hometown, Group group) {
        this.username = username;
        this.location = location;
        this.hometown = hometown;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getLocation(){
        return location;

    }
    public void addgroup(Group group){
        this.group.add(group);
    }

    @Override
    public String change_location() {
        return null;
    }
}
