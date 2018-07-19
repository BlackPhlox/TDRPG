package Model.Item;

import Model.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

public abstract class Item
{
    private double x,y;
    private double weight;
    public boolean isTwoHanded; //private?
    protected List<String> options = new ArrayList<>();
    //Make this method abstract
    public void action(Player p){}

    public Item(double weight){
        this(weight,0,0);
    }

    public Item(double weight, double x, double y){
        this.x = x; this.y = y;
        this.weight = weight;
        options.add("Equip Right");
        options.add("Equip Left");
        options.add("Drop");
    }

    public GraphicsContext update(GraphicsContext gc){
        gc.save();
        gc.translate(x,y);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText(this.toString(),0,-20);
        //gc.rotate(UI.frameCount);
        gc.fillRect(10-20,10-20,20,20);
        gc.restore();
        return gc;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    @Override
    public String toString(){
        return this.getClass()
                .getSimpleName()
                .replace("class ","") + " " + weight;
    }

    public String getOptions(int index){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < options.size(); i++) {
            s.append("\n     ");
            if(index == i){
                s.append(">");
            }
            s.append(options.get(i));
        }
        return this + s.toString();
    }

    public double getWeight(){
        return weight;
    }
}
