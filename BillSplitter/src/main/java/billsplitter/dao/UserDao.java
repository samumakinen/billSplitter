
package billsplitter.dao;

import billsplitter.domain.User;
import java.util.List;

public interface UserDao {
    
    void create(User user) throws Exception;
    
    List<User> getAll();
    
}
