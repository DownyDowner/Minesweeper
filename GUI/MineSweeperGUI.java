package GUI;

import Constants.GameConstants;
import Models.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MineSweeperGUI extends Application {
    private Game game;
    private Label timerLabel;
    private Timeline timeline;
    private GridPane gridPane;
    @Override
    public void start(Stage primaryStage) {
        game = new Game("Player");
        timerLabel = new Label("Temps écoulé : 0 secondes");
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
        timeline.setCycleCount(Animation.INDEFINITE);

        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

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

        VBox headerBox = new VBox(10);
        headerBox.setPadding(new Insets(10));
        headerBox.getChildren().addAll(timerLabel);

        VBox mainBox = new VBox();
        mainBox.getChildren().addAll(headerBox, gridPane);

        Scene scene = new Scene(mainBox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Démineur");
        timeline.play();
        primaryStage.show();
    }

    private void handleButtonClick(int row, int col, MouseButton button) {
        if (!game.isFinished()) {
            if (button == MouseButton.PRIMARY) {
                game.discover(row, col);
            } else if (button == MouseButton.SECONDARY) {
                game.flag(row, col);
            }
            updateButtonsAppearance();
        }
    }

    private void updateButtonsAppearance() {
        for (int row = 0; row < GameConstants.ROWS; row++) {
            for (int col = 0; col < GameConstants.COLS; col++) {
                Button button = getButtonAt(row, col);
                if (button != null) {
                    updateButtonAppearance(button, row, col);
                }
            }
        }
        if (game.isFinished()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fin de la partie");
            alert.setHeaderText(null);
            if (game.isWon()) {
                alert.setContentText("Félicitations, vous avez gagné !");
            } else {
                alert.setContentText("Désolé, vous avez perdu.");
            }
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    Platform.exit();
                }
            });
        }
    }

    private void updateButtonAppearance(Button button, int row, int col) {
        if (game.getCell(row, col).isDiscovered()) {
            button.setDisable(true);
            button.setText(game.getCell(row, col).toString());
        } else {
            if (game.getCell(row, col).isFlagged()) {
                button.setText("P");
                button.setStyle("-fx-background-color: orange;");
            } else {
                button.setDisable(false);
                button.setText("");
                button.setStyle("");
            }
        }

        if (game.isFinished() && game.getCell(row, col).isMined()) {
            if (game.isWon())
                button.setStyle("-fx-background-color: green;");
            else
                button.setStyle("-fx-background-color: red;");
        }
    }

    private Button getButtonAt(int row, int col) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return (Button) node;
            }
        }
        return null;
    }

    private void updateTimer() {
        long elapsedTime = game.getStopwatch().getElapsedTimeInSeconds();
        timerLabel.setText("Temps écoulé : " + elapsedTime + " secondes");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

