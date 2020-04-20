
package billsplitter;

import billsplitter.dao.FileBillDao;
import billsplitter.dao.FileUserDao;
import billsplitter.domain.HistoryService;
import billsplitter.domain.LoginService;
import billsplitter.ui.LoginUi;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
    private FileBillDao fileBillDao;
    private FileUserDao fileUserDao;
    private HistoryService historyService;
    private LoginService loginService;
    private LoginUi loginUi;
    
    public static void main(String[] args) {
        launch();
    }
    
    @Override
    public void init() throws Exception {
        this.fileBillDao = new FileBillDao();
        this.fileUserDao = new FileUserDao(); 
        this.historyService = new HistoryService(this.fileBillDao);
        this.loginService = new LoginService(this.fileUserDao);
        this.loginUi = new LoginUi(this.historyService, this.loginService);
    }
    
    @Override
    public void start(Stage window) {
        window.setScene(this.loginUi.getScene(window));
        window.setTitle("Bill Splitter");
        window.show();
    }
    
    @Override
    public void stop() {
        System.out.println("Application closing");
    }
    
}
