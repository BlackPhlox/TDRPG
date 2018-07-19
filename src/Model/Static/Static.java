package Model.Static;

import javafx.scene.canvas.GraphicsContext;

public abstract class Static {
    private double x,y;

    public Static(double x, double y) {
        this.x = x; this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public abstract GraphicsContext update(GraphicsContext gc);
}
