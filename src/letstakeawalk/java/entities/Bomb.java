/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstakeawalk.java.entities;

import environment.Velocity;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import static letstakeawalk.java.main.EntityManager.player;
import letstakeawalk.java.resources.AudioPlayerIntf;
import letstakeawalk.java.resources.ImageProviderIntf;
import letstakeawalk.java.resources.LTAWImageManager;

/**
 *
 * @author Kyle
 */
public class Bomb extends Consumable {
    
    {
//        drawObjectBoundary(true);
    }
    
    private static final double WEIGHT = 3;
    private static final int ANIMATION_SPEED = 80;
    private static final int WIDTH = 5;
    private static final int HEIGHT = 7;

    
    public Bomb(Point position, int zDisplacement, Velocity velocity, double zVelocity, ImageProviderIntf ip, AudioPlayerIntf ap) {
        super(ip.getImage(null), position, new Dimension(WIDTH, HEIGHT), ip, ap, null, ANIMATION_SPEED);
        //TODO Bomb image + velocity
        
    }
    
    @Override
    public void timerTaskHandler() {
        move();
        super.timerTaskHandler();
    }
    
    @Override
    public Rectangle getObjectBoundary() {
        return new Rectangle(getPosition().x - (getSize().width / 2),
        getPosition().y - getSize().height + 2,
        getSize().width, getSize().height - 2);
    }
    
    @Override
    public void pickUpEvent() {
        if (player != null) player.addBombs(1);
    }
    
}
