package Model.Item.Weapon.Launcher;

import Model.Item.Weapon.*;
import Model.Player;
import Model.World;
import View.UI;

public class Launcher extends Weapon
{
    protected Launcher(double weight){
        super(weight);
        isTwoHanded = true;
    }

    protected Launcher(double weight, double x, double y) {
        super(weight, x, y);
        isTwoHanded = true;
    }

    @Override
    public void action(Player p){
        UI.sout(p,"Lanuched " + this);
    }
}
