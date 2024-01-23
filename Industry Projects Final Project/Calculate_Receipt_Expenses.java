/*
Author: Nicole Hackler
Date: 1/23/2024

Industry Projects final project: Calculate Receipt Expenses
This project can take entered double values in different categories and calculate a total for each one and a
grand total of all the receipt values entered along with a button to delete the last entered receipt.

This project has a user interface requiring input into different text fields. I've set a default value of "0.00"
to all the text fields to avoid the receipt object not being able to be created if the user doesn't enter
anything in one of the text fields. If the user needs to delete the last entered receipt it will display in
the text area as a visual indication that the receipt was deleted.
*Only the last entered receipt is deleted, unless they keep pushing the button, then it will keep deleting
the next receipt in line til empty. :/ *
*/

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Calculate_Receipt_Expenses extends Application {
    private TextField tfHousing = new TextField("0.00");
    private TextField tfUtilities = new TextField("0.00");
    private TextField tfTransportGas = new TextField("0.00");
    private TextField tfGroceries = new TextField("0.00");
    private TextField tfInternetCellphone = new TextField("0.00");
    private TextField tfEntertainment = new TextField("0.00");
    private TextField tfBirthHoliday = new TextField("0.00");
    private TextField tfHealthCare = new TextField("0.00");
    private TextArea taDisplay = new TextArea("0.00");

    @Override //Override the start method in the Application class
    public void start(Stage primaryStage) {
        //Properties for Text Area field
        taDisplay.setWrapText(true);
        taDisplay.setEditable(false);
        taDisplay.setPrefColumnCount(20);
        taDisplay.setPrefRowCount(7);

        //Menu bar with options
        MenuBar menuBar = new MenuBar();
        Menu menuOperations = new Menu("Operations");
        Menu menuExit = new Menu("Exit");
        menuBar.getMenus().addAll(menuOperations, menuExit);

        //menuItems for Operations
        MenuItem menuItemEnterReceipt = new MenuItem("Enter Receipt");
        MenuItem menuItemDelete = new MenuItem("Delete last Receipt");
        MenuItem menuItemCalculateReceipt = new MenuItem("Calculate Receipts");
        menuOperations.getItems().addAll(menuItemEnterReceipt, menuItemDelete, menuItemCalculateReceipt);

        //menuItems for Exit
        MenuItem menuItemClose = new MenuItem("Close");
        menuExit.getItems().add(menuItemClose);

        //Grid Pane to hold text fields and labels
        GridPane tfPane = new GridPane();
        tfPane.setAlignment(Pos.CENTER);
        tfPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        tfPane.setHgap(5.5);
        tfPane.setVgap(5.5);

        //Placing text fields and labels on the Grid Pane
        tfPane.add(new Label("Housing"), 0, 0);
        tfPane.add(tfHousing, 1, 0);
        tfPane.add(new Label("Utilities"), 0, 1);
        tfPane.add(tfUtilities, 1, 1);
        tfPane.add(new Label("Transportation/Gas"), 0, 2);
        tfPane.add(tfTransportGas, 1, 2);
        tfPane.add(new Label("Groceries/House/Cleaning"), 0, 3);
        tfPane.add(tfGroceries, 1, 3);
        tfPane.add(new Label("Internet/Cellphone"), 0, 4);
        tfPane.add(tfInternetCellphone, 1, 4);
        tfPane.add(new Label("Entertainment"), 0, 5);
        tfPane.add(tfEntertainment, 1, 5);
        tfPane.add(new Label("Birthdays/Holidays"), 0, 6);
        tfPane.add(tfBirthHoliday, 1, 6);
        tfPane.add(new Label("Healthcare"), 0, 7);
        tfPane.add(tfHealthCare, 1, 7);
        Button btEnterReceipt = new Button("Enter Receipt"); //Button for entering receipts ***
        Button btDelete = new Button("Delete last Receipt"); //Button for deleting last receipt ***
        tfPane.add(btEnterReceipt, 1, 8);
        tfPane.add(btDelete, 0, 8);
        GridPane.setHalignment(btEnterReceipt, HPos.RIGHT);
        GridPane.setHalignment(btDelete, HPos.LEFT);

        //Hbox to hold the "Calculate Receipt" button
        HBox hBox = new HBox();
        Button btTotal = new Button("Calculate Receipts"); //Button for the grand total of all Receipts ***
        hBox.getChildren().add(btTotal);
        hBox.setAlignment(Pos.CENTER);

        //Boarder Pane to hold menu bar, Grid Pane and Hbox
        BorderPane pane = new BorderPane();
        pane.setTop(menuBar);
        pane.setCenter(tfPane);
        pane.setRight(taDisplay);
        pane.setBottom(hBox);

        Scene scene = new Scene(pane,700, 400); //Creating a scene
        primaryStage.setTitle("Calculate Receipt Expenses"); //set window title
        primaryStage.setScene(scene); //Place scene in the stage
        primaryStage.show(); //Display the stage

        //Arraylist to hold Receipts
        List<Receipt> listR = new ArrayList<>();

        //Handle menu actions "Enter Receipt
        menuItemEnterReceipt.setOnAction(e -> {
            Receipt receipt = new Receipt(Double.parseDouble(tfHousing.getText().trim()),
                    Double.parseDouble(tfUtilities.getText().trim()),
                    Double.parseDouble(tfTransportGas.getText().trim()),
                    Double.parseDouble(tfGroceries.getText().trim()),
                    Double.parseDouble(tfInternetCellphone.getText().trim()),
                    Double.parseDouble(tfEntertainment.getText().trim()),
                    Double.parseDouble(tfBirthHoliday.getText().trim()),
                    Double.parseDouble(tfHealthCare.getText().trim())); //Creating a new receipt object
            listR.add(receipt);
            //Setting Text Fields back to a default
            tfHousing.setText("0.00");
            tfUtilities.setText("0.00");
            tfTransportGas.setText("0.00");
            tfGroceries.setText("0.00");
            tfInternetCellphone.setText("0.00");
            tfEntertainment.setText("0.00");
            tfBirthHoliday.setText("0.00");
            tfHealthCare.setText("0.00");
            tfHousing.requestFocus(); //Requesting focus back to the housing text area for user ease
        });

        //Handle menu action "Calculate Receipts"
        menuItemCalculateReceipt.setOnAction(e -> {
            double houseTotal = 0.0;
            double utilTotal = 0.0;
            double tranGasTotal = 0.0;
            double groceriesTotal = 0.0;
            double intCellTotal = 0.0;
            double entertainTotal = 0.0;
            double birthTotal = 0.0;
            double healthTotal = 0.0;
            double total = 0.0;

            for(int i = 0; i <= listR.size() - 1; i++){
                houseTotal += listR.get(i).getHousing(); //house total
            }

            for (int j = 0; j <= listR.size() - 1; j++){
                utilTotal += listR.get(j).getUtilities(); //Utilities total
            }

            for(int k = 0; k <= listR.size() - 1; k++){
                tranGasTotal += listR.get(k).getTransportGas(); //Transport and Gas total
            }

            for(int l = 0; l <= listR.size() - 1; l++){
                groceriesTotal += listR.get(l).getGroceriesHouseItems(); //Groceries/House/Cleaning total
            }

            for(int m = 0; m <= listR.size() - 1; m++){
                intCellTotal += listR.get(m).getInternetCellphone(); //Internet/Cellphone total
            }

            for(int n = 0; n <= listR.size() - 1; n++){
                entertainTotal += listR.get(n).getEntertainment(); //Entertainment total
            }

            for(int o = 0; o <= listR.size() - 1; o++){
                birthTotal += listR.get(o).getBirthHoliday(); //Birthday/Holiday total
            }

            for(int p = 0; p <= listR.size() - 1; p++){
                healthTotal += listR.get(p).getHealthCare(); //Healthcare total
            }

            for (int q = 0; q <= listR.size() - 1; q++){
                total += listR.get(q).getReceiptTotal(); //Total for all receipts for loop and display
            }
            //Display the calculated results in the text area
            taDisplay.setText(String.format("Housing Total: $%4.2f\nUtilities Total: $%4.2f" +
                            "\nTransportation/Gas Total: $%4.2f\nGroceries/House/Cleaning Total: $%4.2f" +
                            "\nInternet/Cellphone Total: $%4.2f\nEntertainment Total: $%4.2f" +
                            "\nBirthday/Holidays Total: $%4.2f\nHealthcare Total: $%4.2f" +
                            "\n\nTotal of all Receipts: $%4.2f\nNumber of Receipts Entered: " + listR.size(),
                    houseTotal, utilTotal, tranGasTotal, groceriesTotal, intCellTotal, entertainTotal,
                    birthTotal, healthTotal, total));

        });

        //Handle menu actions "Delete last Receipt" and "Close"
        menuItemDelete.setOnAction(e -> {
            //checking if there are no receipts to delete
            if (listR.isEmpty()){
                taDisplay.setText("All Receipts Deleted.\nPlease Enter Receipt values");
            }
            else {
                listR.remove(listR.size() - 1);
                taDisplay.setText("Last entered Receipt Deleted");
                tfHousing.requestFocus(); //Requesting focus back to the housing text area for user ease
            }
        });

        menuItemClose.setOnAction(e -> System.exit(0));

        //Handle button action "Delete last Receipt"
        btDelete.setOnAction(e -> {
            //checking if there are no receipts to delete
            if (listR.isEmpty()){
                taDisplay.setText("All Receipts Deleted.\nPlease Enter Receipt values");
            }
            else {
                listR.remove(listR.size() - 1);
                taDisplay.setText("Last entered Receipt Deleted");
                tfHousing.requestFocus(); //Requesting focus back to the housing text area for user ease
            }
        });

        //Handle button action "Enter Receipt"
        btEnterReceipt.setOnAction(e -> {
            Receipt receipt = new Receipt(Double.parseDouble(tfHousing.getText().trim()),
                    Double.parseDouble(tfUtilities.getText().trim()),
                    Double.parseDouble(tfTransportGas.getText().trim()),
                    Double.parseDouble(tfGroceries.getText().trim()),
                    Double.parseDouble(tfInternetCellphone.getText().trim()),
                    Double.parseDouble(tfEntertainment.getText().trim()),
                    Double.parseDouble(tfBirthHoliday.getText().trim()),
                    Double.parseDouble(tfHealthCare.getText().trim())); //Creating a new receipt object
            listR.add(receipt);
            //Setting Text Fields back to a default
            tfHousing.setText("0.00");
            tfUtilities.setText("0.00");
            tfTransportGas.setText("0.00");
            tfGroceries.setText("0.00");
            tfInternetCellphone.setText("0.00");
            tfEntertainment.setText("0.00");
            tfBirthHoliday.setText("0.00");
            tfHealthCare.setText("0.00");
            tfHousing.requestFocus(); //Requesting focus back to the housing text area for user ease
        });

        //Handle button action "Calculate Receipts"
        btTotal.setOnAction(e -> {
            double houseTotal = 0.0;
            double utilTotal = 0.0;
            double tranGasTotal = 0.0;
            double groceriesTotal = 0.0;
            double intCellTotal = 0.0;
            double entertainTotal = 0.0;
            double birthTotal = 0.0;
            double healthTotal = 0.0;
            double total = 0.0;

            for(int i = 0; i <= listR.size() - 1; i++){
                houseTotal += listR.get(i).getHousing(); //house total
            }

            for (int j = 0; j <= listR.size() - 1; j++){
                utilTotal += listR.get(j).getUtilities(); //Utilities total
            }

            for(int k = 0; k <= listR.size() - 1; k++){
                tranGasTotal += listR.get(k).getTransportGas(); //Transport and Gas total
            }

            for(int l = 0; l <= listR.size() - 1; l++){
                groceriesTotal += listR.get(l).getGroceriesHouseItems(); //Groceries/House/Cleaning total
            }

            for(int m = 0; m <= listR.size() - 1; m++){
                intCellTotal += listR.get(m).getInternetCellphone(); //Internet/Cellphone total
            }

            for(int n = 0; n <= listR.size() - 1; n++){
                entertainTotal += listR.get(n).getEntertainment(); //Entertainment total
            }

            for(int o = 0; o <= listR.size() - 1; o++){
                birthTotal += listR.get(o).getBirthHoliday(); //Birthday/Holiday total
            }

            for(int p = 0; p <= listR.size() - 1; p++){
                healthTotal += listR.get(p).getHealthCare(); //Healthcare total
            }

            for (int q = 0; q <= listR.size() - 1; q++){
                total += listR.get(q).getReceiptTotal(); //Total for all receipts for loop and display
            }
            //Display the calculated results in the text area
            taDisplay.setText(String.format("Housing Total: $%4.2f\nUtilities Total: $%4.2f" +
                            "\nTransportation/Gas Total: $%4.2f\nGroceries/House/Cleaning Total: $%4.2f" +
                            "\nInternet/Cellphone Total: $%4.2f\nEntertainment Total: $%4.2f" +
                            "\nBirthday/Holidays Total: $%4.2f\nHealthcare Total: $%4.2f" +
                            "\n\nTotal of all Receipts: $%4.2f\nNumber of Receipts Entered: " + listR.size(),
                    houseTotal, utilTotal, tranGasTotal, groceriesTotal, intCellTotal, entertainTotal,
                    birthTotal, healthTotal, total));
        });
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     * line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}



