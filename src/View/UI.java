package View;

import Model.Hand;
import Model.Item.Item;
import Model.Item.Weapon.Firearm.Firearm;
import Model.Player;
import Model.Projectile.*;
import Model.Round;
import Model.Static.Static;
import Model.World;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.beans.value.*;
import javafx.scene.input.MouseEvent;
import javafx.animation.AnimationTimer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UI extends Application
{
    //UI
    private GraphicsContext gc;
    private Canvas canvas;

    private boolean goNorth,goSouth,goEast,goWest,running;
    public static double mouseX = 0;
    public static double mouseY = 0;
    private boolean priMouseIsPressed = false,secMouseIsPressed = false;

    //More ui
    private Label inventoryContent;

    //Player
    private Player player1,player2,player3;
    private Player currentPlayer;
    public static double penalty;
    private ComboBox<Player> playerSelection;

    //World
    private static List<Player> players = new ArrayList<>();
    private static List<Item> items = new ArrayList<>();
    private static List<Projectile> projectiles = new ArrayList<>();
    private static List<Static> statics = new ArrayList<>();

    //Other
    private long programStart;

    @Override
    public void start(Stage stage) throws Exception
    {
        programStart = System.currentTimeMillis();
        TextArea ta = new TextArea();
        /*
        //Show custom console
        Console console = new Console(ta);
        PrintStream ps = new PrintStream(console, true);
        System.setOut(ps);
        System.setErr(ps);

        */
        ta.setEditable(false);
        ta.setFocusTraversable(false);


        // Create a new grid pane
        ScrollPane pane = new ScrollPane();
        pane.setPadding(new Insets(0,2,0,2));

        inventoryContent = new Label("Inventory: ");
        inventoryContent.setWrapText(true);
        inventoryContent.setPadding(new Insets(2,2,2,2));

        VBox handbox = new VBox(inventoryContent);
        pane.setContent(handbox);

        player1 = new Player("Micheal", 200, 100,20,20);
        player2 = new Player("James", 50, 50,20,20);
        player3 = new Player("Jin", 150, 50,20,20);

        players.add(player1);
        players.add(player2);
        players.add(player3);

        playerSelection = new ComboBox<>();
        playerSelection.setFocusTraversable(false);
        for(Player p : players){
            playerSelection.getItems().add(p);
        }

        Button cv = new Button("Canvas");
        playerSelection.setPrefWidth(130);
        cv.setPrefWidth(70);
        HBox hb1 = new HBox(playerSelection,cv);
        pane.setPrefHeight(400);
        VBox vb = new VBox(hb1,handbox,pane,ta);
        vb.setFocusTraversable(false);

        canvas = new Canvas(250,250);
        canvas.setFocusTraversable(false);
        gc = canvas.getGraphicsContext2D();

        Group root = new Group();
        root.setAutoSizeChildren(true);
        root.getChildren().add(canvas);

        root.prefHeight(20);
        root.prefWidth(200);

        vb.setMaxWidth(400);
        HBox hb = new HBox(root,vb);
        hb.setPadding(new Insets(2,2,2,2));
        hb.setPrefWidth(Double.MAX_VALUE);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(hb, 600,600);
        stage.setTitle("Meteor Remastered");
        stage.setScene(scene);

        // Show the Stage (window)
        stage.show();
        addEventsToScene(scene);
        addEventsToCanvas(canvas);

        AnimationTimer timer = new AnimationTimer()
        {
            @Override
            public void handle(long now) {
                if(currentPlayer != null) {
                    int dx = 0,dy = 0;
                    if (goNorth) dy -= currentPlayer.getWalkSpeed();
                    if (goSouth) dy += currentPlayer.getWalkSpeed();
                    if (goEast)  dx += currentPlayer.getWalkSpeed();
                    if (goWest)  dx -= currentPlayer.getWalkSpeed();
                    if (running && currentPlayer.stamina > 0 && penalty <= 0) {
                        dx *= currentPlayer.getRunningSpeed();
                        dy *= currentPlayer.getRunningSpeed();
                        if(goNorth||goEast||goSouth||goWest){
                            currentPlayer.stamina -= (currentPlayer.fatigueRate * currentPlayer.fatigueMult);
                        }
                    }
                    if(currentPlayer.stamina <= 0){
                        penalty = currentPlayer.recoverRate;
                    }
                    if(penalty > 0 || currentPlayer.stamina < currentPlayer.maxStamina){
                        currentPlayer.stamina += currentPlayer.restitutionRate;
                    }
                    currentPlayer.x += dx; currentPlayer.y += dy;

                    if(penalty > 0)penalty--;

                }
                draw();
            }
        }
        ;
        timer.start();
        canvas.setWidth(400);
        canvas.setHeight(600);

        World.init(players,items, statics);
    }

    private Image img = new Image("View/background.png");
    public static long frameCount = 0;
    private void draw(){
        frameCount++;
        //Clear rendering
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());

        //Draw background
        gc.save();
        drawBackground();

        drawPlayers();

        drawStatics();

        drawItems();

        if(currentPlayer != null) {
            if (priMouseIsPressed) {
                currentPlayer.action(Hand.LEFT);
            }
            if (secMouseIsPressed){
                currentPlayer.action(Hand.RIGHT);
            }
        }

        drawProjectiles();

        gc.restore();

        drawCurrentPlayer();

        drawCrosshair();

        //Update UI-information-text
        updateText();
    }

    private void drawBackground(){
        if(currentPlayer != null){
            gc.translate(canvas.getWidth()/2-currentPlayer.x,canvas.getHeight()/2-currentPlayer.y);
        }
        gc.drawImage(img,0,0);
    }

    private void drawPlayers() {
        if(currentPlayer != null) {
            for(Player p : players) {
                if (p != currentPlayer) {
                    gc = p.update(gc, canvas);
                }
            }
        }
    }

    private void drawStatics() {
        for(Static s: statics){
            gc = s.update(gc);
        }
    }

    private void drawItems() {
        for(Item i : items){
            gc = i.update(gc);
        }
    }

    private void drawProjectiles() {
        for(int i = 0; i < projectiles.size(); i++){
            if(projectiles.get(i).position.x < 0 || projectiles.get(i).position.y < 0 || projectiles.get(i).position.x > img.getWidth() || projectiles.get(i).position.y > img.getHeight()) {
                projectiles.remove(i);
            } else {
                gc = projectiles.get(i).update(gc);
            }
        }
    }

    private void drawCurrentPlayer() {
        if(currentPlayer != null) {
            gc = currentPlayer.update(gc, canvas);
        }
    }

    private void drawCrosshair() {
        gc.strokeOval(mouseX-10,mouseY-10,20,20);
        if(secMouseIsPressed){
            gc.strokeLine(mouseX-10,mouseY,mouseX+10,mouseY);
            gc.strokeLine(mouseX,mouseY-10,mouseX,mouseY+10);
        }
    }

    private void addEventsToCanvas(Canvas c){
        c.setCursor(Cursor.NONE);
        c.setOnMouseMoved(event -> {
            mouseX = event.getX();
            mouseY = event.getY();
        }
        );

        c.setOnMouseDragged(mE -> {
            if (mE.isPrimaryButtonDown())   {priMouseIsPressed = true;}
            if (mE.isSecondaryButtonDown()) {
                secMouseIsPressed = true;
                c.setCursor(Cursor.CROSSHAIR);
            }
            mouseX = mE.getX();
            mouseY = mE.getY();
        }
        );

        c.addEventFilter(MouseEvent.MOUSE_PRESSED, mE -> {
            System.out.println(mE.getButton() + " CLICK ON "
                    + mE.getSource()
                    + " AT: " + mE.getX() + " " + mE.getY());
            if (mE.isPrimaryButtonDown())   {priMouseIsPressed = true;}
            if (mE.isSecondaryButtonDown()) {secMouseIsPressed = true;}
            mouseX = mE.getX();
            mouseY = mE.getY();
        }
        );

        c.addEventFilter(MouseEvent.MOUSE_RELEASED, mE -> {
            c.setCursor(Cursor.NONE);
            priMouseIsPressed = false;
            secMouseIsPressed = false;
        }
        );

    }

    private void addEventsToScene(Scene s){
        s.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            System.out.println("Width: " + newSceneWidth);
            canvas.setWidth((double)newSceneWidth-200);
        }
        );
        s.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            System.out.println("Height: " + newSceneHeight);
            canvas.setHeight((double)newSceneHeight);
        }
        );

        s.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:    goNorth = true; break;
                case W:     goNorth = true; break;
                case DOWN:  goSouth = true; break;
                case S:     goSouth = true; break;
                case LEFT:  goWest  = true; break;
                case A:     goWest  = true; break;
                case RIGHT: goEast  = true; break;
                case D:     goEast  = true; break;
                case SHIFT: running = true; break;
                case E: currentPlayer.pickupItem(items);break;
                case PAGE_UP: currentPlayer.moveSelector("UP"); break;
                case PAGE_DOWN: currentPlayer.moveSelector("DOWN"); break;
                case DELETE: currentPlayer.dropItem(currentPlayer.getSelectorIndex());break;
                case HOME: currentPlayer.equip(currentPlayer.getItem(currentPlayer.getSelectorIndex()),Hand.LEFT);break;
                case END:  currentPlayer.equip(currentPlayer.getItem(currentPlayer.getSelectorIndex()),Hand.RIGHT);break;
                //case ENTER: currentPlayer.showOptions();break;
                case ENTER: currentPlayer.action(currentPlayer.getSelectorIndex());break;
                case BACK_SPACE:break;
            }
        }
        );

        s.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:    goNorth = false; break;
                case W:     goNorth = false; break;
                case DOWN:  goSouth = false; break;
                case S:     goSouth = false; break;
                case LEFT:  goWest  = false; break;
                case A:     goWest  = false; break;
                case RIGHT: goEast  = false; break;
                case D:     goEast  = false; break;
                case SHIFT: running = false; break;
            }
        }
        );

        playerSelection.valueProperty().addListener(
                (ObservableValue<? extends Player> observable, Player oldValue, Player newValue) ->
                {
                    currentPlayer = newValue;
                    if(oldValue != null){
                        oldValue.setControlled(false);
                    }
                    newValue.setControlled(true);
                    UI.sout(currentPlayer + " is selected");
        });

    }

    public static List<Projectile> getProjectiles(){
        return projectiles;
    }
    public static List<Item> getItems(){return items;}

    private void updateText(){
        if (currentPlayer != null){
            String s = currentPlayer.getPlayerInfo();
            inventoryContent.setText(s);
        }
    }

    private static void sout(String input){
        System.out.println("UI: " + input);
    }

    public static double map(double n, double start1, double stop1, double start2, double stop2){
        double newVal = (n - start1) / (stop1 - start1) * (stop2 - start2) + start2;
        if (start2 < stop2) {
            return constrain(newVal, start2, stop2);
        } else {
            return constrain(newVal, stop2, start2);
        }
    }

    public static double constrain(double n, double low, double high){
        return Math.max(Math.min(n, high), low);
    }

    public static class Console extends OutputStream {
        private TextArea output;
        public Console(TextArea ta) {
            this.output = ta;
        }

        @Override
        public void write(int i) throws IOException {
            output.appendText(String.valueOf((char) i));
        }
    }

    public static void sout(Player p,String s){
        boolean isDebugging = true;
        if (isDebugging) System.out.println(p + " : " + s);
    }

    public long millis(){
        return System.currentTimeMillis() - programStart;
    }
}
