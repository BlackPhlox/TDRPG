package Model;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public static Vector2D sub(Vector2D v1, Vector2D v2){
        return new Vector2D(v1.x-v2.x,v1.y-v2.y);
    }

    public static Vector2D add(Vector2D v1, Vector2D v2){
        return new Vector2D(v1.x+v2.x,v1.y+v2.y);
    }

    public static Vector2D fromAngle(double angle, double length){
        return new Vector2D(length * Math.cos(angle), length * Math.sin(angle));
    }

    public void multiply(double m){
        x *= m;y *= m;
    }

    public double getHeading(){
        return Math.atan2(y,x);
    }
}
