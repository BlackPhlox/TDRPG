package Model.Item.Weapon.Explosive;

import Model.Item.Weapon.*;
import Model.Player;
import Model.World;
import View.UI;

public abstract class Explosive extends Weapon
{
    public Explosive(){super();}
    protected Explosive(double x, double y) {
        super(x, y);
    }

    @Override
    public void action(Player p){
        UI.sout(p,"Threw " + this); //Placed
    }
    //Trigger
}