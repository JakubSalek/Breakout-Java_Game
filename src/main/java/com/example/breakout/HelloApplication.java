package com.example.breakout;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage){
        GameCanvas gameCanvas = new GameCanvas();
        primaryStage.setTitle("Breakout");
        GridPane pane = new GridPane();
        pane.add(gameCanvas,0,0);
        primaryStage.setScene(new Scene(pane));
        primaryStage.setResizable(false);
        gameCanvas.setCursor(Cursor.NONE);
        gameCanvas.initialize();
        gameCanvas.draw();
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}