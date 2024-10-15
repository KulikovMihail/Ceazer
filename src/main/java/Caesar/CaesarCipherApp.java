package Caesar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CaesarCipherApp extends Application {

    private static final String RUSSIAN_ALPHABET = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
    private static final int ALPHABET_SIZE = RUSSIAN_ALPHABET.length();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Шифр Цезаря");

        Label inputLabel = new Label("Введите текст:");
        TextField inputField = new TextField();

        Label shiftLabel = new Label("Введите сдвиг:");
        TextField shiftField = new TextField();

        Button encryptButton = new Button("Зашифровать");
        Button decryptButton = new Button("Расшифровать");

        Label resultLabel = new Label();

        encryptButton.setOnAction(e -> {
            String text = inputField.getText();
            int shift = Integer.parseInt(shiftField.getText());
            resultLabel.setText("Зашифрованный текст: " + encrypt(text, shift));
        });

        decryptButton.setOnAction(e -> {
            String text = inputField.getText();
            int shift = Integer.parseInt(shiftField.getText());
            resultLabel.setText("Расшифрованный текст: " + decrypt(text, shift));
        });

        VBox vbox = new VBox(10, inputLabel, inputField, shiftLabel, shiftField, encryptButton, decryptButton, resultLabel);
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String encrypt(String text, int shift) {
        return shiftText(text, shift);
    }

    private String decrypt(String text, int shift) {
        return shiftText(text, -shift);
    }

    private String shiftText(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = RUSSIAN_ALPHABET.indexOf(Character.toLowerCase(c));
            if (index != -1) {
                int newIndex = (index + shift + ALPHABET_SIZE) % ALPHABET_SIZE;
                char shiftedChar = RUSSIAN_ALPHABET.charAt(newIndex);
                result.append(Character.isUpperCase(c) ? Character.toUpperCase(shiftedChar) : shiftedChar);
            } else {
                result.append(c); // Неизменяем символы, которых нет в алфавите
            }
        }
        return result.toString();
    }
}
