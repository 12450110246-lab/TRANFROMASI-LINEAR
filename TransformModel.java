public class TransformModel {

    public double dx, dy;
    public double angle;
    public double sx, sy;

    public void setTranslation(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void setRotation(double angle) {
        this.angle = angle;
    }

    public void setScaling(double sx, double sy) {
        this.sx = sx;
        this.sy = sy;
    }
}
