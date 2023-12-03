package use_case.group;

import entity.Weather;

import java.util.HashMap;

public class GroupOutputData {
    private final HashMap<String, HashMap<String,Weather>> data;
    private boolean useCaseFailed;

    public GroupOutputData(HashMap<String, HashMap<String, Weather>> data) {
        this.data = data;
    }

    public HashMap<String, HashMap<String,Weather>> getData(){return data;}
}