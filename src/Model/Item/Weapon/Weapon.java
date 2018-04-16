package Model.Item.Weapon;

import Model.Item.*;

public abstract class Weapon extends Item
{
    protected Weapon(){
        super();
    }
    protected Weapon(double x, double y){
        super(x,y);
    }
    int ammo_capacity;
}

