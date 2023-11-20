package use_case.create_group;

public class CreateGroupOutputData {
    private final String group_name;
    private boolean useCaseFailed;

    public CreateGroupOutputData(String group_name, boolean useCaseFailed) {
        this.group_name = group_name;
        this.useCaseFailed = useCaseFailed;
    }

    public String getGroup_name() {
        return group_name;
    }
}
