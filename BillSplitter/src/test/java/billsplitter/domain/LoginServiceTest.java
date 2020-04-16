
package billsplitter.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class LoginServiceTest {
    LoginService loginService;
    
    @Before
    public void setup() {
        this.loginService = new LoginService(new FakeFileUserDao());
    }
    
    @Test
    public void loginMethodReturnsCorrectlyWhenUserExists() {
        assertEquals(new User("Testi Testinen", "testaaja"), this.loginService.logIn("testaaja"));
    }
    
    @Test
    public void loginMethodReturnsCorrectlyWhenUserDoesNotExist() {
        assertEquals(null, this.loginService.logIn("thisUserDoesNotExist"));
    }
    
    @Test
    public void createMethodBothFieldsEmpty() {
        assertEquals("ERROR;Both name and username need to be at least 3 characters long!", this.loginService.createUser("", ""));
    }
    
    @Test
    public void createMethodBothFieldsTooShort() {
        assertEquals("ERROR;Both name and username need to be at least 3 characters long!", this.loginService.createUser("Xy", "ab"));
    }
    
    @Test
    public void createMethodSpotsIllegalCharsInUsername() {
        assertEquals("ERROR;Name or username contains invalid characters!", this.loginService.createUser("Abb", "test;user"));
    }
    
    @Test
    public void createMethodSpotsIllegalCharsInName() {
        assertEquals("ERROR;Name or username contains invalid characters!", this.loginService.createUser("Ab;bb", "testest"));
    }
    
    @Test
    public void createMethodSpotsUsernameInUse() {
        assertEquals("ERROR;Username already in use!", this.loginService.createUser("Test Tester", "testaaja"));
    }
    
    @Test
    public void createMethodCreatesNewUser() {
        assertEquals("SUCCESS;User Test User has been created with username tester", this.loginService.createUser("Test User", "tester"));
    }
    
}
