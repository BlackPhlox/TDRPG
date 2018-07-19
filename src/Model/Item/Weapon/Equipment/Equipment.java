package Model.Item.Weapon.Equipment;

import Model.Item.Weapon.*;
import Model.Player;
import Model.World;
import View.UI;

public abstract class Equipment extends Weapon
{
    protected Equipment(double weight){
        super(weight);
    }
    protected Equipment(double weight,double x, double y) {
        super(weight, x, y);
    }

    @Override
    public void action(Player p){
        UI.sout(p,"Deployed " + this);
    }
}
