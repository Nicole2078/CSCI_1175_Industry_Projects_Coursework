/*
Author: Nicole Hackler
Date: 1/11/2024

Programming Exercise 31-20
(Use tab panes) Modify TabPaneDemo.javaLinks to an external site. to add a pane of radio buttons for specifying
the tab placement of the tab pane.
*/
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.TopLevelAttribute;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TabPaneDemo extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("Line");
        StackPane pane1 = new StackPane();
        pane1.getChildren().add(new Line(10, 10, 80, 80));
        tab1.setContent(pane1);
        Tab tab2 = new Tab("Rectangle");
        tab2.setContent(new Rectangle(10, 10, 200, 200));
        Tab tab3 = new Tab("Circle");
        tab3.setContent(new Circle(50, 50, 20));
        Tab tab4 = new Tab("Ellipse");
        tab4.setContent(new Ellipse(10, 10, 100, 80));
        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4);

        HBox hBoxRB = new HBox(10); //Hbox to hold Radio Buttons
        hBoxRB.setPadding(new Insets(5, 5, 5, 5));
        //Creating Radio Buttons
        RadioButton top = new RadioButton("Top");
        RadioButton left = new RadioButton("Left");
        RadioButton bottom = new RadioButton("Bottom");
        RadioButton right = new RadioButton("Right");
        hBoxRB.getChildren().addAll(top, left, bottom, right); //placing them in the Hbox

        ToggleGroup group= new ToggleGroup(); //Group for Radio Buttons
        top.setToggleGroup(group);
        left.setToggleGroup(group);
        bottom.setToggleGroup(group);
        right.setToggleGroup(group);

        BorderPane borderPane = new BorderPane(); //Border Pane to hold the Tab Pane and Hbox
        borderPane.setBottom(hBoxRB);
        borderPane.setTop(tabPane);

        //Radio Button event handling
        top.setOnAction(e ->{
            if (top.isSelected()){
                tabPane.setSide(Side.TOP);
            }
        });
        left.setOnAction(e ->{
            if (left.isSelected()){
                tabPane.setSide(Side.LEFT);
            }
        });
        bottom.setOnAction(e ->{
            if (bottom.isSelected()){
                tabPane.setSide(Side.BOTTOM);
            }
        });
        right.setOnAction(e ->{
            if (right.isSelected()){
                tabPane.setSide(Side.RIGHT);
            }
        });

        Scene scene = new Scene(borderPane, 300, 250);
        primaryStage.setTitle("DisplayFigure"); // Set the window title
        primaryStage.setScene(scene); // Place the scene in the window
        primaryStage.show(); // Display the window
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

