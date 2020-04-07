

package billsplitter.ui;

import billsplitter.domain.HistoryService;
import billsplitter.domain.LoginService;
import billsplitter.domain.User;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginUi {
    
    private final HistoryService historyService;
    private final LoginService loginService;
    private final TextField newName;
    private final TextField newUsername;
    private final TextField username;
    
    public LoginUi(HistoryService historyService, LoginService loginService) {
        
        this.historyService = historyService;
        this.loginService = loginService;
        this.newName = new TextField();
        this.newUsername = new TextField();
        this.username = new TextField();
        
    }
    
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

        // Heading (login)
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
        grid.add(buildLoginButton(window), col, row);

        // Heading (create new user)
        row += 4;
        Text heading2 = new Text("Create a new user:");
        heading2.setId("heading");
        heading2.setStyle("-fx-font-size:22; -fx-font-weight:bold");
        grid.add(heading2, col, row, colSpan, rowSpan);
        
        // Create new user
        row += 2;
        grid.add(new Label("Your name:"), col, row);
        row++;
        grid.add(this.newName, col, row);
        row++;
        grid.add(new Label("Choose username:"), col, row);
        row++;
        grid.add(this.newUsername, col, row);
        row++;
        grid.add(buildCreateButton(window), col, row);
        
        return grid;
    }

    private Node buildLoginButton(Stage window) {
        
        Button login = new Button("Login");
        login.setOnAction((ActionEvent event) -> {
            String usernameText = this.username.getText();
            User user = this.loginService.logIn(usernameText);
            if (user instanceof User) {
                this.historyService.setUser(user);
                window.setScene(new HistoryUi(this.historyService, this.loginService).buildGui(window));
            }
            else {
                this.username.clear();
                Alert a = new Alert(AlertType.ERROR);
                a.setTitle("ERROR");
                a.setContentText("User " + usernameText + " does not exist! You can create a new one below.");
                a.show();
            }
        });
        
        return login;
    }

    private Node buildCreateButton(Stage window) {
        
        Button create = new Button("Create");
        create.setOnAction((ActionEvent event) -> {
            String response = this.loginService.createUser(this.newName.getText(), this.newUsername.getText());
            String[] message = response.split(";");
            if ("ERROR".equals(message[0])) {
                System.out.println(message[1]);
                Alert a = new Alert(AlertType.ERROR);
                a.setTitle("ERROR");
                a.setContentText(message[1]);
                a.show();
            } else {
                System.out.println(message[1]);
                Alert a = new Alert(AlertType.INFORMATION);
                a.setTitle("SUCCESS");
                a.setContentText(message[1]);
                a.show();
                this.newName.clear();
                this.newUsername.clear();
            }
        });
        
        return create;
    }
}
