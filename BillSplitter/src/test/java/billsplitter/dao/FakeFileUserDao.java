
package billsplitter.dao;

import billsplitter.domain.User;
import java.util.ArrayList;
import java.util.List;

public class FakeFileUserDao extends FileUserDao{
    private final ArrayList<User> users;
    
    public FakeFileUserDao() {
        this.users = new ArrayList<>();
        create(new User("Ville Kalle", "villekalle"));
    }
    
    @Override
    public void create(User user) {
        this.users.add(user);
    }

    @Override
    public List<User> getAll() {
        return this.users;
    }

}
