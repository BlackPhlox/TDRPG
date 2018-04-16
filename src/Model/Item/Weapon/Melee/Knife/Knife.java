package Model.Item.Weapon.Melee.Knife;

import Model.Item.Weapon.Melee.Melee;

public abstract class Knife extends Melee
{
    protected Knife(){
        super();
    }

    public Knife(double x, double y){
        super(x,y);
        isTwoHanded = false;
    }
}
