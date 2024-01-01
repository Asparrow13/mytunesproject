package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class MyTunesApplication extends Application {


    /**
     * Initializes and launches the MyTunes application.
     * <p>
     * This method is the entry point for launching the MyTunes application. It sets up the primary stage,
     * loads the FXML layout, configures the stage properties, and displays the main window. Additionally,
     * it sets a custom icon for the stage and adds a background image to the application window.
     *
     * @param primaryStage The primary stage of the application.
     * @throws IOException If an error occurs while loading the FXML layout.
     *                     <p>
     *                     This method uses JavaFX classes such as 'Stage', 'FXMLLoader', 'AnchorPane', 'Scene',
     *                     'Image', 'BackgroundImage', 'Background', and 'StageStyle' for setting up and displaying
     *                     the application window.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the application icon
        InputStream icon = ClassLoader.getSystemResourceAsStream("media/Icons&Images/appIcon.png");
        Image iconImage = new Image(Objects.requireNonNull(icon));

        // Load the FXML layout
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/myTunes.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        // Set stage properties
        primaryStage.getIcons().add(iconImage);
        primaryStage.setMinWidth(1250);
        primaryStage.setMinHeight(750);
        primaryStage.setHeight(800);
        primaryStage.setWidth(1250);
        primaryStage.setTitle("MyTunes");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(iconImage);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();

        // Load and set the background image
        InputStream input = ClassLoader.getSystemResourceAsStream("media/Icons&Images/black.png");
        Image backgroundImage = new Image(Objects.requireNonNull(input));
        BackgroundImage backgroundimage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        Background background = new Background(backgroundimage);
        root.setBackground(background);
    }

    /**
     * The main entry point for launching the MyTunes application.
     * <p>
     * This method is the entry point for launching the MyTunes application. It calls the JavaFX 'launch'
     * method, which initializes the JavaFX runtime and starts the application by calling the 'start' method
     * in the specified Application class (assuming it extends javafx.application.Application).
     *
     * @param args The command-line arguments passed to the application.
     *             <p>
     *             This method serves as the starting point for the MyTunes application and is required
     *             for launching JavaFX applications.
     */
    public static void main(String[] args) {
        launch(args);
    }
}