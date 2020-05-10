
package billsplitter.ui;

import billsplitter.domain.Bill;
import billsplitter.domain.HistoryService;
import billsplitter.domain.LoginService;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.ERROR;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewBillUi implements Ui {
    
    private final TextField billTitle;
    private final TextArea billDescription;
    private final TextField billPayers;
    private final TextField billAmount;
    private final Label result;
    private final HistoryService historyService;
    private final LoginService loginService;
    private Bill bill;
    
    public NewBillUi(HistoryService historyService, LoginService loginService) {
        this.billTitle = new TextField();
        this.billDescription = new TextArea();
        this.billPayers = new TextField();
        this.billPayers.textProperty().addListener((obs, oldText, newText) -> {
            updateResultText();
        });
        this.billAmount = new TextField();
        this.billAmount.textProperty().addListener((obs, oldText, newText) -> {
            updateResultText();
        });
        this.result = new Label();
        this.historyService = historyService;
        this.loginService = loginService;
    }

    /**
     * Constructor for modifying a bill.
     *
     * @param   id   Id of the bill to be modified.
     *
     */
    NewBillUi(HistoryService historyService, LoginService loginService, int id) {
        this.billTitle = new TextField();
        this.billDescription = new TextArea();
        this.billPayers = new TextField();
        this.billPayers.textProperty().addListener((obs, oldText, newText) -> {
            updateResultText();
        });
        this.billAmount = new TextField();
        this.billAmount.textProperty().addListener((obs, oldText, newText) -> {
            updateResultText();
        });
        this.result = new Label();
        this.historyService = historyService;
        this.loginService = loginService;
        
        this.bill = historyService.getBill(id);
        
        this.billTitle.setText(this.bill.getTitle());
        this.billDescription.setText(this.bill.getDescription());
        this.billPayers.setText(String.valueOf(this.bill.getPayers()));
        this.billAmount.setText(String.valueOf(this.bill.getAmount()));
    }
    
    /**
     * Returns the scene for the new bill screen.
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
     * Returns a gridpane for the contents of the new bill screen.
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

        Text heading;
        if (this.bill != null) {
            heading = new Text("Modify or delete this bill");
        } else {
            heading = new Text("Create a new bill");
        }
        heading.setId("heading");
        heading.setStyle("-fx-font-size:22; -fx-font-weight:bold");
        int col = 0, row = 0, colSpan = 2, rowSpan = 1;
        grid.add(heading, col, row, colSpan, rowSpan);
        
        row++;
        grid.add(new Label("Title: *"), col, row);
        row++;
        grid.add(this.billTitle, col, row);
        
        row++;
        grid.add(new Label("Description:"), col, row);
        row++;
        grid.add(this.billDescription, col, row);
        this.billDescription.setPrefSize(200, 100);
        this.billDescription.setWrapText(true);
        
        row++;
        grid.add(new Label("Number of payers: *"), col, row);
        row++;
        grid.add(this.billPayers, col, row);
        
        row++;
        grid.add(new Label("Amount: *"), col, row);
        row++;
        grid.add(this.billAmount, col, row);
        
        row++;
        grid.add(this.result, col, row);
        
        HBox hbox;
        if (this.bill != null) {
            hbox = getAltButtons(window);
        } else {
            hbox = getButtons(window);
        }
        row += rowSpan;
        grid.add(hbox, col, row);
        
        return grid;
    }
    
    /**
     * Returns a HBox with the cancel, clear and save -buttons.
     *
     * @param   window   Stage object
     *
     * @return GridPane
     */
    private HBox getButtons(Stage window) {
        
        HBox box = new HBox();
        box.setAlignment(Pos.BASELINE_CENTER);

        Button cancel = new Button("Cancel");
        cancel.setOnAction((ActionEvent event) -> window.setScene(new HistoryUi(this.historyService, this.loginService).getScene(window)));
        box.getChildren().add(cancel);
        int top = 0, right = 5, bottom = 0, left = 5;
        HBox.setMargin(cancel, new Insets(top, right, bottom, left));

        Button clear = new Button("Clear");
        clear.setOnAction((ActionEvent event) -> clearText());
        box.getChildren().add(clear);
        top = 0;
        right = 5;
        bottom = 0;
        left = 0;
        HBox.setMargin(clear, new Insets(top, right, bottom, left));

        Button save = new Button("Save");
        save.setOnAction((ActionEvent event) -> {
            Boolean success = saveBill();
            if (success) {
                window.setScene(new HistoryUi(this.historyService, this.loginService).getScene(window));
            } else {
                Alert alert = new Alert(ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("All fields marked with a * must be filled!");
                alert.show();
            }
        });
        box.getChildren().add(save);
        top = 0;
        right = 5;
        bottom = 0;
        left = 0;
        HBox.setMargin(save, new Insets(top, right, bottom, left));

        return box;
    }
    
    /**
     * This is for getting the alternative buttons for modifying a bill,
     * returns a HBox with the cancel, delete and save -buttons.
     *
     * @param   window   Stage object
     *
     * @return GridPane
     */
    private HBox getAltButtons(Stage window) {
        
        HBox box = new HBox();
        box.setAlignment(Pos.BASELINE_CENTER);

        Button cancel = new Button("Cancel");
        cancel.setOnAction((ActionEvent event) -> window.setScene(new HistoryUi(this.historyService, this.loginService).getScene(window)));
        box.getChildren().add(cancel);
        int top = 0, right = 5, bottom = 0, left = 5;
        HBox.setMargin(cancel, new Insets(top, right, bottom, left));

        Button delete = new Button("Delete");
        delete.setOnAction((ActionEvent event) -> {
            this.historyService.deleteBill(this.bill.getId());
            window.setScene(new HistoryUi(this.historyService, this.loginService).getScene(window));
        });
        box.getChildren().add(delete);
        top = 0;
        right = 5;
        bottom = 0;
        left = 0;
        HBox.setMargin(delete, new Insets(top, right, bottom, left));

        Button save = new Button("Save");
        save.setOnAction((ActionEvent event) -> {
            Boolean success = saveBill();
            if (success) {
                this.historyService.deleteBill(this.bill.getId());
                window.setScene(new HistoryUi(this.historyService, this.loginService).getScene(window));
            } else {
                Alert alert = new Alert(ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("All fields marked with a * must be filled!");
                alert.show();
            }
        });
        box.getChildren().add(save);
        top = 0;
        right = 5;
        bottom = 0;
        left = 0;
        HBox.setMargin(save, new Insets(top, right, bottom, left));

        return box;
    }

    /**
     * Clears the text fields.
     *
     */
    private void clearText() {
        this.billTitle.clear();
        this.billDescription.clear();
        this.billPayers.clear();
        this.billAmount.clear();
    }

    /**
     * Tries to save a bill to the history, returns true if successful.
     *
     * @return boolean
     */
    private boolean saveBill() {
        
        String title = "";
        title = this.billTitle.getText();
        
        String description = "";
        description = this.billDescription.getText();
        
        String resultText = getResult();
        
        if (title.isBlank() || resultText == null) {
            return false;
        } else {
            int payers = Integer.valueOf(this.billPayers.getText());
            double amount = Double.valueOf(this.billAmount.getText());
            double result = Double.valueOf(resultText);
            this.historyService.createBill(title, description, payers, amount, result);
            return true;
        }
    }

    /**
     * Updates the result field.
     *
     */
    private void updateResultText() {
        String text = "Amount per person: ";
        String result = getResult();
        if (result != null) {
            this.result.setText(text + result);
        } else {
            this.result.setText(text);
        }
    }
    
    /**
     * Returns the contents of the result field. 
     *
     * @return String
     */
    private String getResult() {
        String result = null;
        
        if (this.billPayers.getText().isBlank() || this.billAmount.getText().isBlank()) {
            return null;
        } else {
            int payers = -1;
            double amount = -1;
            
            String payersS = this.billPayers.getText().trim();
            String amountS = this.billAmount.getText().trim();
            
            if (payersS.matches("\\d+") && amountS.matches("-?\\d+(\\.\\d+)?")) {
                payers = Integer.valueOf(payersS);
                amount = Double.valueOf(amountS);
            }
            if (payers >= 0 && amount >= 0) {
                double splitted = amount / (1.0 * payers);

                DecimalFormatSymbols sym = new DecimalFormatSymbols();
                sym.setDecimalSeparator('.');
                DecimalFormat df = new DecimalFormat("0.00", sym);
                
                result = df.format(splitted);
            } else {
                return null;
            }
        }        
        return result;
    }
}