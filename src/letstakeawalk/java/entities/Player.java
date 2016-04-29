/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstakeawalk.java.entities;

import java.awt.Dimension;
import letstakeawalk.java.main.ActionState;
import letstakeawalk.java.main.Direction;
import letstakeawalk.java.resources.LTAWImageManager;
import letstakeawalk.java.resources.ImageProviderIntf;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import static letstakeawalk.java.main.EntityManager.bombs;
import letstakeawalk.java.resources.AudioPlayerIntf;
import timer.DurationTimer;

/**
 *
 * @author Kyle van Wiltenburg
 */
public class Player extends Entity {
    
    private static final int PLAYER_WIDTH = 13;
    private static final int PLAYER_HEIGHT = 30;
    private int health, maxHealth, bombCount;
    
    private final DurationTimer invulTimer;
    private final DurationTimer healthTimer;
    private final DurationTimer healthMeterBlinkTimer;
    private final DurationTimer itemDisplayTimer;
    
    private BufferedImage displayItemImage;
    
    {
        actionState = ActionState.IDLE;
        facing = Direction.DOWN;
//        drawObjectBoundary(true);
    }
    
    private final ArrayList<Direction> directions;
    private Direction facingDebug;
    private Direction facing;
    private ActionState actionState;
    private ActionState actionStateDebug;
    
    private static final int ANIMATION_SPEED = 200;
    
    public static final int BOW_CHARGE_TIME = 30;
    
    private boolean sprinting;
    
    
    /**
     * Constructor, returns an instance of the Player class
     *
     * @param position the current position of the entity on screen
     * @param screenLimiter inputs the minimum and maximum positions for the camera
     * @param ip the LTAWImageManager for the entity
     * @param ap the AudioManager for the entity
     * 
     */
    
    public Player(Point position, ImageProviderIntf ip, AudioPlayerIntf ap) {

        super(ip.getImage(LTAWImageManager.WOMAN_IDLE_DOWN_00), position, new Dimension(PLAYER_WIDTH, PLAYER_HEIGHT), ip, ap, LTAWImageManager.WOMAN_IDLE_DOWN_LIST, ANIMATION_SPEED);
        this.directions = new ArrayList<>();
        maxHealth = 6;
        health = maxHealth;
        
        invulTimer = new DurationTimer(1200);
        healthTimer = new DurationTimer(1600);
        healthMeterBlinkTimer = new DurationTimer(200);
        itemDisplayTimer = new DurationTimer(600);
    }
    
    @Override
    public void draw(Graphics2D graphics) {
        if (displayItemImage != null) graphics.drawImage(displayItemImage, null, getPosition().x - ((displayItemImage.getWidth() + 1) / 2), getPosition().y - PLAYER_WIDTH - displayItemImage.getHeight() - 1);
        if (invulTimer.getRemainingDurationMillis() / 80 % 2 == 0) super.draw(graphics);
    }
    
    @Override
    public void timerTaskHandler() {
        
        
//        System.out.println("Position: [" + getPosition().x + "," + getPosition().y + "]");
        
        if (displayItemImage != null && itemDisplayTimer.isComplete()) displayItemImage = null;
        
        if (health <= 0) {
            setDespawn(true);
        }
        
        if (healthTimer.isComplete()) healthTimer.start();
        
        if (maxHealth % 2 > 0) maxHealth++;
        if (health > maxHealth) health = maxHealth;
        
        updateActionState();
        
        
        updateVelocity();
        updateFacingDirection();

        move();
        
        
//        System.out.println(getAnimator().getCurrentImageName());
        
        if (actionStateDebug != actionState || facingDebug != facing) {
            updateAnimator();
        }
        
        actionStateDebug = actionState;
        facingDebug = facing;
        
        // Updates the player's position in the world
        
        super.timerTaskHandler();
        
    }
    
    private void updateAnimator() {
        
        switch (actionState) {
            
            case IDLE:
                switch (facing) {
                    case UP: 
                        setImageList(LTAWImageManager.WOMAN_IDLE_UP_LIST);
                        break;
                    case DOWN:
                        setImageList(LTAWImageManager.WOMAN_IDLE_DOWN_LIST);
                        break;
                        case LEFT:
                        setImageList(LTAWImageManager.WOMAN_IDLE_LEFT_LIST);
                        break;
                    case RIGHT:
                        setImageList(LTAWImageManager.WOMAN_IDLE_RIGHT_LIST);
                        break;
                }
            break;
                
            case WALKING:
                switch (facing) {
                    case UP: 
                        setImageList(LTAWImageManager.WOMAN_WALK_UP_LIST);
                        break;
                    case DOWN: 
                        setImageList(LTAWImageManager.WOMAN_WALK_DOWN_LIST);
                        break;
                    case LEFT: 
                        setImageList(LTAWImageManager.WOMAN_WALK_LEFT_LIST);
                        break;
                    case RIGHT: 
                        setImageList(LTAWImageManager.WOMAN_WALK_RIGHT_LIST);
                        break;
                }
            break;
        }
    }
    
    private void updateVelocity() {
        //Cammy made changes, all twos were origionally threes
        // If the player's Directions list contains a certain direction, apply that direction to the velocity
        setVelocity(0, 0);
        if (directions.contains(Direction.UP)) accelerate(0, -1);
        if (directions.contains(Direction.DOWN)) accelerate(0, 1);
        if (directions.contains(Direction.LEFT)) accelerate(-1, 0);
        if (directions.contains(Direction.RIGHT)) accelerate(1, 0);
        
    }
    
    private void updateActionState() {
        if (getVelocity().x != 0 || getVelocity().y != 0) actionState = ActionState.WALKING;
        else actionState = ActionState.IDLE;
    }
    
    @Override
    public Rectangle getObjectBoundary() {
        return new Rectangle(getPosition().x - (getSize().width / 2) + 3,
        getPosition().y - (getSize().height) + 2,
        getSize().width - 6, getSize().height - 4);
    }
    
    private void updateFacingDirection() {
        if (directions != null && !directions.isEmpty()) facing = directions.get(directions.size() - 1);
        switch (facing) {
            case UP:
                if (getVelocity().y >= 0) {
                    if (getVelocity().x < 0) facing = Direction.LEFT;
                    else if (getVelocity().x > 0) facing = Direction.RIGHT;
                }
                break;
            case DOWN:
                if (getVelocity().y <= 0) {
                    if (getVelocity().x < 0) facing = Direction.LEFT;
                    else if (getVelocity().x > 0) facing = Direction.RIGHT;
                }
                break;
            case LEFT:
                if (getVelocity().x >= 0) {
                    if (getVelocity().y < 0) facing = Direction.UP;
                    else if (getVelocity().y > 0) facing = Direction.DOWN;
                }
                break;
            case RIGHT:
                if (getVelocity().x <= 0) {
                    if (getVelocity().y < 0) facing = Direction.UP;
                    else if (getVelocity().y > 0) facing = Direction.DOWN;
                }
                break;
                
        }
    }
    
    public void damage(int damage) {
        if (invulTimer.isComplete()) {
            health -= damage;
            invulTimer.start();
        }
    }
    
    public int getHealth() {
        if (health < maxHealth) return health;
        else return maxHealth;
    }
    
    public int getMaxHealth() {
        return maxHealth;
    }
    
    public ArrayList<Direction> getDirections() {
        return directions;
    }
    
    public ActionState getActionState() {
        return actionState;
    }
    
    public void addDirection(Direction direction) {
        directions.add(direction);
    }
    
    public void removeDirection(Direction direction) {
        directions.remove(direction);
    }
    
    public boolean healthBlip() {
        return health <= 2 && healthTimer.getRemainingDurationMillis() <= 100 || !healthMeterBlinkTimer.isComplete();
    }
    
    public void heal(int health) {
        this.health += health;
        healthMeterBlinkTimer.start();
    }
    
    public void displayItem(BufferedImage displayItemImage) {
        this.displayItemImage = displayItemImage;
        itemDisplayTimer.start();
    }
    
    public void addBombs(int bombs) {
        bombCount += bombs;
    }
    
    public void useBomb() {
        if (bombCount > 0) {
            bombs.add(new PrimedBomb(new Point(getPosition().x, getPosition().y), getImageProvider(), getAudioPlayer()));
            bombCount--;
        }
    }
    
    public int getBombCount() {
        return bombCount;
    }

    /**
     * @return the sprinting
     */
    public boolean isSprinting() {
        return sprinting;
    }

    /**
     * @param sprinting the sprinting to set
     */
    public void setSprinting(boolean sprinting) {
        this.sprinting = sprinting;
    }
}
