
package billsplitter;

import billsplitter.ui.NewBillUi;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
    
    public static void main(String[] args) {
        launch();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        new NewBillUi().buildAndShowGui(stage);
    }
    
}
