package com.example.kostka;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField inputField = new TextField();
        inputField.setPromptText("Wpisz liczbę kostek (max 10)");

        Button submitButton = new Button("Rzuć kostkami");
        submitButton.setOnAction(e -> {
            try {
                int numDice = Integer.parseInt(inputField.getText());

                if (numDice <= 2) {
                    showAlert("Błąd", "Liczba kostek musi być większa niż 2.");
                } else if (numDice > 10) {
                    showAlert("Błąd", "Maksymalna liczba kostek to 10.");
                } else {
                    Random rand = new Random();
                    int total = 0;
                    StringBuilder resultText = new StringBuilder();
                    for (int i = 0; i < numDice; i++) {
                        int roll = rand.nextInt(6) + 1;
                        resultText.append("Kostka ").append(i + 1).append(" rzuciła wynik: ").append(roll).append("\n");
                        total += roll;
                    }
                    resultText.append("\nSuma wyników rzutu: ").append(total);
                    showAlert("Wyniki rzutu", resultText.toString());
                }
            } catch (NumberFormatException ex) {
                showAlert("Błąd", "Wprowadź poprawną liczbę całkowitą.");
            }
        });

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(inputField, submitButton);

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setTitle("Rzut Kostkami");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
