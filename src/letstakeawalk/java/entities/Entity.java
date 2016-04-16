/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstakeawalk.java.entities;

import letstakeawalk.java.resources.AudioPlayerIntf;
import environment.Actor;
import environment.Physics;
import environment.Velocity;
import images.Animator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static letstakeawalk.java.main.EntityManager.explosions;
import letstakeawalk.java.resources.ImageProviderIntf;
import letstakeawalk.java.resources.LTAWImageManager;

/**
 *
 * @author Kyle
 */
public class Entity extends Actor{
    
    {
        drawBoundary = false;
    }
    
    private boolean onGround;
    private double weight;
    
    private boolean explode;
    private int explosionSize;
    
    private boolean despawn;
    
    private final Animator animator;
    
    private final Dimension size;
    private boolean drawBoundary;
    private double xKnockback, yKnockback;
    private boolean applyGravity;
    
    private final ImageProviderIntf ip;
    private final AudioPlayerIntf ap;
    
    public Entity(BufferedImage image, Point position, Dimension size, ImageProviderIntf ip, AudioPlayerIntf ap, String imageListName, int animationSpeed) {
        super(image, position, new Velocity(0, 0));
        this.ap = ap;
        this.size = size;
        this.ip = ip;
        this.weight = weight;
        if (this.weight < 0) this.weight = 0;
        LTAWImageManager im = new LTAWImageManager();
        this.animator = new Animator(im, ip.getImageList(imageListName), animationSpeed);
        applyGravity = true;
    }
    
    public void accelerateKnockbackVelocity(int x, int y) {
        xKnockback += x;
        yKnockback += y;
    }
    
    public void applyGravity(boolean applyGravity) {
        this.applyGravity = applyGravity;
    }
    
    public void accelerateKnockbackVelocity(Velocity velocity) {
        accelerateKnockbackVelocity(velocity.x, velocity.y);
    }
    
    public Dimension getSize() {
        return size;
    }
    
    public void timerTaskHandler() {
        updateImage();
        
        setPosition(getPosition().x + (int) xKnockback, getPosition().y + (int) yKnockback);
        if (xKnockback != 0) xKnockback -= weight * xKnockback / Math.abs(xKnockback) / 8;
        if (yKnockback != 0) yKnockback -= weight * yKnockback / Math.abs(yKnockback) / 8;
        
        if (explode) {
            explosions.add(new Explosion(getPosition(), explosionSize, ap));
            setDespawn(true);
        }
    }
    
    @Override
    public void draw(Graphics2D graphics) {
        graphics.drawImage(ip.getImage(null), getShadowRectangle().x, getShadowRectangle().y, getShadowRectangle().width, getShadowRectangle().height, null);
                //TODO Replace "null" with entity shadow image
        
        graphics.drawImage(getImage(), getPosition().x - (size.width / 2), getPosition().y - (size.height), size.width, size.height, null);
        if (drawBoundary) {
            graphics.setColor(Color.BLUE);
            graphics.draw(getObjectBoundary());
        }
    }
    
    @Override
    public Rectangle getObjectBoundary() {
        return new Rectangle(getPosition().x - (size.width / 2),
        getPosition().y - size.height,
        size.width, size.height);
    }
    
    public void drawObjectBoundary(boolean drawBoundary) {
        this.drawBoundary = drawBoundary;
    }
    
    public void explode(int explosionSize) {
        this.explosionSize = explosionSize;
        explode = true;
    }
    
    
    public Rectangle getShadowRectangle() {
        int shadowWidth = getObjectBoundary().width - ((1 + (getObjectBoundary().width / 6)) * 2);
        if (shadowWidth < 0) shadowWidth = 0;
        return new Rectangle(getObjectBoundary().x + (1 + (getObjectBoundary().width / 6)), getObjectBoundary().y + (1 + (getObjectBoundary().width / 6)) + (getObjectBoundary().height / 2), shadowWidth, shadowWidth);
    }
    
    public boolean drawBoundary() {
        return drawBoundary;
    }
    
    public boolean intersects(Entity entity) {
        return getObjectBoundary().intersects(entity.getObjectBoundary());
    }
    
    public void setImage(String image) {
        super.setImage(ip.getImage(image));
    }
    
    public Animator getAnimator() {
        return animator;
    }
    
    public ImageProviderIntf getImageProvider() {
        return ip;
    }
    
    public AudioPlayerIntf getAudioPlayer() {
        return ap;
    }
    
    public void setDespawn(boolean despawn) {
        this.despawn = despawn;
    }
    
    public boolean despawn() {
        return despawn;
    }
    
    private void updateImage() {
        if (animator != null) setImage(animator.getCurrentImage());
    }
    
    public void setImageList(String listName) {
        animator.setImageNames(getImageProvider().getImageList(listName));
    }
    
}
