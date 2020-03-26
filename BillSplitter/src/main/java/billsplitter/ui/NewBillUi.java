
package billsplitter.ui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author samumakinen
 */
public class NewBillUi implements Gui {
    
    private final TextField billTitle = new TextField();
    private final TextArea billDescription = new TextArea("Optional...");
    private final TextField billPayers = new TextField();
    private final TextField billAmount = new TextField();
    
    /**
     *
     * @param window
     * @throws Exception
     */
    @Override
    public void buildAndShowGui(Stage window) throws Exception {
        
        GridPane grid = buildGrid();
        VBox box = new VBox();
        Scene scene = new Scene(box);
        box.getChildren().add(grid);
        window.setScene(scene);
        window.setTitle("Bill Splitter");
        window.show();
        
    }
    
    private GridPane buildGrid() {
        
        // Creating the GridPane
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        int top = 5, right = 20, bottom = 15, left = 20;
        grid.setPadding(new Insets(top, right, bottom, left));

        // Heading
        Text heading = new Text("Create a new bill");
        heading.setId("heading");
        heading.setStyle("-fx-font-size:22; -fx-font-weight:bold");
        int col = 0, row = 0, colSpan = 2, rowSpan = 1;
        grid.add(heading, col, row, colSpan, rowSpan);
        
        // Title
        row++;
        grid.add(new Label("Title: *"), col, row);
        row++;
        grid.add(this.billTitle, col, row);
        
        // Description
        row++;
        grid.add(new Label("Description:"), col, row);
        row++;
        grid.add(this.billDescription, col, row);
        this.billDescription.setPrefSize(200, 100);
        this.billDescription.setWrapText(true);
        
        // Number of payers
        row++;
        grid.add(new Label("Number of payers: *"), col, row);
        row++;
        grid.add(this.billPayers, col, row);
        
        // Amount
        row++;
        grid.add(new Label("Amount: *"), col, row);
        row++;
        grid.add(this.billAmount, col, row);
        
        // Result
        
        // Buttons
        HBox hbox = buildButtons();
	row += rowSpan;
	grid.add(hbox, col, row);
        
        return grid;
    }
    
    private HBox buildButtons() {
        
        // Create HBox
        HBox box = new HBox();
        box.setAlignment(Pos.BASELINE_CENTER);

        // Cancel
        Button cancel = new Button("Cancel");
        // cancel.setOnAction(event -> clearText());
        box.getChildren().add(cancel);
        int top = 0, right = 5, bottom = 0, left = 5;
        HBox.setMargin(cancel, new Insets(top, right, bottom, left));

        // Clear
        Button clear = new Button("Clear");
        clear.setOnAction((ActionEvent event) -> clearText());
        box.getChildren().add(clear);
        top = 0; right = 5; bottom = 0; left = 0;
        HBox.setMargin(clear, new Insets(top, right, bottom, left));

        // Save
        Button save = new Button("Save");
        save.setOnAction((ActionEvent event) -> saveBill());
        box.getChildren().add(save);
        top = 0; right = 5; bottom = 0; left = 0;
        HBox.setMargin(save, new Insets(top, right, bottom, left));

        return box;
    }

    private void clearText() {
        this.billTitle.clear();
        this.billDescription.clear();
        this.billPayers.clear();
        this.billAmount.clear();
    }

    private void saveBill() {
        
    }
    
}
