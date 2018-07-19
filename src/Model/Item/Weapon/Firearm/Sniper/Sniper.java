package Model.Item.Weapon.Firearm.Sniper;

import Model.Item.Weapon.Firearm.*;

public abstract class Sniper extends Firearm
{

    Sniper(double weight){
        super(weight);
        isTwoHanded = true;
    }

    public Sniper(double weight, double x, double y) {
        super(weight, x, y);
        isTwoHanded = true;
    }
}
