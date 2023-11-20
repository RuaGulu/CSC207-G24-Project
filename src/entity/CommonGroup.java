package entity;

import java.time.LocalDateTime;

public class CommonGroup implements Group{
  
    private final String name;

    private final String[] member;

    private final LocalDateTime creationTime;

    CommonGroup(String name, String[] member, LocalDateTime creationTime){
        this.name = name;
        this.member = member;
        this.creationTime = creationTime;
    }

    public String getName() {return name;}

    public String getMembers(){
        StringBuilder sb = new StringBuilder();
        for (String s : member) {
            sb.append(s);
        }
        return sb.toString();
    }

    public LocalDateTime getCreationTime() {return creationTime};
}
