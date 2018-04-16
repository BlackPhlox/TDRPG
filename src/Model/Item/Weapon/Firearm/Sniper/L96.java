package Model.Item.Weapon.Firearm.Sniper;

import Model.Round;

public class L96 extends Sniper {

    public L96(){
        this.roundType = Round._5_56;
    }

    public L96(double x, double y){
        super(x,y);
        this.roundType = Round._5_56;
    }

    @Override
    protected Round getRoundType() {
        return roundType;
    }
}
