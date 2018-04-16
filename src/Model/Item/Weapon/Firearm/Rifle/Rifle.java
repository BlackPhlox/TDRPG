package Model.Item.Weapon.Firearm.Rifle;

import Model.Item.Weapon.Firearm.*;

abstract class Rifle extends Firearm{

    Rifle(){
        super();
        isTwoHanded = true;
    }

    Rifle(double x, double y){
        super(x,y);
        isTwoHanded = true;
    }
}
