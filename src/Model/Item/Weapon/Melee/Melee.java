package Model.Item.Weapon.Melee;

import Model.Item.Weapon.*;
import Model.Player;
import Program.*;

public abstract class Melee extends Weapon
{
    String strength = "heavy";

    protected Melee(double weight){
        super(weight);
    }

    protected Melee(double weight, double x, double y) {
        super(weight, x, y);
    }

    public void lightSwing(){}

    public void heavySwing(){}

    @Override
    public void action(Player p){
        Program.println(p,"Used " + this + " to make a " + strength + " swing");
    }
}
