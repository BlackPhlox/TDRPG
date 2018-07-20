package Model.Static;

import javafx.scene.canvas.GraphicsContext;

public abstract class Static{
    private double x,y,w,h;

    public Static(double x, double y,double w, double h) {
        this.x = x; this.y = y;
        this.w = w; this.h = h;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getW() {
        return w;
    }

    public double getH() {
        return h;
    }

    public abstract GraphicsContext update(GraphicsContext gc);
}
