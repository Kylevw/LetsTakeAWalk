/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstakeawalk.java.entities;

import environment.Velocity;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import static letstakeawalk.java.main.EntityManager.player;
import letstakeawalk.java.resources.AudioPlayerIntf;
import letstakeawalk.java.resources.ImageProviderIntf;

/**
 *
 * @author Kyle
 */
public class Consumable extends Entity{

    public Consumable(BufferedImage image, Point position, Dimension size, ImageProviderIntf ip, AudioPlayerIntf ap, String imageNameList, int animationSpeed) {
        super(image, position, size, ip, ap, imageNameList, animationSpeed);
    }
    
    @Override
    public void timerTaskHandler() {
        
        if (player != null && !despawn() && intersects(player)) {
            playerPickUp();
        }
        
        
        super.timerTaskHandler();
        
    }
    
    private void playerPickUp() {
        setDespawn(true);
        player.displayItem((BufferedImage) getAnimator().getCurrentImage());
        pickUpEvent();
    }
    
    public void pickUpEvent() {
        
    }
    
}
