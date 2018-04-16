package Model.Item.Weapon.Melee;

import Model.Item.Weapon.*;
import Model.Player;
import Model.World;
import View.UI;

public abstract class Melee extends Weapon
{
    String strength = "heavy";

    protected Melee(){
        super();
    }

    protected Melee(double x, double y) {
        super(x, y);
    }

    public void lightSwing(){}

    public void heavySwing(){}

    @Override
    public void action(Player p){
        UI.sout(p,"Used " + this + " to make a " + strength + " swing");
    }
}
