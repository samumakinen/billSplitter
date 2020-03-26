
package billsplitter.ui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginUi {
    private final TextField username = new TextField();
    private final TextField newName = new TextField();
    private final TextField newUsername = new TextField();
    private final HistoryUi historyUi = new HistoryUi();
    
    public void buildAndShowGui(Stage window) throws Exception {
        GridPane grid = buildGrid(window);
        VBox box = new VBox();
        Scene scene = new Scene(box);
        box.getChildren().add(grid);
        window.setScene(scene);
        window.setTitle("Bill Splitter");
        window.show();
    }

    private GridPane buildGrid(Stage window) {
        
        // Creating the GridPane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        int top = 5, right = 20, bottom = 15, left = 20;
        grid.setPadding(new Insets(top, right, bottom, left));

        // Heading 1
        Text heading1 = new Text("Existing user login:");
        heading1.setId("heading");
        heading1.setStyle("-fx-font-size:22; -fx-font-weight:bold");
        int col = 0, row = 0, colSpan = 2, rowSpan = 1;
        grid.add(heading1, col, row, colSpan, rowSpan);
        
        // Login
        row += 2;
        grid.add(new Label("Username:"), col, row);
        row++;
        grid.add(this.username, col, row);
        row++;
        Button login = new Button("Login");
        login.setOnAction((ActionEvent event) -> window.setScene(historyUi.buildGui(window)));
        grid.add(login, col, row);

        // Heading 2
        row += 4;
        Text heading2 = new Text("Create a new user:");
        heading2.setId("heading");
        heading2.setStyle("-fx-font-size:22; -fx-font-weight:bold");
        grid.add(heading2, col, row, colSpan, rowSpan);
        
        // Create a new user
        row += 2;
        grid.add(new Label("Your name:"), col, row);
        row++;
        grid.add(this.newName, col, row);
        row++;
        grid.add(new Label("Choose username:"), col, row);
        row++;
        grid.add(this.newUsername, col, row);
        row++;
        Button createNewUser = new Button("Create new user");
        grid.add(createNewUser, col, row);
        
        return grid;
    }
    
}
