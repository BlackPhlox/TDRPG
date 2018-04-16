package Model.Item.Weapon.Firearm.Sniper;

import Model.Item.Weapon.Firearm.*;

public abstract class Sniper extends Firearm
{

    Sniper(){
        super();
        isTwoHanded = true;
    }

    public Sniper(double x, double y) {
        super(x, y);
        isTwoHanded = true;
    }
}
