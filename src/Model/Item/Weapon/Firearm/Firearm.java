package Model.Item.Weapon.Firearm;


import Model.AttachmentPoint;
import Model.Item.Attachment.Attachment;
import Model.Item.Weapon.Weapon;
import Model.Player;
import Model.Projectile.Projectile;
import Model.Round;
import View.UI;
import Program.*;

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
        Program.println(p,"Fired " + this);
        UI.getProjectiles().add(new Projectile(p.getVector(), p.getHeading(), this.getRoundType()));
    }

    public void addAttachment(Player p, AttachmentPoint ap, Attachment a){
        Program.println(p,"Added "+ a + " to " + this + "'s " + ap);
        attachments.put(ap,a);
    }

    public void removeAttachment(Player p, AttachmentPoint ap){
        attachments.replace(ap,null);
        Program.println(p,"Removed "+ ap + " from " + this);
    }

    public void showAttachments(Player p){
        int index = 0;
        for (Map.Entry<AttachmentPoint, Attachment> entry : attachments.entrySet()) {
            Program.println(p,""+(index++)+". "+ entry.getKey() + " | " + entry.getValue());
        }
        if (index == 0){
            Program.println(p,"No attachments on " + this + " was found");
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


