package Model.Item.Clothes.Torso;

import Model.ClothingType;
import Model.Item.Clothes.*;

public abstract class Torso extends Clothes
{
    public Torso(){
        type = ClothingType.TORSO;
        super.addType();
    }
}