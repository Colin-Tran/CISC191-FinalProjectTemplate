package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;

import com.sun.xml.internal.ws.api.addressing.AddressingVersion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Server extends Application{
    double amount;
    double years;
    double monthlyInterestRate;

    public void start(Stage stage) throws Exception{

        BorderPane root = new BorderPane();


        Button button = new Button("Expenses and Income");
        button.setPrefSize(250, 100);

        root.setTop(button);

        button.setOnAction(e -> {
            Expenses exp = new Expenses(root);
        });


        Pane p = new Pane();
        Font f = new Font("SANS_SERIF", 50);
        Text t = new Text(200, 250, "Expense Tracker");
        t.isUnderline();
        t.setFont(f);
        p.getChildren().add(t);
        root.setCenter(p);

        HBox bottomButtons = new HBox();
        Button exitButton = new Button("Exit");

        exitButton.setPrefSize(150, 100);
        bottomButtons.getChildren().add(exitButton);
        exitButton.setOnAction(e -> {
            System.exit(0);
        });



        root.setBottom(bottomButtons);
        Scene primaryScene = new Scene(root, 750, 750);
        stage.setTitle("ExpenseTracker");
        stage.setScene(primaryScene);
        stage.show();
    }



    public static void main(String[] args) {
        Expenses p = new Expenses(new BorderPane());

        launch();

    }

}