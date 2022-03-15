package softwaredesign.projectManager;

import javafx.application.Application;
import javafx.stage.Stage;

//extends JavaFX Application, not our Application
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        new MainStage().show();
    }
}
