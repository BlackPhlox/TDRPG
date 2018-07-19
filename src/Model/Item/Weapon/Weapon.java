package Model.Item.Weapon;

import Model.Item.*;

public abstract class Weapon extends Item
{
    protected Weapon(double weight){
        super(weight);
    }
    protected Weapon(double weight, double x, double y){
        super(weight,x,y);
    }
    int ammo_capacity;
}

