package com.itc.onlinegameitc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("store-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1242, 700);
        stage.setTitle("Market");
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(new Image(GameApplication.class.getResourceAsStream("market.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}