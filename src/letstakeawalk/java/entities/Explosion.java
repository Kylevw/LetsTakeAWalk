/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstakeawalk.java.entities;

import images.Animator;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import path.TrigonometryCalculator;
import letstakeawalk.java.main.EntityManager;
import static letstakeawalk.java.main.EntityManager.bombs;
import static letstakeawalk.java.main.EntityManager.consumables;
import static letstakeawalk.java.main.EntityManager.enemies;
import static letstakeawalk.java.main.EntityManager.player;
import letstakeawalk.java.resources.AudioManager;
import letstakeawalk.java.resources.AudioPlayerIntf;
import letstakeawalk.java.resources.LTAWImageManager;
import timer.DurationTimer;

/**
 *
 * @author Kyle
 */
public class Explosion {
    
    private final Point position;
    private final Animator animator;
    private final int size;
    
    private final DurationTimer despawnTimer;
    
    private BufferedImage currentImage;
    
    private final AudioPlayerIntf ap;
    
    private static final int ANIMATION_SPEED = 80;
    private static final int RADIUS = 27;
    private static final int MAX_DAMAGE = 5;
    
    private boolean dealtDamage;
    
    public Explosion(Point position, int size, AudioPlayerIntf ap) {
        this.position = position;
        LTAWImageManager im = new LTAWImageManager();
        this.animator = new Animator(im, im.getImageList(null), ANIMATION_SPEED);
        //TODO Animation list for explosions
        
        this.size = size;
        despawnTimer = new DurationTimer(640);
        this.ap = ap;
    }
    
    public void draw(Graphics2D graphics) {
        graphics.drawImage(currentImage, position.x - (size * RADIUS / 2), position.y - (size * RADIUS / 2), size * RADIUS, size * RADIUS, null);
    }
    
    public void timerTaskHandler() {
        currentImage = (BufferedImage) animator.getCurrentImage();
        if (!dealtDamage) {
//            ap.playAudio(AudioManager.EXPLOSION, false);
            //TODO Factor in audio sound EXPLOSION
            
            dealtDamage = true;
            if (player != null) {
                double playerDistance = TrigonometryCalculator.getHypotenuse(position.x, position.y, player.getPosition().x, player.getPosition().y);
                if (playerDistance <= size * RADIUS / 2) player.damage(size);
            }
            if (enemies != null) {
                EntityManager.getEnemies().stream().forEach((enemy) -> {
                double enemyDistance = TrigonometryCalculator.getHypotenuse(position.x, position.y, enemy.getPosition().x, enemy.getPosition().y);
                    if (enemyDistance <= size * RADIUS / 2) {
                        enemy.accelerateKnockbackVelocity(TrigonometryCalculator.calculateVelocity(position, enemy.getPosition(), ((RADIUS * size) - enemyDistance) * 5 / RADIUS));
                        enemy.damage((int) ((size * RADIUS - enemyDistance - 1) * MAX_DAMAGE / RADIUS) + 1);
                    }
                });
            }
            if (consumables != null) {
                EntityManager.getConsumables().stream().forEach((consumable) -> {
                    double consumableDistance = TrigonometryCalculator.getHypotenuse(position.x, position.y, consumable.getPosition().x, consumable.getPosition().y);
                    if (consumableDistance <= size * RADIUS / 2) {
                        if (consumable instanceof Bomb) consumable.explode(2);
                        else consumable.setDespawn(true);
                    }
                });
            }
            if (bombs != null) {
                EntityManager.getBombs().stream().forEach((bomb) -> {
                    double consumableDistance = TrigonometryCalculator.getHypotenuse(position.x, position.y, bomb.getPosition().x, bomb.getPosition().y);
                    if (consumableDistance <= size * RADIUS / 2) {
                        bomb.explode(2);
                    }
                });
            }
        }
    }
    
    public boolean despawn() {
        return despawnTimer.isComplete();
    }
    
}
