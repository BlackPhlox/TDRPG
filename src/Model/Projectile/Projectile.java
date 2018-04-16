package Model.Projectile;

import Model.Round;
import Model.Vector2D;
import javafx.scene.canvas.GraphicsContext;


public class Projectile {
    public Vector2D position, velocity;
    public Round projectileType;

    public Projectile(){
        this(new Vector2D(0,0),0,Round._9MM);
    }

    public Projectile(Vector2D startPos , double angle, Round projectileType){
        position = new Vector2D(startPos.x,startPos.y);
        double vel = 1;
        switch (projectileType){
            case _9MM:   vel = 5  ;break;
            case _5_56:  vel = 15 ;break;
            case _45ACP: vel = 4  ;break;
        }
        velocity = Vector2D.fromAngle(Math.toRadians(angle),vel);
        this.projectileType = projectileType;
    }

    public GraphicsContext update(GraphicsContext gc){
        Vector2D tempVec = Vector2D.add(position,velocity);
        gc.strokeLine(position.x,position.y,tempVec.x,tempVec.y);
        position = tempVec;
        return gc;
    }
}
