package GUI;

import Constants.GameConstants;
import Models.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MineSweeperGUI extends Application {
    private Game game;
    private Label timerLabel;
    private Timeline timeline;
    @Override
    public void start(Stage primaryStage) {
        game = new Game("Player");

        timerLabel = new Label("Temps écoulé : 0 secondes");
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
        timeline.setCycleCount(Animation.INDEFINITE);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        GridPane.setConstraints(timerLabel, 0, GameConstants.ROWS);
        gridPane.getChildren().add(timerLabel);

        for (int row = 0; row < GameConstants.ROWS; row++) {
            for (int col = 0; col < GameConstants.COLS; col++) {
                Button button = new Button();
                button.setMinSize(30, 30);
                int finalRow = row;
                int finalCol = col;
                button.setOnMouseClicked(e -> {
                    MouseButton buttonClicked = e.getButton();
                    handleButtonClick(finalRow, finalCol, buttonClicked);
                });
                gridPane.add(button, col, row);
            }
        }

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Démineur");
        timeline.play();
        primaryStage.show();
    }

    private void handleButtonClick(int row, int col, MouseButton button) {
        if (button == MouseButton.PRIMARY) {
            game.discover(row, col);
        } else if (button == MouseButton.SECONDARY) {
            game.flag(row, col);
        }
    }

    private void updateTimer() {
        long elapsedTime = game.getStopwatch().getElapsedTimeInSeconds();
        timerLabel.setText("Temps écoulé : " + elapsedTime + " secondes");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

