import Constants.GameConstants;
import Models.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main extends Application {

    private static String setupName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez votre nom : ");
        String name = scanner.nextLine();

        return name;
    }

    private static void chooseOption() {
        System.out.println("Que voulez-vous faire ?");
        System.out.println("1. Marquer/Démarquer une case");
        System.out.println("2. Découvrir une case");
        System.out.println();
    }

    private static boolean isValidCell(int row, int col) {
        return row >= 0 && row < GameConstants.ROWS && col >= 0 && col < GameConstants.COLS;
    }

    public static void main(String[] args) {
        launch(args);
        System.out.println("Bienvenue dans le jeu du démineur.");

        String playerName = setupName();
        Game game = new Game(playerName);
        Scanner scanner = new Scanner(System.in);

        while (!game.isFinished()) {
            System.out.println(game.showGame(false));

            boolean isValidOption = false;
            int choiceOption = -1;
            while (!isValidOption) {
                try {
                    chooseOption();
                    choiceOption = scanner.nextInt();
                    isValidOption = choiceOption == 1 || choiceOption == 2;
                    if (!isValidOption)
                        System.out.println("Veuillez entrer un nombre valide (1 ou 2).");
                } catch (InputMismatchException exception) {
                    System.out.println("Veuillez entrer un nombre valide (1 ou 2).");
                    scanner.next();
                }
            }

            boolean isValidNumbers = false;
            int row = -1;
            int col = -1;
            while (!isValidNumbers) {
                try {
                    System.out.print("Veuillez entrer une ligne : ");
                    row = scanner.nextInt();
                    System.out.print("Veuillez entrer une colonne : ");
                    col = scanner.nextInt();

                    isValidNumbers = isValidCell(row, col);
                    if (!isValidNumbers)
                        System.out.println("Veuillez entrer des nombres valides.");
                } catch (InputMismatchException exception) {
                    System.out.println("Veuillez entrer des nombres valides.");
                    scanner.next();
                }
            }

            if (choiceOption == 1) {
                game.flag(row, col);
            } else {
                game.discover(row, col);
            }
        }

        if ((game.isWon())) {
            System.out.println("Bravo ! Vous avez gagné.");
        } else {
            System.out.println("Vous avez perdu.");
        }
        System.out.println(game.showGame(true));
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Création d'une étiquette avec un texte
        Label label = new Label("JavaFX fonctionne correctement !");

        // Création d'un conteneur pour l'étiquette
        StackPane root = new StackPane();
        root.getChildren().add(label);

        // Création de la scène
        Scene scene = new Scene(root, 300, 200);

        // Configuration de la scène sur la scène principale
        stage.setScene(scene);

        // Titre de la fenêtre
        stage.setTitle("Test JavaFX");

        // Affichage de la fenêtre
        stage.show();
    }
}