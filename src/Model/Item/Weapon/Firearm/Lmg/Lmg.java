package Model.Item.Weapon.Firearm.Lmg;

import Model.Item.Weapon.Firearm.*;

public abstract class Lmg extends Firearm {

    public Lmg(double weight){
        super(weight);
        isTwoHanded = true;
    }

    public Lmg(double weight, double x, double y) {
        super(weight, x, y);
        isTwoHanded = true;
    }
}
