package interface_adapter.search_usergroup;


import entity.Group;

import java.util.List;

public class SearchGroupState {
    private String username = "";
    private String usernameError = null;
    private List<Group> usergroup = null;

    public SearchGroupState(SearchGroupState copy){
        username = copy.username;
        usernameError = copy.usernameError;
    }
    public SearchGroupState() {}

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public List<Group> getGroup() {return usergroup;}
    public void setUsergroup(List<Group> usergroup) {
        this.usergroup = usergroup;
    }
}
