package Model.Item.Weapon.Firearm.Sniper;

import Model.Round;

public class L96 extends Sniper {

    public L96(double weight){
        super(weight);
        this.roundType = Round._5_56;
    }

    public L96(double weight, double x, double y){
        super(weight, x,y);
        this.roundType = Round._5_56;
    }

    @Override
    protected Round getRoundType() {
        return roundType;
    }
}
