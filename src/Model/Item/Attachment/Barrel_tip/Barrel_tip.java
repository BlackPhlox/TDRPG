package Model.Item.Attachment.Barrel_tip;

import Model.AttachmentPoint;
import Model.Item.Attachment.*;

public abstract class Barrel_tip extends Attachment
{

    public Barrel_tip(){
        super();
        point = AttachmentPoint.BARREL_TIP;

    }

    public Barrel_tip(double x, double y){
        super(x,y);
        point = AttachmentPoint.BARREL_TIP;
    }
}
