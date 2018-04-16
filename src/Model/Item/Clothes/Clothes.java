package Model.Item.Clothes;

import Model.ClothingType;
import Model.Item.Item;
import Model.Player;

public abstract class Clothes extends Item
{
    public ClothingType type;

    protected Clothes(){
        super();
    }

    @Override
    public void action(Player p) {
        p.clothe(type,this);
    }

    public void addType(){
        options.add("Put " + this + " on " +type.toString());
    }
}
