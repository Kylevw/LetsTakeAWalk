package letstakeawalk.java.main;

import images.ResourceTools;
import java.awt.Dimension;
import java.awt.Point;
import map.Map;
import map.ObstacleType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Katherine
 */
public class MapFactory {

    public static final String CAMPUS_MAP = "CAMPUS";
    public static final String FOREST_MAP = "FOREST";

    public static Map getMap(String name) {
        switch (name) {
            default:
            case CAMPUS_MAP:
                return getCampusMap();

            case FOREST_MAP:
                return getForestMap();
        }
    }

    public static Map getCampusMap() {
        Map campus = new Map(ResourceTools.loadImageFromResource("letstakeawalk/resources/images/maps/campus.png"), new Dimension(16, 16), new Dimension(20, 50));

        Map.addObstacle(campus, new Point(45, 6), ObstacleType.BARRIER);
        Map.addObstacle(campus, new Point(6, 18), ObstacleType.BARRIER);
        Map.addObstacle(campus, new Point(18, 16), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(0, 4), new Point(5, 5), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(4, 0), new Point(5, 3), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(5, 16), new Point(8, 16), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(2, 18), new Point(3, 18), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(36, 6), new Point(36, 9), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(1, 19), new Point(2, 19), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(4, 19), new Point(6, 19), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(8, 15), new Point(10, 15), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(0, 14), new Point(13, 14), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(3, 17), new Point(15, 17), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(15, 14), new Point(17, 16), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(16, 12), new Point(21, 13), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(18, 15), new Point(23, 15), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(23, 14), new Point(27, 14), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(20, 11), new Point(29, 11), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(24, 10), new Point(29, 10), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(22, 12), new Point(25, 12), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(27, 13), new Point(29, 13), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(32, 13), new Point(34, 13), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(34, 14), new Point(39, 14), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(39, 15), new Point(49, 15), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(32, 10), new Point(36, 11), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(37, 11), new Point(41, 11), ObstacleType.BARRIER);
        Map.addObstacleRange(campus, new Point(44, 6), new Point(44, 11), ObstacleType.BARRIER);
        

        
        
        
        Map.addObstacle(campus, new Point(10, 10), ObstacleType.WATER);
        Map.addObstacleRange(campus, new Point(7, 18), new Point(16, 18), ObstacleType.WATER);
        
        Map.addPortal(campus, new Point(13, 0), FOREST_MAP, new Point(0, 0));

        return campus;
    }

    static Map getForestMap() {
        Map forest = new Map(ResourceTools.loadImageFromResource("letstakeawalk/resources/images/maps/forest.png"), new Dimension(16, 16), new Dimension(44, 48));

        Map.addObstacle(forest, new Point(10, 10), ObstacleType.WATER);
        Map.addObstacleRange(forest, new Point(0, 0), new Point(2, 5), ObstacleType.BARRIER);
        Map.addObstacleRange(forest, new Point(7, 18), new Point(16, 18), ObstacleType.WATER);
        
        Map.addPortal(forest, new Point(15, 0), CAMPUS_MAP, new Point(12, 0));

        return forest;
    }
}
