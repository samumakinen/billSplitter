
package billsplitter;

import billsplitter.dao.FileUserDao;
import billsplitter.domain.LoginService;
import billsplitter.ui.LoginUi;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
    private LoginService loginService;
    private FileUserDao fileUserDao;
    private LoginUi loginUi;
    
    public static void main(String[] args) {
        launch();
    }
    
    @Override
   public void init() {
       this.fileUserDao = new FileUserDao(); 
       this.loginService = new LoginService(fileUserDao);
       this.loginUi = new LoginUi(this.loginService);
   }
    
    @Override
    public void start(Stage window) throws Exception {
        window.setScene(this.loginUi.buildGui(window));
        window.setTitle("Bill Splitter");
        window.show();
    }
    
    @Override
    public void stop() {
      System.out.println("sovellus sulkeutuu");
    }
    
}
