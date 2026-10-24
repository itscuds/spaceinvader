package br.com.joaovjsilva;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                App.class.getResource(
                        "/br/com/joaovjsilva/view.fxml"
                )
        );

        Parent root = loader.load();
        Controller controller = loader.getController();

        Scene scene = new Scene(root, 640, 480);

        scene.addEventFilter(
                KeyEvent.KEY_PRESSED,
                controller::keyHandler
        );

        scene.addEventFilter(
                KeyEvent.KEY_RELEASED,
                controller::keyReleased
        );

        stage.setTitle("Space Objects");
        stage.setResizable(false);

        var iconStream = App.class.getResourceAsStream(
                "/br/com/joaovjsilva/images/icon.png"
        );

        if (iconStream != null) {
            stage.getIcons().add(new Image(iconStream));
        }

        stage.setScene(scene);
        stage.show();

        root.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}