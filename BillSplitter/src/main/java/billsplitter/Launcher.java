
package billsplitter;

import billsplitter.ui.LoginUi;
import billsplitter.ui.NewBillUi;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
    
    public static void main(String[] args) {
        launch();
    }
    
    @Override
    public void start(Stage window) throws Exception {
        new LoginUi().buildAndShowGui(window);
    }
    
    @Override
    public void stop() {
      System.out.println("sovellus sulkeutuu");
    }
    
}
