package Model.Item.Weapon.Melee.Knife;

import Model.Item.Weapon.Melee.Melee;

public abstract class Knife extends Melee
{
    protected Knife(double weight){
        super(weight);
    }

    public Knife(double weight, double x, double y){
        super(weight, x,y);
        isTwoHanded = false;
    }
}
