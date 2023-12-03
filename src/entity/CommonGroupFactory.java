package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonGroupFactory implements GroupFactory{
    /**
     * Require: member list is not empty
     * @param name
     * @param members
     * @param creationTime
     * @return
     */
    public Group create(String name, ArrayList<User> members, LocalDateTime creationTime){
        return new CommonGroup(name, members, creationTime);
    }
}

