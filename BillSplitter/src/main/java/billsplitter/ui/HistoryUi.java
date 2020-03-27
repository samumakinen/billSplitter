
package billsplitter.ui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HistoryUi {
    
    public Scene buildGui(Stage window) {
        
        GridPane grid = buildGrid(window);
        VBox box = new VBox();
        Scene scene = new Scene(box);
        box.getChildren().add(grid);
        
        return scene;
    }

    private GridPane buildGrid(Stage window) {
        
        // Creating the GridPane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        int top = 5, right = 20, bottom = 15, left = 20;
        grid.setPadding(new Insets(top, right, bottom, left));
        
        Label t1 = new Label("Testi 1");
        int col = 0, row = 0, colSpan = 2, rowSpan = 1;
        grid.add(t1, col, row);
        
        row++;
        Button t2 = new Button("Tee uusi");
        t2.setOnAction((ActionEvent event) -> window.setScene(new NewBillUi().buildGui(window)));
        grid.add(t2, col, row);
        
        return grid;
    }
}
