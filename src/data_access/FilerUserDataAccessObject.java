package data_access;

import entity.Group;
import entity.GroupFactory;
import entity.User;
import entity.UserFactory;
import use_case.logged_in.LoggedInUserDataAccessinterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FilerUserDataAccessObject implements LoginUserDataAccessInterface, SignupUserDataAccessInterface, LoggedInUserDataAccessinterface{

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, User> accounts = new HashMap<>();
    private UserFactory userFactory;

    private GroupFactory groupFactory;

    private final Map<String, Group>  groups = new HashMap<>();

    public FilerUserDataAccessObject(String csvPath, UserFactory userFactory, GroupFactory groupFactory) throws IOException{
        this.userFactory = userFactory;
        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("location", 1);

        if (csvFile.length() == 0){
            save();
        }else{
            try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))){
                String header = reader.readLine();
                assert header.equals("username,location");
                String row;
                while((row = reader.readLine())!= null){
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String location = String.valueOf(col[headers.get("location")]);
                    User user = userFactory.create(username, location,null,null);
                    accounts.put(username,user);
                }

            }

        }

    }

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public boolean existsByGroup(String identifier) {
        return groups.containsKey(identifier);
    }


    @Override
    public void save(User user) {
        accounts.put(user.getUsername(),user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    public Group getGroup(String group){return groups.get(group);}


    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line;
                if (user.getGroupName() != null){
                    line = String.format("%s,%s",
                            user.getUsername(),user.getLocation());
                }else{
                    line = String.format("%s,%s",
                            user.getUsername(),user.getLocation());
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

