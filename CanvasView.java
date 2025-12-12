import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CanvasView extends StackPane {

    public Circle circle;
    public Rectangle square;
    private Canvas gridCanvas;

    public CanvasView() {

        setStyle("-fx-background-color: black;");


        gridCanvas = new Canvas(800, 600);
        drawGrid(gridCanvas.getGraphicsContext2D());


        circle = new Circle(40);
        circle.setFill(Color.LIGHTBLUE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1.5);
        circle.setTranslateX(-120);
        circle.setTranslateY(-50);


        square = new Rectangle(80, 80);
        square.setFill(Color.LIGHTPINK);
        square.setStroke(Color.BLACK);
        square.setStrokeWidth(1.5);
        square.setTranslateX(90);
        square.setTranslateY(30);

        getChildren().addAll(gridCanvas, circle, square);
    }

    private void drawGrid(GraphicsContext gc) {

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 800, 600);

        gc.setStroke(Color.GRAY);
        gc.setLineWidth(0.5);


        for (int x = 0; x < 800; x += 20) {
            gc.strokeLine(x, 0, x, 600);
        }
        for (int y = 0; y < 600; y += 20) {
            gc.strokeLine(0, y, 800, y);
        }


        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(2);
        gc.strokeLine(390, 300, 410, 300); // horizontal
        gc.strokeLine(400, 290, 400, 310); // vertical
    }



    public void translate(double dx, double dy) {
        circle.setTranslateX(circle.getTranslateX() + dx);
        circle.setTranslateY(circle.getTranslateY() + dy);

        square.setTranslateX(square.getTranslateX() + dx);
        square.setTranslateY(square.getTranslateY() + dy);
    }

    public void rotate(double angle) {
        circle.setRotate(circle.getRotate() + angle);
        square.setRotate(square.getRotate() + angle);
    }

    public void scale(double sx, double sy) {
        circle.setScaleX(circle.getScaleX() * sx);
        circle.setScaleY(circle.getScaleY() * sy);

        square.setScaleX(square.getScaleX() * sx);
        square.setScaleY(square.getScaleY() * sy);
    }

    public void reset() {
        // Reset circle
        circle.setTranslateX(-120);
        circle.setTranslateY(-50);
        circle.setRotate(0);
        circle.setScaleX(1);
        circle.setScaleY(1);

        // Reset square
        square.setTranslateX(90);
        square.setTranslateY(30);
        square.setRotate(0);
        square.setScaleX(1);
        square.setScaleY(1);
    }
}
