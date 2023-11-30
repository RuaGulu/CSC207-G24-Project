package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommonGroup implements Group {
  
    private final String name;

    private final List<String> member = new ArrayList<>();

    private final LocalDateTime creationTime;

    CommonGroup(String name, String member, LocalDateTime creationTime){
        this.name = name;
        this.member.add(member);
        this.creationTime = creationTime;
    }

    public void addmember(String member){
       this.member.add(member);
    }

    public String getName() {return name;}

    public String getMembers(){
        return member.toString();
    }

    public LocalDateTime getCreationTime() {return creationTime;}
}
