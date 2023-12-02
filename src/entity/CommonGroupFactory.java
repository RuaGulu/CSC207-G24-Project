package entity;

import java.time.LocalDateTime;

public class CommonGroupFactory implements GroupFactory{
        /**
         * Require: member list is not empty
         * @param name
         * @param member
         * @param creationTime
         * @return
         */
    public Group create( String name, String member, LocalDateTime creationTime){
        return new CommonGroup(name, member, creationTime);
    }
}
