package use_case.search_usergroup;

import entity.Group;

import java.util.List;

public class SearchGroupOutputData {
    private final String username;
    private final List<Group> group;
    private boolean useCaseFailed;

    public SearchGroupOutputData(String username, List<Group> group, boolean useCaseFailed) {
        this.username = username;
        this.group = group;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getgroup() {
        return "The user " + username + " is currently in group " + group.toString();
    }
}
