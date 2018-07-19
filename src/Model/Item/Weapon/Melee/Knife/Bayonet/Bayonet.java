package Model.Item.Weapon.Melee.Knife.Bayonet;

import Model.Item.Weapon.Melee.Knife.*;

public abstract class Bayonet extends Knife
{

    public Bayonet(double weight){
        super(weight);
    }

    public Bayonet(double weight, double x, double y) {
        super(weight, x,y);
    }
}
