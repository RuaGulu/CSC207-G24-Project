package data_access;

import entity.Group;
import entity.GroupFactory;
import entity.User;
import entity.UserFactory;
import use_case.group.GroupDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GroupDataAccessObject implements GroupDataAccessInterface {
    private final File csvFile;
    private GroupFactory groupFactory;
    private UserFactory userFactory;
    private final Map<String, Group> groups = new HashMap<>();

    private final Map<String, String> members = new HashMap<>();
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    public GroupDataAccessObject(String csvPath, GroupFactory groupFactory, UserFactory userFactory) throws IOException {

        csvFile = new File(csvPath);
        this.groupFactory = groupFactory;
        this.userFactory = userFactory;
        headers.put("groupName", 0);
        headers.put("groupMembers", 1);

        if (csvFile.length() == 0){
            save();
        }else{
            try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))){
                String header = reader.readLine();
                assert header.equals("groupName,groupMembers");
                String row;
                while((row = reader.readLine())!= null){
                    ArrayList<String> result = new ArrayList<>();
                    String [] s = row.split(",",2);
                    members.put(s[0],s[1]);
                    for (String str : row.split(",")){
                        result.add(str);
                    }
                    String name = result.get(0);
                    Group group = groupFactory.create(name,new ArrayList<User>(),LocalDateTime.now());
                    for (int i = 1; i < result.size(); i++) {
                        User user = userFactory.create(result.get(i),null,null,null);
                        group.addMember(user);
                    }
                    groups.put(name,group);
                }
            }

        }

    }

    @Override
    public boolean existsByName(String identifier) {
        return groups.containsKey(identifier);
    }

    @Override
    public ArrayList<Group> getGroups(String userName) {
        ArrayList<Group> result = new ArrayList();
        for (String group : groups.keySet()){
            ArrayList<User> names = groups.get(group).getMembers();
            for (int i = 0; i < names.size(); i++) {
                if (String.valueOf(names.get(i).getUsername()).equals(userName)){
                    result.add(groups.get(group));
                }
            }
        }
        return result;
    }

    @Override
    public void addMember(String groupName, User user){
        groups.get(groupName).addMember(user);
    }

    public void save(String groupName, Group group) {
        if (group == null){
            this.save();
        }else{
            groups.put(groupName,group);
            this.save();
        }
    }

    private void save(){
        BufferedWriter writer;
        try{
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();
            for (String groupName : groups.keySet()) {
                String line = groupName;
                for (int i = 0; i < groups.get(groupName).getMembers().size() ; i++) {
                    User user = (User) groups.get(groupName).getMembers().get(i);
                    line += ","+user.getUsername();
                }
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }
    }
