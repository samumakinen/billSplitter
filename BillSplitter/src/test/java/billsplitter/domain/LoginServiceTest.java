
package billsplitter.domain;

import billsplitter.dao.FakeFileUserDao;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class LoginServiceTest {
    LoginService loginService;
    
    @Before
    public void setUp() {
        this.loginService = new LoginService(new FakeFileUserDao());
    }
    
    @Test
    public void userExistsReturnsTrueWhenUserExists() {
        assertTrue(this.loginService.userExists("villekalle"));
    }
    @Test
    public void userExistsReturnsFalseWhenUserDoesNotExists() {
        assertFalse(this.loginService.userExists("kettukarkki"));
    }
}
