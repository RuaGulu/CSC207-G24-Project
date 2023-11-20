package entity;

import java.time.LocalDateTime;
import java.util.List;

public class CommonGroup implements Group {
  
    private final String name;

    private final List<User> member;

    private final LocalDateTime creationTime;

    CommonGroup(String name, List<User> member, LocalDateTime creationTime){
        this.name = name;
        this.member = member;
        this.creationTime = creationTime;
    }

    public String getName() {return name;}

    public String getMembers(){
        StringBuilder sb = new StringBuilder();
        for (User s : member) {
            sb.append(s.getUsername());
        }
        return sb.toString();
    }

    public LocalDateTime getCreationTime() {return creationTime;}
}
