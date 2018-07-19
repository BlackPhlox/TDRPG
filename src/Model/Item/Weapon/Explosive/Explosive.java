package Model.Item.Weapon.Explosive;

import Model.Item.Weapon.*;
import Model.Player;
import Model.World;
import View.UI;

public abstract class Explosive extends Weapon
{
    public Explosive(double weight){super(weight);}
    protected Explosive(double weight,double x, double y) {
        super(weight,x, y);
    }

    @Override
    public void action(Player p){
        UI.sout(p,"Threw " + this); //Placed
    }
    //Trigger
}