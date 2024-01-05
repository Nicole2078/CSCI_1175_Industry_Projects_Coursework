/*
Author: Nicole Hackler
Date: 1/04/2024

(Chat) Write a program that enables two users to chat. Implement one user as the server and the other
as the client.

The server has two text areas: one for entering text and the other (non-editable) for displaying the
history of the conversation. When the user presses the Enter key, the current line is sent to the
client and the text area is cleared.

The client has two text areas: one (non-editable) for displaying the history of the conversation.
When the user presses the Enter key, the current line is sent to the server and the text area is cleared.
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Exercise33_09Server extends Application {
    private TextArea taServer = new TextArea();
    private TextArea taClient = new TextArea();

    private DataInputStream inputFromClient = null;
    private DataOutputStream outputToClient = null;

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        taServer.setWrapText(true);
        taClient.setWrapText(true);
        taServer.setEditable(false);
        //taClient.setDisable(true);

        BorderPane pane1 = new BorderPane();
        pane1.setTop(new Label("History"));
        pane1.setCenter(new ScrollPane(taServer));
        BorderPane pane2 = new BorderPane();
        pane2.setTop(new Label("New Message"));
        pane2.setCenter(new ScrollPane(taClient));

        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(pane1, pane2);

        // Create a scene and place it in the stage
        Scene scene = new Scene(vBox, 200, 200);
        primaryStage.setTitle("Exercise31_09Server"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        // To complete later
        //Enter button key event handler
        taClient.setOnKeyPressed(e ->{
            try{
                if (e.getCode() == KeyCode.ENTER) {
                    //Getting text from Typing text field
                    String line = taClient.getText().trim();

                    //Updating the history text field
                    taServer.appendText("S: " + line + '\n');

                    //Sending the text field information to the client
                    outputToClient.writeUTF(line);
                    outputToClient.flush();

                    //Clearing the Text field
                    taClient.setText("");
                }
            }
            catch (IOException ex){
                System.err.println(ex);
            }
        });

        new Thread(() -> {
            try {
                //Create a server socket
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(() ->
                        taServer.appendText("Server started at " + new Date() + '\n'));

                //Listen for a connection request
                Socket socket = serverSocket.accept();

                //Create data input and output streams
                inputFromClient = new DataInputStream(socket.getInputStream());
                outputToClient = new DataOutputStream(socket.getOutputStream());

                while (true){
                    //receive data from client
                    String line = inputFromClient.readUTF();

                    //Update the history log with what the client sent
                    Platform.runLater(() ->{
                        taServer.appendText("C: " + line + '\n');
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
