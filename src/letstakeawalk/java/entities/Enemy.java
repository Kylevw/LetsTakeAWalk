/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstakeawalk.java.entities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import path.TrigonometryCalculator;
import static letstakeawalk.java.main.EntityManager.player;
import letstakeawalk.java.resources.AudioPlayerIntf;
import letstakeawalk.java.resources.ImageProviderIntf;
import timer.DurationTimer;

/**
 *
 * @author Kyle
 */
public class Enemy extends Entity {
    
    private final int sightDistance, attackDistance;
    private final DurationTimer attackTimer;
    private final DurationTimer invulTimer;
    public static final int INVUL_TIME = 120;
    
    private final int maxHealth, strength, defense;
    private int health;

    public Enemy(BufferedImage image, Point position, Dimension size, ImageProviderIntf ip, AudioPlayerIntf ap, String imageListName, int animationSpeed, int maxHealth, int strength, int defense, int sightDistance, int attackDistance, int attackDelay) {
        super(image, position, size, ip, ap, imageListName, animationSpeed);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.strength = strength;
        this.defense = defense;
        this.sightDistance = sightDistance;
        this.attackDistance = attackDistance;
        attackTimer = new DurationTimer(attackDelay);
        invulTimer = new DurationTimer(INVUL_TIME);
    }
    
    @Override
    public void timerTaskHandler() {
        
        if (health <= 0) {
            setDespawn(true);
        }
        
        if (!attackTimer.isComplete()) attackAI();
        else if (player != null) {
            if (getPlayerDistance() <= getAttackDistance() && attackTimer.isComplete()) {
                attackTimer.start();
                startAttackAI();
            } else if (getPlayerDistance() <= getSightDistance() && attackTimer.isComplete()) targetAI();
            else if (attackTimer.isComplete()) standardAI();
        } else standardAI();
        
        if (player != null && intersects(player)) {
            player.damage(strength);
        }
        
        super.timerTaskHandler();
        
        if (!invulTimer.isComplete()) {
            setImage(getImageProvider().getTintedImage(getAnimator().getCurrentImage(), new Color(255, 0, 0, 100)));
        }
                
    }
    
    public double getPlayerDistance() {
       if (player != null) return TrigonometryCalculator.getHypotenuse(getPosition().x, getPosition().y, player.getPosition().x, player.getPosition().y);
       else return Integer.MAX_VALUE;
    }
    
    public void standardAI() {
        
    }
    
    public void targetAI() {
        
    }
    
    public void startAttackAI() {
        
    }
    
    public void attackAI() {
        
    }
    
    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.BLUE);
        if (drawBoundary()) graphics.drawOval(getPosition().x - sightDistance, getPosition().y - sightDistance, sightDistance * 2, sightDistance * 2);
        graphics.setColor(Color.RED);
        if (drawBoundary()) graphics.drawOval(getPosition().x - attackDistance, getPosition().y - attackDistance, attackDistance * 2, attackDistance * 2);
        super.draw(graphics);
    }
    
    @Override
    public void accelerateKnockbackVelocity(int x, int y) {
        if (invulTimer.isComplete()) super.accelerateKnockbackVelocity(x, y);
    }
    
    public void damage(int damage) {
        if (invulTimer.isComplete()) {
            int damageFactor = damage - defense;
            if (damageFactor <= 0 && damage > 0) damageFactor = 1;
            health -= damageFactor;
            invulTimer.start();
        }
    }
    
    public int getAttackDistance() {
        return attackDistance;
    }
    
    public int getSightDistance() {
        return sightDistance;
    }
    
    public int getStrength() {
        return strength;
    }
    
    public int getHealth() {
        if (health < maxHealth) return health;
        else return maxHealth;
    }
    
    public int getMaxHealth() {
        return maxHealth;
    }
}
