package Model;

import Model.Item.Attachment.Attachment;
import Model.Item.Clothes.Clothes;
import Model.Item.Item;
import Model.Item.Weapon.Firearm.Firearm;
import View.UI;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player
{
    private ArrayList<Item> inventory;
    private HashMap<ClothingType,Clothes> outfit;
    private Item leftHand, rightHand;
    private String name;
    private boolean isControlled;
    public double x,y;
    private double w,h;
    public double bodyWeight = 80;
    private double health, maxHealth = 100;
    private double armour, maxArmour = 100;
    public double stamina,
            maxStamina = 100,
            restitutionRate = 1,
            fatigueRate = 2,
            fatigueMult = 1,
            recoverRate = 100,
            totalWeight;
    private double walkSpeed = 2,runningSpeed = 3;
    private Vector2D heading;
    private int selectorIndex;
    public Player(String name, double x, double y, double w, double h)
    {
        this.name = name;
        this.x = x; this.y = y; this.w = w; this.h = h;
        health = maxHealth;
        armour = maxArmour;
        inventory = new ArrayList<>();
        outfit = new HashMap<>();
        heading = new Vector2D(0,0);
        setTotalWeight();
        UI.sout(this, "Spawned at (x:" + x + " y:"+y+")");
    }

    //Movement methods

    double getX(){return x;}
    double getY(){return y;}

    public void clothe(ClothingType ct, Clothes c){
        UI.sout(this,"Putting " + c + " on " + ct);
        if(ct == c.type){
            if(outfit.get(ct) != null) inventory.add(outfit.get(ct));
            outfit.put(ct,c);
            if(leftHand == c){
                leftHand = null;
            } else if(rightHand == c){
                rightHand = null;
            } else {
                removeItem(c);
            }
        } else {
            UI.sout(this,"Can't use this type of clothes here");
        }
    }

    public void unclothe(ClothingType ct){
        outfit.replace(ct, null);
    }

    public void addAttachment(Attachment a, Firearm f, AttachmentPoint ap){
        f.addAttachment(this,ap,a);
        removeItem(a);
    }

    public void removeAttachment(Firearm f,AttachmentPoint ap){
        f.removeAttachment(this,ap);
    }

    public void addItem(Item i){
        UI.sout(this,"Added " + i + " to inventory");
        inventory.add(i);
        setTotalWeight();
    }

    public void removeItem(Item i){
        UI.sout(this,"Removed " + i + " to inventory");
        inventory.remove(i);
        setTotalWeight();
    }

    public void removeItem(int i){
        UI.sout(this,"Removed " + inventory.get(i) + " to inventory");
        inventory.remove(i);
        setTotalWeight();
    }

    public void pickupItem(List<Item> items){
        if(!items.isEmpty()){
            for(Item i : items){
                //DEBUG System.out.println(x + " " + i.getX() + " = " + (x - i.getX()) );
                if(x - i.getX() < 20 && x - i.getX() >= 0 && y-i.getY() < 20 && y-i.getY() >= 0){
                    UI.sout(this,"Picked up a " + i);
                    items.remove(i);
                    addItem(i);
                    break;
                }
            }
        }
    }

    public void setFatigueMult(double fatigueMult) {
        this.fatigueMult = fatigueMult;
    }

    public void setTotalWeight(){
        double weightSum = 0;
        for(Item i : inventory){
            weightSum += i.getWeight();
        }
        if(leftHand != null) weightSum += leftHand.getWeight();
        if(rightHand != null) weightSum += rightHand.getWeight();
        totalWeight = weightSum;
        //TODO Improve stamina reduction function
        //See GeoGebra Sketch
        setFatigueMult(Math.pow(totalWeight,2)/bodyWeight+1);
    }

    public Item getItem(int i){
        Item it;
        try{
            it = inventory.get(i);
        } catch(IndexOutOfBoundsException e){
            UI.sout(this,"Item in inventory with index: "+ i +" not found");
            return null;
        }
        return it;
    }

    public Item getItem(Hand h){
        if(h == Hand.LEFT){
            return leftHand;
        } else {
            return rightHand;
        }
    }

    public void equip(Item i, Hand h){
        if(i != null){
            UI.sout(this,"Is equipping " + i + " in the " + h + " hand");
            if(i.isTwoHanded){
                UI.sout(this,"Is equipping a two-handed item");
                switch(h){
                    case RIGHT: equipHand(i,Hand.RIGHT); equipHand(null,Hand.LEFT); break;
                    case LEFT:  equipHand(i,Hand.LEFT); equipHand(null,Hand.RIGHT); break;
                }
                removeItem(i);
            } else {
                UI.sout(this,"Is equipping a non-two-handed item");
                switch(h){
                    case RIGHT:
                        equipHand(i,Hand.RIGHT);
                        if(leftHand != null){
                            if(leftHand.isTwoHanded)leftHand = null;
                        }
                        break;
                    case LEFT:
                        equipHand(i,Hand.LEFT);
                        if(rightHand != null){
                            if(rightHand.isTwoHanded)rightHand = null;
                        }
                        break;
                    default:
                        equipHand(null,Hand.RIGHT);
                        equipHand(null,Hand.LEFT);
                        UI.sout(this,"Hand not defined");
                        break;
                }
            }
        } else {
            UI.sout(this,"No item found to be equipped");
        }
    }

    private void equipHand(Item i, Hand h){
        switch(h){
            case LEFT:
                if(leftHand != null){
                    UI.sout(this,"Moved " + leftHand + " back to inventory");
                    inventory.add(leftHand);
                }
                UI.sout(this,"Equipped " + i + " in left hand");
                leftHand = i;
                break;
            case RIGHT:
                if(rightHand != null){
                   UI.sout(this,"Moved " + rightHand + " back to inventory");
                   inventory.add(rightHand);
                }
                UI.sout(this,"Equipped " + i + " in right hand");
                rightHand = i;
                break;
        }
        removeItem(i);
    }

    public void dropItem(int i){
        UI.sout(this,"Dropping item");
        if(i >= 0) {
            if (inventory.size()-1 < i) {
                UI.sout(this,i+"");
                int index = 0;
                for (Map.Entry<ClothingType, Clothes> entry : outfit.entrySet()) {
                    if (inventory.size()-1 + index == i) {
                        outfit.replace(entry.getKey(), null);
                        UI.sout(this,"Drops " + entry.getValue());
                        break;
                    }
                    index++;
                }
            } else {
                if(inventory.size() > 0){
                    UI.sout(this, "Dropped " + inventory.get(i));
                    inventory.get(i).setX(x);
                    inventory.get(i).setY(y);
                    UI.getItems().add(inventory.get(i));
                    removeItem(i);
                    if (inventory.size() == 0) {
                        selectorIndex = -1;
                    }
                } else {
                    UI.sout(this, "Inventory is empty");
                }

            }
        } else if (i == -1){
            if(rightHand != null){
                dropFromHand(Hand.RIGHT);
            } else UI.sout(this,"Right hand is empty");
        } else if (i == -2){
            if(leftHand != null){
                dropFromHand(Hand.LEFT);
            } else UI.sout(this,"Left hand is empty");
        }
        setTotalWeight();
    }

    private void dropFromHand(Hand h){
        UI.sout(this,"Dropped " + h.toString().toLowerCase());
        if(h == Hand.RIGHT){
            rightHand.setX(x);rightHand.setY(y);
            UI.getItems().add(rightHand);
            rightHand = null;
        } else{
            leftHand.setX(x);leftHand.setY(y);
            UI.getItems().add(leftHand);
            leftHand = null;
        }
    }

    public void action(int i){
        if(i < 0){
            if(i == -1) if(rightHand != null) rightHand.action(this);
            if(i == -2) if(leftHand != null)  leftHand.action(this);
        } else if (i > inventory.size()-1){
            UI.sout(this,"Clothes only have put on as an action");
        } else {
            inventory.get(i).action(this);
        }
    }

    public void action(Hand h){
        switch(h){
            case RIGHT:
                UI.sout(this,"Took action in his right hand");
                if (rightHand != null){
                    rightHand.action(this);
                } else {
                    UI.sout(this,"There is no item in your right hand");
                }
                break;

            case LEFT:
                UI.sout(this,"Took action in his left hand");
                if (leftHand != null){
                    leftHand.action(this);
                } else {
                    UI.sout(this,"There is no item in your left hand");
                }
                break;
        }
    }

    void showHands(){
        UI.sout(this,"Showing hands:");
        UI.sout(this,"L: " + leftHand + " R: " + rightHand);
    }

    public String getHand(Hand h){
        String s = "";
        if(h == Hand.RIGHT){

                if(selectorIndex == -1) {
                    s += ">";
                    if(getItem(Hand.RIGHT)!= null) {
                        s += "Right: " + getItem(Hand.RIGHT).getOptions(0);
                    } else return s + "Right: null";
                } else {
                    s += "Right: " + getItem(Hand.RIGHT);
                }
                return s;
        } else {
                if(selectorIndex == -2){
                    s += ">";
                    if(getItem(Hand.LEFT)!= null){
                        s += "Left: " + getItem(Hand.LEFT).getOptions(0);
                    } else return s + "Left: null";
                } else {
                    s += "Left: " + getItem(Hand.LEFT);
                }
                return s;
        }
    }

    public String getInventory(){
        String s = "Inventory: ";
        int index = 0;
        for(Item i : inventory){
            s += "\n";
            if(index == selectorIndex){
                s += ">";
                s += "Item " + (index++) + ": " +i.getOptions(0);
            } else {
                s += "Item " + (index++) + ": " +i;
            }
        }
        return s;
    }

    void showInventory(){
        UI.sout(this,"Showing inventory:");
        int index = 0;
        for(Item i : inventory){
            UI.sout(this,""+(index++)+". "+i);
        }
        if(index == 0){
            UI.sout(this,"Inventory is empty");
        }
    }

    void showAttachments(Firearm f){
        UI.sout(this,"Showing " + f +"'s attachments:");
        f.showAttachments(this);
    }

    void showClothing(){
        UI.sout(this,"Showing clothes:");
        int index = 0;
        for (Map.Entry<ClothingType, Clothes> entry : outfit.entrySet()) {
            UI.sout(this,""+(index++)+". "+ entry.getKey() + " | " + entry.getValue());
        }
    }

    public String getClothing(){
        String s = "Clothes: \n";
        int index = 0;
        for (Map.Entry<ClothingType, Clothes> entry : outfit.entrySet()) {
            if(inventory.size()+index == selectorIndex){
                s += ">";
                s += ""+(index++)+". "+ entry.getKey() + " | " + entry.getValue().getOptions(0)+"\n";
            } else {
                s += ""+(index++)+". "+ entry.getKey() + " | " + entry.getValue()+"\n";
            }
        }
        return s;
    }

    public String getStats(){
        StringBuilder s = new StringBuilder();
        s.append("Health:  "+ health);        s.append("\n");
        s.append("Armour:  "+ armour);        s.append("\n");
        s.append("Stamina: "+ stamina);       s.append("\n");
        s.append("Penalty: "+ UI.penalty +" Res:"+ restitutionRate+" Fa:"+fatigueRate+ "FaMult:"+fatigueMult+" Rec:"+recoverRate);    s.append("\n");
        s.append("Inv i: "  + selectorIndex); s.append("\n");
        s.append("Walk Sp: "+ walkSpeed);     s.append("\n");
        s.append("Run Sp: " + runningSpeed);  s.append("\n");
        s.append("Heading: "+ getHeading());
        return s.toString();
    }

    void getItemTree(Item i){
        UI.sout(this,"Showing item-tree of " + i + " :");
        Class C = i.getClass();
        while (C != null) {
            UI.sout(this,C.getSimpleName());
            C = C.getSuperclass();
            if(C != null){
                UI.sout(this, "/\\");
            }
        }
    }
    private boolean isDeselected = false;
    public GraphicsContext update(GraphicsContext gc, Canvas canvas){
        gc.save();
            gc.setFill(Color.BLUE);
            if(isControlled){
                gc.translate(canvas.getWidth()/2,canvas.getHeight()/2);
                gc.save();
                    gc.translate(-20,-30);
                    gc.setFill(Color.RED);
                    gc.fillRect(0,0,UI.map(health,0,maxHealth,0,40),5);      //HEALTH
                    gc.strokeRect(0,0,40,5);
                    gc.setFill(Color.BLUE);
                    gc.fillRect(0,5,UI.map(armour,0,maxArmour,0,40),5);      //ARMOUR
                    gc.strokeRect(0,5,40,5);
                    gc.setFill(Color.YELLOW);
                    gc.fillRect(0,10,UI.map(stamina,0,maxStamina,0,40),5);//STAMINA
                    gc.strokeRect(0,10,40,5);
                gc.restore();
                Vector2D playerVector = new Vector2D(canvas.getWidth()/2,canvas.getHeight()/2);
                Vector2D mouseVector = new Vector2D(UI.mouseX,UI.mouseY);
                heading = Vector2D.sub(mouseVector,playerVector);
                gc.rotate(Math.toDegrees(heading.getHeading())-90);
                gc.setFill(Color.RED);
                isDeselected = true;
            }
            if (isDeselected && !isControlled){
                x = canvas.getWidth()-x;
                y = canvas.getHeight()-y;
            }
            isDeselected = false;

            if(!isControlled){
                gc.translate(x,y);
                gc.setTextAlign(TextAlignment.CENTER);
                gc.fillText(this.toString(),0,-15);
            }
            //w/2-w center rect
            gc.fillRect(w/2-w,h/2-h,w,h);
            gc.setStroke(Color.WHITE);
            gc.strokeLine(0,0,0,h/2);
        gc.restore();
        return gc;
    }

    public void moveSelector(String s){
        if(s.contains("UP")) {
            if(selectorIndex > -2){
                selectorIndex -= 1;
                UI.sout(this,"Selector index: "+selectorIndex);
            }
        } else {
            if(selectorIndex < inventory.size()-1+outfit.size()) {
                selectorIndex += 1;
                UI.sout(this,"Selector index: "+selectorIndex);
            }
        }
    }

    public String getPlayerInfo(){
        StringBuilder s = new StringBuilder();
        s.append("Hand:");
        s.append("\n");
        s.append(getHand(Hand.LEFT));
        s.append("\n");
        s.append(getHand(Hand.RIGHT));
        s.append("\n");
        s.append(getInventory());
        s.append("\n");
        s.append(getClothing());
        s.append(getStats());
        s.append("\n");
        s.append(getTotalWeight());
        return s.toString();
    }

    private String getTotalWeight() {
        return "Total weight: " + totalWeight;
    }

    public int getSelectorIndex(){
        return selectorIndex;
    }

    public Vector2D getVector(){
        return new Vector2D(x,y);
    }

    public double getHeading() {
        return Math.toDegrees(heading.getHeading());
    }

    public String toString(){
        return name;
    }

    public void setControlled(boolean b){
        isControlled = b;
    }

    public double getWalkSpeed() {
        return walkSpeed;
    }

    public double getRunningSpeed() {
        return runningSpeed;
    }

    public void setFatigueRate(double fatigueRate) {
        this.fatigueRate = fatigueRate;
    }
}
