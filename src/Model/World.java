package Model;

import Model.Item.Attachment.Attachment;
import Model.Item.Attachment.Barrel_tip.Suppressor.Sup45acp;
import Model.Item.Clothes.Head.Cool_cap;
import Model.Item.Clothes.Head.Head;
import Model.Item.Clothes.Head.Propeller_hat;
import Model.Item.Clothes.Torso.*;
import Model.Item.Item;
import Model.Item.Weapon.Equipment.Autonomousvehicles.MAV;
import Model.Item.Weapon.Equipment.Mortar.M224;
import Model.Item.Weapon.Firearm.Firearm;
import Model.Item.Weapon.Firearm.Handgun.M1911;
import Model.Item.Weapon.Firearm.Rifle.AR15;
import Model.Item.Weapon.Firearm.Sniper.L96;
import Model.Item.Weapon.Melee.Knife.Bayonet.M9;

import java.util.List;

public class World {
    public static void init(List<Player> players, List<Item> items){
        players.get(0).clothe(ClothingType.TORSO, new Tshirt());
        players.get(0).showClothing();

        players.get(0).addItem(new L96());
        players.get(0).addItem(new M1911());
        players.get(0).addItem(new MAV());

        players.get(0).equip(players.get(0).getItem(0), Hand.RIGHT);//Equipped L96
        players.get(0).action(Hand.RIGHT);
        players.get(0).action(Hand.LEFT);

        players.get(0).equip(players.get(0).getItem(0), Hand.RIGHT);//Equipped M1911
        players.get(0).action(Hand.RIGHT);
        players.get(0).action(Hand.LEFT);

        players.get(0).showInventory();
        players.get(0).dropItem(0);
        players.get(0).dropItem(0);

        players.get(0).showHands();
        players.get(0).showInventory();
        players.get(0).addItem(new Sup45acp());


        Attachment at1 = (Attachment) players.get(0).getItem(0);
        Firearm f1 = (Firearm) players.get(0).getItem(Hand.RIGHT);
        players.get(0).getItemTree(at1);
        players.get(0).addAttachment(at1,f1,AttachmentPoint.BARREL_TIP);
        players.get(0).showAttachments((Firearm) players.get(0).getItem(Hand.RIGHT));


        players.get(1).addItem(new M9());
        players.get(1).equip(players.get(1).getItem(0), Hand.RIGHT);
        players.get(1).equip(players.get(1).getItem(0), Hand.LEFT);
        players.get(1).addItem(new Sup45acp());
        players.get(1).equip(players.get(1).getItem(0), Hand.LEFT);
        players.get(1).addItem(new M224());
        players.get(1).equip(players.get(1).getItem(0), Hand.LEFT);
        players.get(1).showHands();
        players.get(1).showInventory();
        players.get(1).action(Hand.LEFT);
        players.get(1).action(Hand.RIGHT);
        players.get(1).getItemTree(players.get(1).getItem(Hand.RIGHT));
        players.get(0).addItem(new AR15());
        players.get(0).addItem(new Cool_cap());
        players.get(0).addItem(new Propeller_hat());

        players.get(2).equip(new AR15(),Hand.RIGHT);

        players.get(0).addItem(new AR15());
        players.get(0).addItem(new AR15());
        players.get(0).addItem(new AR15());
        players.get(0).addItem(new AR15());
        players.get(0).addItem(new AR15());

        items.add(new M9(30,30));
        items.add(new AR15(300.0,400.0));


    }
}
