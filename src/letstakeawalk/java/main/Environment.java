/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstakeawalk.java.main;

import letstakeawalk.java.resources.GameState;
import letstakeawalk.java.resources.LTAWImageManager;
import letstakeawalk.java.entities.Player;
import letstakeawalk.java.entities.Entity;
import grid.Grid;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import letstakeawalk.java.resources.AudioPlayerIntf;
import map.Item;
import map.ItemEventHandlerIntf;
import map.Map;
import map.MapVisualizerDefault;
import map.Obstacle;
import map.ObstacleEventHandlerIntf;
import map.Portal;
import map.PortalEventHandlerIntf;
import path.TrigonometryCalculator;

/**
 *
 * @author Kyle van Wiltenburg
 */
class Environment extends environment.Environment 
    implements PortalEventHandlerIntf, ItemEventHandlerIntf, ObstacleEventHandlerIntf {
    
    public Player player;
    
    private int environmentTime;
    
    private static final int ENVIRONMENT_DAY_LENGTH = 24000;
    
    public final Grid environmentGrid;
    
    public GameState gameState;
    
    
    public static final int DEFAULT_WINDOW_WIDTH = 672;
    public static final int DEFAULT_WINDOW_HEIGHT = 384;
    public static final int DEFAULT_WINDOW_X = DEFAULT_WINDOW_WIDTH / 2;
    public static final int DEFAULT_WINDOW_Y = DEFAULT_WINDOW_HEIGHT / 2;
    
    public static final int GRID_CELL_SIZE = 24;
    
    LTAWImageManager im;
    AudioPlayerIntf am;

    private Map currentMap;
    private MapVisualizerDefault mapVisualizer;
    
    
    public Environment() {
        
        mapVisualizer = new MapVisualizerDefault(true, false);
//        mapVisualizer.setObjectColor(Color.RED);
        
        setCurrentMap(MapFactory.getCampusMap());
        
//        environmentTime = 8900;
        
        gameState = GameState.ENVIRONMENT;
        im = new LTAWImageManager();
        
        environmentGrid = new Grid
        (DEFAULT_WINDOW_WIDTH / GRID_CELL_SIZE, DEFAULT_WINDOW_HEIGHT / GRID_CELL_SIZE * 2, GRID_CELL_SIZE, GRID_CELL_SIZE, new Point(-DEFAULT_WINDOW_X, -DEFAULT_WINDOW_Y), Color.BLACK);
        
        
        updateGrid(2, 2);
        
        player = new Player(new Point(50, 50), im, am);
        
    }
    
    private void updateGrid(double xScreens, double yScreens) {
        if (xScreens < 1) xScreens = 1;
        if (yScreens < 1) yScreens = 1;
        int x = (int) (xScreens * DEFAULT_WINDOW_WIDTH);
        int y = (int) (yScreens * DEFAULT_WINDOW_HEIGHT);
        environmentGrid.setPosition(new Point(-(x / 2), -(y / 2)));
        environmentGrid.setColumns(x / environmentGrid.getCellWidth());
        environmentGrid.setRows(y / environmentGrid.getCellHeight());
    }

    @Override
    public void initializeEnvironment() {
    }

    @Override
    public void timerTaskHandler() {
        
        if (player != null) {
            player.timerTaskHandler();
            int testPoint = (int) TrigonometryCalculator.getHypotenuse(player.getPosition().x, player.getPosition().y);
//            if (testPoint < 96) System.out.println("Visible");
//            else System.out.println("Not visible");
        }
        
        environmentTime++;
        if (environmentTime > ENVIRONMENT_DAY_LENGTH) environmentTime = 0;
        
    }
    
    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (player != null) {
            if (e.getKeyCode() == KeyEvent.VK_W && !player.getDirections().contains(Direction.UP)) player.addDirection(Direction.UP);
            else if (e.getKeyCode() == KeyEvent.VK_S && !player.getDirections().contains(Direction.DOWN)) player.addDirection(Direction.DOWN);
            else if (e.getKeyCode() == KeyEvent.VK_A && !player.getDirections().contains(Direction.LEFT)) player.addDirection(Direction.LEFT);
            else if (e.getKeyCode() == KeyEvent.VK_D && !player.getDirections().contains(Direction.RIGHT)) player.addDirection(Direction.RIGHT);
        }
        
        if (currentMap != null) {
            if (e.getKeyCode() == KeyEvent.VK_0) {
                mapVisualizer.toggleShowAllObjects();
            }
        }     
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if (player != null) {
            if (e.getKeyCode() == KeyEvent.VK_W && player.getDirections().contains(Direction.UP)) player.removeDirection(Direction.UP);
            else if (e.getKeyCode() == KeyEvent.VK_S && player.getDirections().contains(Direction.DOWN)) player.removeDirection(Direction.DOWN);
            else if (e.getKeyCode() == KeyEvent.VK_A && player.getDirections().contains(Direction.LEFT)) player.removeDirection(Direction.LEFT);
            else if (e.getKeyCode() == KeyEvent.VK_D && player.getDirections().contains(Direction.RIGHT)) player.removeDirection(Direction.RIGHT);
        }
    }
    
    @Override
    public void environmentMouseClicked(MouseEvent e) {
        Point ePoint = e.getPoint();
        ePoint.setLocation(ePoint.x * DEFAULT_WINDOW_WIDTH / LetsTakeAWalk.getWindowSize().width, ePoint.y * DEFAULT_WINDOW_HEIGHT / LetsTakeAWalk.getWindowSize().height);
        System.out.println("Mouse event " + ePoint.getLocation());
        if (currentMap != null) {
            Point cell = currentMap.getCellLocation(e.getPoint());
            currentMap.validateLocation(cell);
        }
    
    }
    
    @Override
    public void paintEnvironment(Graphics g) {
        
        ArrayList<Entity> entities = new ArrayList<>();
        if (player != null) entities.add(player);
        
        entities.sort((Entity e1, Entity e2) -> {
            final int y1 = e1.getPosition().y;
            final int y2 = e2.getPosition().y;
            return y1 < y2 ? -1 : y1 > y2 ? 1 : 0;
        });
        
        AffineTransform atWindow;
        Graphics2D graphics = (Graphics2D) g;
        atWindow = AffineTransform.getScaleInstance((double) LetsTakeAWalk.getWindowSize().width / DEFAULT_WINDOW_WIDTH, (double) LetsTakeAWalk.getWindowSize().height / DEFAULT_WINDOW_HEIGHT);
        if (atWindow != null) graphics.setTransform(atWindow);
        
        int xTranslation = 0;
        int yTranslation = 0;
        
        if (currentMap != null) {
            currentMap.drawMap(g);
            
        }
//        
        entities.stream().forEach((entity) -> {
            entity.draw(graphics);
        });      
        
        int environmentFactor = 0;
        if (environmentTime >= ENVIRONMENT_DAY_LENGTH * 3 / 8) {
            if (environmentTime >= ENVIRONMENT_DAY_LENGTH / 2 && environmentTime < ENVIRONMENT_DAY_LENGTH * 7 / 8) environmentFactor = 255;
            else {
                environmentFactor = environmentTime - (ENVIRONMENT_DAY_LENGTH * 3 / 8);
                if (environmentTime > ENVIRONMENT_DAY_LENGTH / 2) environmentFactor = Math.abs(environmentFactor - (ENVIRONMENT_DAY_LENGTH * 5 / 8));
                environmentFactor = environmentFactor * 255 * 8 / ENVIRONMENT_DAY_LENGTH;
            }
        }
        
        graphics.setColor(new Color(255 - environmentFactor, 128 - environmentFactor / 2, environmentFactor / 16, environmentFactor * 13 / 16));
        if (environmentFactor > 0) graphics.fillRect(-xTranslation, -yTranslation, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
    }
    
    public void drawGridBase(Graphics2D graphics) {
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.fillRect(environmentGrid.getPosition().x + DEFAULT_WINDOW_X,
        environmentGrid.getPosition().y + DEFAULT_WINDOW_Y,
        environmentGrid.getGridSize().width - DEFAULT_WINDOW_WIDTH,
        environmentGrid.getGridSize().height - DEFAULT_WINDOW_HEIGHT);
        
        graphics.setColor(Color.GRAY);
        for (int i = 0; i < environmentGrid.getColumns(); i++) {
            int x = (int) environmentGrid.getCellSystemCoordinate(i, 0).x;
            graphics.fillRect(x, environmentGrid.getPosition().y, environmentGrid.getCellWidth(), environmentGrid.getCellHeight());
            graphics.fillRect(x, environmentGrid.getPosition().y + environmentGrid.getGridSize().height - environmentGrid.getCellHeight(), environmentGrid.getCellWidth(), environmentGrid.getCellHeight());
        }
        
        for (int i = 0; i < environmentGrid.getRows(); i++) {
            int y = (int) environmentGrid.getCellSystemCoordinate(0, i).y;
            graphics.fillRect(environmentGrid.getPosition().x, y, environmentGrid.getCellWidth(), environmentGrid.getCellHeight());
            graphics.fillRect(environmentGrid.getPosition().x + environmentGrid.getGridSize().width - environmentGrid.getCellWidth(), y, environmentGrid.getCellWidth(), environmentGrid.getCellHeight());
        }
        
        environmentGrid.paintComponent(graphics);
    }
    
    public void fillGrid(Graphics2D graphics, BufferedImage image, int xTranslation, int yTranslation) {
        for (int x = 0; x < environmentGrid.getColumns(); x++) {
                for (int y = 0; y < environmentGrid.getRows(); y++) {
                    
                    Point gridPoint = new Point(environmentGrid.getCellSystemCoordinate(x, y));
                    if (player != null &&
                        gridPoint.x + environmentGrid.getCellWidth() >= -xTranslation + 3 &&
                        gridPoint.x - DEFAULT_WINDOW_WIDTH <= -xTranslation - 3 &&
                        gridPoint.y + environmentGrid.getCellHeight() >= -yTranslation + 2 &&
                        gridPoint.y - DEFAULT_WINDOW_HEIGHT <= -yTranslation - 1)
                      
                        graphics.drawImage(image,
                        gridPoint.x, gridPoint.y,
                        environmentGrid.getCellWidth(),
                        environmentGrid.getCellHeight(), null);
                    
            }
        }
    }

    /**
     * @return the currentMap
     */
    public Map getCurrentMap() {
        return currentMap;
    }

    /**
     * @param currentMap the currentMap to set
     */
    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
        
        this.currentMap.setMapVisualizer(mapVisualizer);
        this.currentMap.setPosition(new Point(25, 25));
        //hook up the listener method
        this.currentMap.setPortalEventHandler(this);
        this.currentMap.setItemEventHandler(this);
        this.currentMap.setObstacleEventHandler(this);   
        repaint();
    }

//<editor-fold defaultstate="collapsed" desc="PortalEventHandlerIntf">
    @Override
    public boolean portalEvent(Portal portal) {
        System.out.println("PORTAL");
        setCurrentMap(MapFactory.getMap(portal.getDestinationMapName()));
        return true;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="ItemEventHandlerIntf">
    @Override
    public boolean itemEvent(Item item) {
        System.out.println("Item " + item.getType() + " " + item.getLocation());
        return true;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="ObstacleEventHandlerIntf">
    @Override
    public boolean obstacleEvent(Obstacle obstacle) {
        System.out.println("Obstacle " + obstacle.getType() + " " + obstacle.getLocation());
        return false;
    }
//</editor-fold>
    
}
