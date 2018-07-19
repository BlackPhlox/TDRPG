package Model.Item.Weapon.Firearm;


import Model.AttachmentPoint;
import Model.ClothingType;
import Model.Item.Attachment.Attachment;
import Model.Item.Clothes.Clothes;
import Model.Item.Weapon.Weapon;
import Model.Player;
import Model.Projectile.Projectile;
import Model.Round;
import View.UI;

import java.util.*;
public abstract class Firearm extends Weapon
{
    private Map<AttachmentPoint,Attachment> attachments;
    protected Round roundType;

    protected Firearm(double weight){
        super(weight);
        attachments = new HashMap<>();
    }

    protected Firearm(double weight, double x, double y){
        super(weight, x,y);
        attachments = new HashMap<>();
    }

    protected abstract Round getRoundType();

    @Override
    public void action(Player p){
        UI.sout(p,"Fired " + this);
        UI.getProjectiles().add(new Projectile(p.getVector(), p.getHeading(), this.getRoundType()));
    }

    public void addAttachment(Player p, AttachmentPoint ap, Attachment a){
        UI.sout(p,"Added "+ a + " to " + this + "'s " + ap);
        attachments.put(ap,a);
    }

    public void removeAttachment(Player p, AttachmentPoint ap){
        attachments.replace(ap,null);
        UI.sout(p,"Removed "+ ap + " from " + this);
    }

    public void showAttachments(Player p){
        int index = 0;
        for (Map.Entry<AttachmentPoint, Attachment> entry : attachments.entrySet()) {
            UI.sout(p,""+(index++)+". "+ entry.getKey() + " | " + entry.getValue());
        }
        if (index == 0){
            UI.sout(p,"No attachments on " + this + " was found");
        }
    }

    @Override
    public String toString() {
        if(!attachments.isEmpty()){
            String s = "";
            for (Map.Entry<AttachmentPoint,Attachment> entry : attachments.entrySet()) {
                s += entry.getValue()+" ";
            }
            return getClass().getSimpleName() + " ( " +s+")";
        } else {
            return super.toString();
        }
    }
}


