package Model.Item.Attachment;

import Model.AttachmentPoint;
import Model.Hand;
import Model.Item.Item;
import Model.Item.Weapon.Firearm.Firearm;
import Model.Player;

public abstract class Attachment extends Item
{
    protected AttachmentPoint point;

    protected Attachment(double weight){
        super(weight);
    }
    protected Attachment(double weight, double x, double y){
        super(weight, x,y);
    }

    @Override
    public void action(Player p) {
        if(p.getItem(Hand.RIGHT) instanceof Firearm){
            p.addAttachment(this, (Firearm) p.getItem(Hand.RIGHT),point);
        } else if (p.getItem(Hand.LEFT) instanceof Firearm){
            p.addAttachment(this, (Firearm) p.getItem(Hand.LEFT),point);
        }
    }
}
