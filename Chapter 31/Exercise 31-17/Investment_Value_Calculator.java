/*
Author: Nicole Hackler
Date: 1/10/2024

Programing Exercise 31-17
(Create an investment value calculator) Write a program that calculates the future value of an
investment at a given interest rate for a specified number of years. The formula for the calculation
is as follows:

futureValue = investmentAmount × (1 + monthlyInterestRate)years×12

**int numberOfYears = Integer.parseInt(tfNumberOfYears.getText()) * 12;**
**double result = investmentAmount * Math.pow((1 + annualInterestRate/1200), numberOfYears);**

Use text fields for interest rate, investment amount, and years. Display the future amount in a
text field when the user clicks the Calculate button or chooses Calculate from the Operation menu.
Click the Exit menu to exit the program.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Investment_Value_Calculator extends Application {
    private TextField tfInvestmentAmount = new TextField();
    private TextField tfNumberOfYears = new TextField();
    private TextField tfAnnualInterestRate = new TextField();
    private TextField tfFutureValue = new TextField();

    @Override //Override the start method in the Application class
    public void start(Stage primaryStage){
        MenuBar menuBar = new MenuBar(); //Creating a menu

        Menu menuOperation = new Menu("Operation"); //Adding the menu Operations to the bar
        menuBar.getMenus().add(menuOperation);

        MenuItem menuItemCalculate = new MenuItem("Calculate"); //Adding menu Items to the drop-down menu
        MenuItem menuItemExit = new MenuItem("Exit");
        menuOperation.getItems().addAll(menuItemCalculate, menuItemExit);

        menuItemCalculate.setAccelerator(KeyCombination.keyCombination("Ctrl+A")); //Adding shortcuts for operations
        menuItemExit.setAccelerator(KeyCombination.keyCombination("Ctrl+E"));

        HBox hBox = new HBox(10); //Hbox for the calculate button
        Button btCalculate = new Button("Calculate");
        hBox.getChildren().add(btCalculate);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);

        HBox hBoxMenu = new HBox(10); //Hbox for the menu bar
        hBoxMenu.getChildren().add(menuBar);
        hBoxMenu.setAlignment(Pos.TOP_LEFT);

        VBox vBoxTF = new VBox(10); //Vbox for the text fields
        tfInvestmentAmount.setPrefColumnCount(12);
        tfNumberOfYears.setPrefColumnCount(12);
        tfAnnualInterestRate.setPrefColumnCount(12);
        tfFutureValue.setPrefColumnCount(12);
        tfFutureValue.setEditable(false); //Setting this text field to Non-editable
        vBoxTF.getChildren().addAll(tfInvestmentAmount, tfNumberOfYears, tfAnnualInterestRate, tfFutureValue);
        vBoxTF.setAlignment(Pos.CENTER_RIGHT);

        VBox vBoxLabel = new VBox(18); //Vbox to hold the labels for the text fields
        vBoxLabel.getChildren().addAll(new Label(" Investment Amount:"), new Label(" Number of Years:"),
                new Label(" Annual Interest Rate:"), new Label(" Future Value:"));
        vBoxLabel.setAlignment(Pos.CENTER_LEFT);

        //BorderPane to hold the labels, menu, calculate button and the text fields
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setBottom(hBox);
        borderPane.setLeft(vBoxLabel);
        borderPane.setRight(vBoxTF);

        Scene scene =new Scene(borderPane, 280, 200);
        primaryStage.setTitle("Exercise 31-17"); //Set the window title
        primaryStage.setScene(scene); //Place the scene in the window
        primaryStage.show(); //Display the window

        //Event handlers for menu options and button
        menuItemCalculate.setOnAction(e -> calculate());
        btCalculate.setOnAction(e -> calculate());
        menuItemExit.setOnAction(e -> System.exit(0));

    }
    //Method for calculating the Future Value
    private void calculate(){
        double investmentAmount = Double.parseDouble(tfInvestmentAmount.getText());
        int numberOfYears = Integer.parseInt(tfNumberOfYears.getText()) * 12;
        double annualInterestRate = Double.parseDouble(tfAnnualInterestRate.getText());

        double result = investmentAmount * Math.pow((1 + annualInterestRate/1200), numberOfYears);
        tfFutureValue.setText(String.format("$%4.2f", result));
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
