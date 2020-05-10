

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

public class LoginUi implements Ui {
    
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
    
    /**
     * Returns the scene for the login screen.
     *
     * @param   window   Stage object
     *
     * @return Scene
     */
    @Override
    public Scene getScene(Stage window) {
        
        GridPane grid = getGrid(window);
        VBox box = new VBox();
        Scene scene = new Scene(box);
        box.getChildren().add(grid);
        
        return scene;
    }

    /**
     * Returns a gridpane for the contents of the login screen.
     *
     * @param   window   Stage object
     *
     * @return GridPane
     */
    private GridPane getGrid(Stage window) {
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        int top = 5, right = 20, bottom = 15, left = 20;
        grid.setPadding(new Insets(top, right, bottom, left));

        Text heading1 = new Text("Existing user login:");
        heading1.setId("heading");
        heading1.setStyle("-fx-font-size:22; -fx-font-weight:bold");
        int col = 0, row = 0, colSpan = 2, rowSpan = 1;
        grid.add(heading1, col, row, colSpan, rowSpan);
        
        row += 2;
        grid.add(new Label("Username:"), col, row);
        row++;
        grid.add(this.username, col, row);
        row++;
        grid.add(getLoginButton(window), col, row);

        row += 4;
        Text heading2 = new Text("Create a new user:");
        heading2.setId("heading");
        heading2.setStyle("-fx-font-size:22; -fx-font-weight:bold");
        grid.add(heading2, col, row, colSpan, rowSpan);
        
        row += 2;
        grid.add(new Label("Your name:"), col, row);
        row++;
        grid.add(this.newName, col, row);
        row++;
        grid.add(new Label("Choose username:"), col, row);
        row++;
        grid.add(this.newUsername, col, row);
        row++;
        grid.add(getCreateButton(window), col, row);
        
        return grid;
    }

    /**
     * Returns a login button.
     *
     * @param   window   Stage object
     *
     * @return Node
     */
    private Node getLoginButton(Stage window) {
        
        Button login = new Button("Login");
        login.setOnAction((ActionEvent event) -> {
            String usernameText = this.username.getText();
            User user = this.loginService.logIn(usernameText);
            if (user instanceof User) {
                this.historyService.setUser(user);
                window.setScene(new HistoryUi(this.historyService, this.loginService).getScene(window));
            } else {
                this.username.clear();
                Alert a = new Alert(AlertType.ERROR);
                a.setTitle("ERROR");
                a.setContentText("User " + usernameText + " does not exist! You can create a new one below.");
                a.show();
            }
        });
        
        return login;
    }

    /**
     * Returns a create new user -button.
     *
     * @param   window   Stage object
     *
     * @return Node
     */
    private Node getCreateButton(Stage window) {
        
        Button create = new Button("Create");
        create.setOnAction((ActionEvent event) -> {
            String response = this.loginService.createUser(this.newName.getText(), this.newUsername.getText());
            String[] message = response.split(";");
            if ("ERROR".equals(message[0])) {
                getAlert(AlertType.ERROR, "ERROR", message[1]).show();
            } else {
                getAlert(AlertType.INFORMATION, "SUCCESS", message[1]).show();
                this.newName.clear();
                this.newUsername.clear();
            }
        });
        
        return create;
    }

    /**
     * Returns alert constructed of the given parameters:
     *
     * @param   alertType   AlertType
     * @param   title   String
     * @param   message   String
     * 
     * @return Node
     */
    private Alert getAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        return alert;
    }
    
}
