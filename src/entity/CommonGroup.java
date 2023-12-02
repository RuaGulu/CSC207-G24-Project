package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonGroup implements Group {

    private final String name;

    private ArrayList<User> members = new ArrayList<>();

    private final LocalDateTime creationTime;

    CommonGroup(String name, ArrayList<User> members, LocalDateTime creationTime){
        this.name = name;
        this.members = members;
        this.creationTime = creationTime;
    }


    public String getName() {return name;}

    public ArrayList getMembers(){
        return members;
    }

    public ArrayList getLocations(){
        ArrayList locations = new ArrayList();
        for (int i = 0; i < members.size(); i++) {
            locations.add(members.get(i).getLocation());
        }
        return locations;
    }

    public void addMember(User user){members.add(user);};

    public boolean containUser(String userName){
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getUsername() == userName){
                return true;
            }
        }
        return false;
    }

//    public List getMembers(){
//        StringBuilder sb = new StringBuilder();
//        for (User s : member) {
//            sb.append(s.getUsername());
//        }
//        return sb.toString();
//    }

    public LocalDateTime getCreationTime() {return creationTime;}
}

