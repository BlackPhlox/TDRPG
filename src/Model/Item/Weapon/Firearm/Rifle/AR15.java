package Model.Item.Weapon.Firearm.Rifle;

import Model.Round;

public class AR15 extends Rifle {

    public AR15(){
        this.roundType = Round._5_56;
    }

    public AR15(double x, double y){
        super(x,y);
        this.roundType = Round._5_56;
    }

    public Round getRoundType(){
        return roundType;
    }
}
