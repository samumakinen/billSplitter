
package billsplitter.dao;

import billsplitter.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class FileUserDao implements UserDao {
    String usersFilePath;
    
    public FileUserDao() throws Exception {
        this.usersFilePath = "./src/main/resources/data/users.txt";
        
        Scanner reader = new Scanner(new File("config.txt"));
            
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] parts = line.split(";");
            if (parts[0].equals("usersfilepath")) {
                this.usersFilePath = parts[1];
            }
        }
        
    }

    @Override
    public void create(User user) throws Exception {
        try (FileWriter writer = new FileWriter(usersFilePath, true)) {
            writer.write(user.getUsername() + ";" + user.getName());
            writer.write(System.lineSeparator());
        }
    }

    @Override
    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        Scanner reader = new Scanner(new File(usersFilePath));
        
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] parts = line.split(";");
            users.add(new User(parts[1], parts[0]));
        }
        return users;
    }
}
