package Model.Item.Weapon.Firearm.Lmg;

import Model.Item.Weapon.Firearm.*;

public abstract class Lmg extends Firearm {

    public Lmg(){
        super();
        isTwoHanded = true;
    }

    public Lmg(double x, double y) {
        super(x, y);
        isTwoHanded = true;
    }
}
