
package billsplitter.domain;

import billsplitter.dao.UserDao;
import billsplitter.domain.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FakeFileUserDao implements UserDao {
    String usersFilePath;

    public FakeFileUserDao() {
        this.usersFilePath = "./src/test/resources/data/users.txt";
    }

    @Override
    public void create(User user) throws Exception {
    }

    @Override
    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        
        try (Scanner reader = new Scanner(new File(this.usersFilePath))) {
            
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
    
}
