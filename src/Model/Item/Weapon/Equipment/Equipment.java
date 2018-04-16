package Model.Item.Weapon.Equipment;

import Model.Item.Weapon.*;
import Model.Player;
import Model.World;
import View.UI;

public abstract class Equipment extends Weapon
{
    protected Equipment(){
        super();
    }
    protected Equipment(double x, double y) {
        super(x, y);
    }

    @Override
    public void action(Player p){
        UI.sout(p,"Deployed " + this);
    }
}
