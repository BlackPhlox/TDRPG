package Model.Item.Weapon.Firearm.Handgun;

import Model.Item.Weapon.Firearm.*;

public abstract class Handgun extends Firearm
{
    public Handgun(double weight){
        super(weight);
    }
    public Handgun(double weight, double x, double y) {
        super(weight, x, y);
    }
}