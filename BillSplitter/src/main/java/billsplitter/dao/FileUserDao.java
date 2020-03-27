
package billsplitter.dao;

import billsplitter.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUserDao implements UserDao {
    private final String usersFilePath;
    
    public FileUserDao() {
        this.usersFilePath = initConfig();
    }
    
    @Override
    public void create(User user) {
        try {
            FileWriter writer = new FileWriter(usersFilePath, true);
            writer.write(user.getUsername() + ";" + user.getName());
            writer.write(System.lineSeparator());
            writer.close();
        } catch (IOException ex) {
            System.out.println("FileUserDao.create(" + user + ")");
            System.out.println("Virhe: " + ex.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(usersFilePath))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(";");
                users.add(new User(parts[1], parts[0]));
            }
        } catch (Exception e) {
            System.out.println("FileUserDao.getAll()");
            System.out.println("Virhe: " + e.getMessage());
        }
        return users;
    }



    private String initConfig() {
        String path = "./src/main/resources/data/users.txt";
        
        try (Scanner reader = new Scanner(new File("config.txt"))) {

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] string = line.split(";");
            if (string[0].equals("usersfilepath")) {
                path = string[1];
            }
        }
    } catch (Exception e) {
        System.out.println("FileUserDao.initConfig()");
        System.out.println("Virhe: " + e.getMessage());
    }
        return path;
    }
    
}
