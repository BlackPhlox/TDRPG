package Model.Item.Clothes.Head;

import Model.ClothingType;
import Model.Item.Clothes.Clothes;

public abstract class Head extends Clothes{
    public Head(){
        type = ClothingType.HEAD;
        super.addType();
    }

}
