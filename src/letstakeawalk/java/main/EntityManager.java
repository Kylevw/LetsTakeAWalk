/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstakeawalk.java.main;

import java.util.ArrayList;
import letstakeawalk.java.entities.Consumable;
import letstakeawalk.java.entities.Enemy;
import letstakeawalk.java.entities.Entity;
import letstakeawalk.java.entities.Explosion;
import letstakeawalk.java.entities.Player;
import letstakeawalk.java.entities.PrimedBomb;

/**
 *
 * @author Kyle
 */
public class EntityManager {
    public static ArrayList<Entity> entities = new ArrayList<>();
    
    public static Player player;
    public static ArrayList<Enemy> enemies = new ArrayList<>();

    public static ArrayList<Consumable> consumables = new ArrayList<>();
    public static ArrayList<PrimedBomb> bombs = new ArrayList<>();


    public static ArrayList<Explosion> explosions = new ArrayList<>();
    
    public static ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    
    public static ArrayList<Entity> getEntities() {
        if (player == null) entities.remove(player);
        return entities;
    }
    
    public static ArrayList<Consumable> getConsumables() {
        return consumables;
    }
    
    public static ArrayList<PrimedBomb> getBombs() {
        return bombs;
    }
    
    public static ArrayList<Explosion> getExplosions() {
        return explosions;
    }
    
}
