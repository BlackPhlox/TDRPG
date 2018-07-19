package Model.Item.Weapon.Launcher;

import Model.Item.Weapon.*;
import Model.Player;
import Program.*;

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
        Program.println(p,"Lanuched " + this);
    }
}
