
package billsplitter.domain;

import billsplitter.dao.FileUserDao;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class LoginServiceTest {
    FileUserDao fileUserDao;
    LoginService loginService;
    
    @Before
    public void setUp() {
        this.fileUserDao = new FileUserDao();
        this.loginService = new LoginService(this.fileUserDao);
    }
    
    @Test
    public void logInWorks() {
        
    }
}
