package Model.Item.Weapon.Launcher;

import Model.Item.Weapon.*;
import Model.Player;
import Model.World;
import View.UI;

public abstract class Launcher extends Weapon
{
    protected Launcher(){
        super();
        isTwoHanded = true;
    }

    protected Launcher(double x, double y) {
        super(x, y);
        isTwoHanded = true;
    }

    @Override
    public void action(Player p){
        UI.sout(p,"Lanuched " + this);
    }
}
