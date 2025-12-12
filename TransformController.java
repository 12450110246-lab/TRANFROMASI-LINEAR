import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class TransformController {

    private CanvasView canvas;
    private BorderPane root;

    public TransformController() {

        canvas = new CanvasView();
        root = new BorderPane();
        root.setCenter(canvas);

        VBox panel = new VBox(20);
        panel.setPadding(new Insets(20));
        panel.setStyle("-fx-background-color: #2C2F33;");
        panel.setPrefWidth(240);

        Label title = new Label("Transformasi Objek");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");


        TextField dxField = createInputField("0");
        TextField dyField = createInputField("0");
        TextField angleField = createInputField("0");
        TextField sxField = createInputField("1");
        TextField syField = createInputField("1");

        Button btnTranslate = createButton("Translasi");
        Button btnRotate = createButton("Rotasi");
        Button btnScale = createButton("Scaling");
        Button btnReset = createResetButton("Reset");


        btnTranslate.setOnAction(e -> {
            try {
                double dx = Double.parseDouble(dxField.getText());
                double dy = Double.parseDouble(dyField.getText());

                animateTranslate(canvas.circle, dx, dy);
                animateTranslate(canvas.square, dx, dy);

            } catch (Exception ex) {
                System.out.println("Input translasi tidak valid!");
            }
        });


        btnRotate.setOnAction(e -> {
            try {
                double angle = Double.parseDouble(angleField.getText());

                animateRotate(canvas.circle, angle);
                animateRotate(canvas.square, angle);

            } catch (Exception ex) {
                System.out.println("Input rotasi tidak valid!");
            }
        });


        btnScale.setOnAction(e -> {
            try {
                double sx = Double.parseDouble(sxField.getText());
                double sy = Double.parseDouble(syField.getText());

                animateScale(canvas.circle, sx, sy);
                animateScale(canvas.square, sx, sy);

            } catch (Exception ex) {
                System.out.println("Input scaling tidak valid!");
            }
        });


        btnReset.setOnAction(e -> {
            resetNode(canvas.circle);
            resetNode(canvas.square);
        });


        panel.getChildren().addAll(
                title,
                createSection("Translasi (dx, dy):", dxField, dyField, btnTranslate),
                createSection("Rotasi (derajat):", angleField, btnRotate),
                createSection("Scaling (sx, sy):", sxField, syField, btnScale),
                btnReset
        );

        root.setRight(panel);
    }

    public BorderPane getRoot() {
        return root;
    }


    private TextField createInputField(String def) {
        TextField t = new TextField(def);
        t.setStyle("-fx-background-radius: 6; -fx-font-size: 14;");
        return t;
    }

    private Button createButton(String text) {
        Button btn = new Button(text);
        btn.setPrefWidth(180);
        btn.setStyle(
                "-fx-background-color: #7289DA; -fx-text-fill: white; " +
                        "-fx-font-size: 14; -fx-background-radius: 10;"
        );

        btn.setOnMouseEntered(e ->
                btn.setStyle("-fx-background-color: #5b6eae; -fx-text-fill: white; -fx-background-radius: 10;")
        );
        btn.setOnMouseExited(e ->
                btn.setStyle("-fx-background-color: #7289DA; -fx-text-fill: white; -fx-background-radius: 10;")
        );

        return btn;
    }

    private Button createResetButton(String text) {
        Button btn = new Button(text);
        btn.setPrefWidth(180);
        btn.setStyle(
                "-fx-background-color: #ff5555; -fx-text-fill: white; " +
                        "-fx-font-size: 14; -fx-background-radius: 10;"
        );

        btn.setOnMouseEntered(e ->
                btn.setStyle("-fx-background-color: #cc4444; -fx-text-fill: white; -fx-background-radius: 10;")
        );
        btn.setOnMouseExited(e ->
                btn.setStyle("-fx-background-color: #ff5555; -fx-text-fill: white; -fx-background-radius: 10;")
        );

        return btn;
    }

    private VBox createSection(String labelText, javafx.scene.Node... children) {
        Label label = new Label(labelText);
        label.setStyle("-fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold;");

        VBox box = new VBox(8);
        box.getChildren().add(label);
        box.getChildren().addAll(children);
        return box;
    }


    private void resetNode(javafx.scene.shape.Shape shape) {
        shape.setTranslateX(0);
        shape.setTranslateY(0);
        shape.setRotate(0);
        shape.setScaleX(1);
        shape.setScaleY(1);
        shape.setEffect(null);
    }




    private void addGlow(javafx.scene.Node node) {
        DropShadow glow = new DropShadow(20, Color.WHITE);
        glow.setSpread(0.2);
        node.setEffect(glow);
    }

    private void removeGlow(javafx.scene.Node node) {
        node.setEffect(null);
    }


    private void animateTranslate(javafx.scene.shape.Shape shape, double dx, double dy) {

        DropShadow glow = new DropShadow(25, Color.WHITE);
        glow.setSpread(0.25);


        Timeline glowIn = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(glow.colorProperty(), Color.rgb(255,255,255,0)),
                        new KeyValue(shape.effectProperty(), glow)
                ),
                new KeyFrame(Duration.millis(200),
                        new KeyValue(glow.colorProperty(), Color.rgb(255,255,255,1))
                )
        );

        double startX = shape.getTranslateX();
        double startY = shape.getTranslateY();
        double targetX = startX + dx;
        double targetY = startY + dy;

        Timeline translate = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(shape.translateXProperty(), startX),
                        new KeyValue(shape.translateYProperty(), startY)
                ),
                new KeyFrame(Duration.millis(650),
                        new KeyValue(shape.translateXProperty(), targetX,
                                Interpolator.SPLINE(0.25, 0.1, 0.25, 1)
                        ),
                        new KeyValue(shape.translateYProperty(), targetY,
                                Interpolator.SPLINE(0.25, 0.1, 0.25, 1)
                        )
                )
        );


        Timeline glowOut = new Timeline(
                new KeyFrame(Duration.ZERO),
                new KeyFrame(Duration.millis(250),
                        new KeyValue(glow.colorProperty(), Color.rgb(255,255,255,0))
                )
        );

        glowOut.setOnFinished(e -> shape.setEffect(null));

        SequentialTransition anim = new SequentialTransition(glowIn, translate, glowOut);
        anim.play();
    }


    private void animateRotate(javafx.scene.shape.Shape shape, double angle) {
        addGlow(shape);

        RotateTransition rt = new RotateTransition(Duration.millis(600), shape);
        rt.setByAngle(angle);
        rt.setInterpolator(Interpolator.EASE_BOTH);

        rt.setOnFinished(e -> removeGlow(shape));
        rt.play();
    }


    private void animateScale(javafx.scene.shape.Shape shape, double sx, double sy) {
        addGlow(shape);

        ScaleTransition st = new ScaleTransition(Duration.millis(600), shape);
        st.setToX(sx);
        st.setToY(sy);
        st.setInterpolator(Interpolator.SPLINE(0.2, 0.7, 0.4, 1)); // sedikit bounce lembut

        st.setOnFinished(e -> removeGlow(shape));
        st.play();
    }
}
