import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        TransformController controller = new TransformController();

        Scene scene = new Scene(controller.getRoot(), 900, 600);

        primaryStage.setTitle("Visualisasi Transformasi Linear 2D");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
