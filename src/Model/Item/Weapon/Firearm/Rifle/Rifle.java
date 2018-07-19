package Model.Item.Weapon.Firearm.Rifle;

import Model.Item.Weapon.Firearm.*;

abstract class Rifle extends Firearm{

    Rifle(double weight){
        super(weight);
        isTwoHanded = true;
    }

    Rifle(double weight, double x, double y){
        super(weight, x,y);
        isTwoHanded = true;
    }
}
