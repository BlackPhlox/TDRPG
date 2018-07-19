package Model.Item.Weapon.Firearm.Handgun;


import Model.Round;

public class M1911 extends Handgun {
    public M1911(double weight){
        super(weight);
        this.roundType = Round._45ACP;

    }

    public Round getRoundType(){
        return roundType;
    }
}
