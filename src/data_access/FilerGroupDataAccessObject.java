package data_access;

import entity.Group;
import entity.GroupFactory;
import entity.User;
import entity.UserFactory;
import use_case.create_group.GroupDataAccessInterface;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileGroupDataAccessObject implements GroupDataAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Group> groups = new HashMap<>();
    private GroupFactory groupFactory;

    public FileGroupDataAccessObject(String csvPath, GroupFactory groupFactory) throws IOException {
        this.groupFactory = groupFactory;
        csvFile = new File(csvPath);
        headers.put("groupname", 0);
        headers.put("group", 1);

        if (csvFile.length() == 0){
            save();
        }else{
            try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))){
                String header = reader.readLine();
                assert header.equals("groupname,group");
                String row;
                while((row = reader.readLine())!= null){
                    String[] col = row.split(",");
                    String groupname = String.valueOf(col[headers.get("groupname")]);
                    // Please check if users represent users list for the group.
                    String users = String.valueOf(col[headers.get("users")]);
                    Group group = groupFactory.create(groupname, users);
                    groups.put(groupname, group);
                }

            }

        }

    }

    @Override
    public boolean existsByName(String identifier) {
        return groups.containsKey(identifier);
    }

    @Override
    public void save(Group group) {
        groups.put(group.getName(), group);
        this.save();
    }

    @Override
    public Group get(String groupname) {
        return groups.get(groupname);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Group group : groups.values()) {
                String line = String.format("%s,%s",
                        group.getName(),group.getMembers());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException("Failed to save group to CSV.", e);
        }
    }
}