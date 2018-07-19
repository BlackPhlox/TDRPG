package Model.Item.Clothes.Torso;

import Model.ClothingType;
import Model.Item.Clothes.*;

public abstract class Torso extends Clothes
{
    public Torso(double weight){
        super(weight);
        type = ClothingType.TORSO;
        super.addType();
    }
}