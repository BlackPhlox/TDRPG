package Model.Item.Weapon.Explosive;

import Model.Item.Weapon.*;
import Model.Player;
import Program.*;

public abstract class Explosive extends Weapon
{
    public Explosive(double weight){super(weight);}
    protected Explosive(double weight,double x, double y) {
        super(weight,x, y);
    }

    @Override
    public void action(Player p){
        Program.println(p,"Threw " + this); //Placed
    }
    //Trigger
}