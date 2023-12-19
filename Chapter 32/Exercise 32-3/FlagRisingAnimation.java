/*
Author (Changes made by) Nicole Hackler
Date: 12/19/2023

Exercise 32-3
(Raise flags) Rewrite Listing 15.13 using a thread to animate a flag being raised.
 */
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FlagRisingAnimation extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a pane
        Pane pane = new Pane();

        // Add an image view and add it to pane
        ImageView imageView = new ImageView("image/us.gif");
        pane.getChildren().add(imageView);
        
        //Created a thread to start the animation
        new Thread(() -> {
                // Create a path transition
                PathTransition pt = new PathTransition(Duration.millis(10000),
                        new Line(100, 200, 100, 0), imageView);
                pt.setCycleCount(5);
                pt.play(); // Start animation
        }).start();

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 250, 200);
        primaryStage.setTitle("FlagRisingAnimation"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}