/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstakeawalk.java.entities;

import java.awt.Dimension;
import java.awt.Point;
import letstakeawalk.java.resources.AudioPlayerIntf;
import letstakeawalk.java.resources.ImageProviderIntf;
import letstakeawalk.java.resources.LTAWImageManager;
import timer.DurationTimer;

/**
 *
 * @author Kyle
 */
public class PrimedBomb extends Entity{
    
    private final DurationTimer bombTimer;
    
    private static final int ANIMATION_SPEED = 80;
    private static final int WIDTH = 7;
    private static final int HEIGHT = 8;
    private static final int FUSE_DELAY = 1000;

    public PrimedBomb(Point position, ImageProviderIntf ip, AudioPlayerIntf ap) {
        super(ip.getImage(null), position, new Dimension(WIDTH, HEIGHT), ip, ap, null, ANIMATION_SPEED);
        //TODO Add bomb image + list
        
        bombTimer = new DurationTimer(FUSE_DELAY);
    }
    
    @Override
    public void timerTaskHandler() {
        move();
        
        super.timerTaskHandler();
        
        if (bombTimer.isComplete()) {
            explode(2);
        }
        
    }
}
