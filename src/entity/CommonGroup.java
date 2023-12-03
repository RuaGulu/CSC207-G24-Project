package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommonGroup implements Group {

    private final String name;

    private final List<User> members = new ArrayList<>();

    private final LocalDateTime creationTime;

    CommonGroup(String name, User member, LocalDateTime creationTime){
        this.name = name;
        this.members.add(member);
        this.creationTime = creationTime;
    }


    public String getName() {return name;}

    public List getMembers(){
        return members;
    }

    public void addMember(User user){members.add(user);};

//    public List getMembers(){
//        StringBuilder sb = new StringBuilder();
//        for (User s : member) {
//            sb.append(s.getUsername());
//        }
//        return sb.toString();
//    }

    public LocalDateTime getCreationTime() {return creationTime;}
}