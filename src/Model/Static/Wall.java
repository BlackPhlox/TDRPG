package Model.Static;

import javafx.scene.canvas.GraphicsContext;

public class Wall extends Static{
    private double w,h;
    public Wall(double x, double y,double w, double h){
        super(x,y);
        this.w = w; this.h = h;
    }

    @Override
    public GraphicsContext update(GraphicsContext gc){
        gc.save();
        gc.translate(super.getX(),super.getY());
        gc.fillRect(w/2-w,h/2-h,w,h);
        gc.restore();
        return gc;
    }

}
