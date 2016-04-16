/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstakeawalk.java.entities;

import java.awt.Dimension;
import java.awt.Point;
import static letstakeawalk.java.main.EntityManager.player;
import letstakeawalk.java.resources.AudioPlayerIntf;
import letstakeawalk.java.resources.ImageProviderIntf;

/**
 *
 * @author Kyle
 */
public class Heart extends Consumable {
    
    {
//        drawObjectBoundary(true);
    }
    
    private static final int ANIMATION_SPEED = 160;
    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;

    public Heart(Point position, ImageProviderIntf ip, AudioPlayerIntf ap) {
        super(ip.getImage(null), position, new Dimension(WIDTH, HEIGHT), ip, ap, null, ANIMATION_SPEED);
        //TODO Heart image + animation list
    }
    
    @Override
    public void pickUpEvent() {
        if (player != null) player.heal(2);
    }
    
}
