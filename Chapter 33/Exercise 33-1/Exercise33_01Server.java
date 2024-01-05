// Exercise31_01Server.java: The server can communicate with
// multiple clients concurrently using the multiple threads
/*
Author: Nicole Hackler
Date: 1/04/2024

(Loan server)Write a server for a client. The client sends loan information (annual interest rate,
number of years, and loan amount) to the server. The server creates a Loan object to compute monthly
payment and total payment, and sends those numbers back to the client.
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise33_01Server extends Application {
    // Text area for displaying contents
    private TextArea ta = new TextArea();
    private DataInputStream inputFromClient;
    private DataOutputStream outputToClient;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        ta.setWrapText(true);

        // Create a scene and place it in the stage
        Scene scene = new Scene(new ScrollPane(ta), 400, 200);
        primaryStage.setTitle("Exercise31_01Server"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        new Thread(() -> {
            try {
                //Create a server socket
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(() ->
                        ta.appendText("Server Started at " + new Date() + '\n'));

                //Listen for a new connection request
                Socket socket = serverSocket.accept();
                Platform.runLater(() ->
                        ta.appendText("Connected to a client at " + new Date() + '\n'));

                //Create object input and output streams
                inputFromClient = new DataInputStream(socket.getInputStream());
                outputToClient = new DataOutputStream(socket.getOutputStream());

                while (true){
                    //Read from input
                    double annualInterestRate = inputFromClient.readDouble();
                    int numberOfYears = inputFromClient.readInt();
                    double loanAmount = inputFromClient.readDouble();
                    Loan loan = new Loan(annualInterestRate, numberOfYears, loanAmount);

                    //computing monthly and total payments
                    double monthlyPayment = loan.getMonthlyPayment();
                    double totalPayment = loan.getTotalPayment();

                    //Send back to the client
                    outputToClient.writeDouble(monthlyPayment);
                    outputToClient.writeDouble(totalPayment);

                    Platform.runLater(() -> {
                        ta.appendText("Annual Interest Rate: " + annualInterestRate + "\nNumber of years: " +
                                numberOfYears + "\nLoan Amount: " + loanAmount + "\nMonthly Payment: " +
                                monthlyPayment + "\nTotal Payment: " + totalPayment);
                    });
                }
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }).start();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}



