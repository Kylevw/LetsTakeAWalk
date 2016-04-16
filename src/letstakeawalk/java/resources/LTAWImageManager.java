/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstakeawalk.java.resources;

import images.ImageManager;
import images.ResourceTools;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Kyle van Wiltenburg
 */
public class LTAWImageManager extends ImageManager implements ImageProviderIntf{
    
    private static final String MISSING_TEXTURE = "MISSING_TEXTURE";
    
    public static final String ENTITY_SHADOW_BIG = "ENTITY_SHADOW_BIG";
    public static final String ENTITY_SHADOW_MEDIUM = "ENTITY_SHADOW_MEDIUM";
    public static final String ENTITY_SHADOW_SMALL = "ENTITY_SHADOW_SMALL";
    public static final String ENTITY_SHADOW_TINY = "ENTITY_SHADOW_TINY";
    
    public static final String PLAYER_IDLE_UP_00 = "PLAYER_UP";
    public static final String PLAYER_IDLE_DOWN_00 = "PLAYER_DOWN";
    public static final String PLAYER_IDLE_LEFT_00 = "PLAYER_LEFT";
    public static final String PLAYER_IDLE_RIGHT_00 = "PLAYER_RIGHT";
    
    public static final String TIMMY_UP = "TIMMY_UP";
    public static final String TIMMY_UP_RIGHT = "TIMMY_UP_RIGHT";
    public static final String TIMMY_RIGHT = "TIMMY_RIGHT";
    public static final String TIMMY_DOWN_RIGHT = "TIMMY_DOWN_RIGHT";
    public static final String TIMMY_DOWN = "TIMMY_DOWN";
    public static final String TIMMY_DOWN_LEFT = "TIMMY_DOWN_LEFT";
    public static final String TIMMY_LEFT = "TIMMY_LEFT";
    public static final String TIMMY_UP_LEFT = "TIMMY_UP_LEFT";
    
    public static final String PLAYER_WALK_DOWN_00 = "PLAYER_WALK_DOWN_01";
    public static final String PLAYER_WALK_DOWN_01 = "PLAYER_WALK_DOWN_02";
    public static final String PLAYER_WALK_DOWN_02 = "PLAYER_WALK_DOWN_03";
    public static final String PLAYER_WALK_DOWN_03 = "PLAYER_WALK_DOWN_04";
    public static final String PLAYER_WALK_DOWN_04 = "PLAYER_WALK_DOWN_05";
    public static final String PLAYER_WALK_DOWN_05 = "PLAYER_WALK_DOWN_06";
    public static final String PLAYER_WALK_DOWN_06 = "PLAYER_WALK_DOWN_07";
    public static final String PLAYER_WALK_DOWN_07 = "PLAYER_WALK_DOWN_08";
    
    
    public static final String PLAYER_IDLE_UP_LIST = "PLAYER_IDLE_UP_LIST";
    public static final String PLAYER_IDLE_DOWN_LIST = "PLAYER_IDLE_DOWN_LIST";
    public static final String PLAYER_IDLE_LEFT_LIST = "PLAYER_IDLE_LEFT_LIST";
    public static final String PLAYER_IDLE_RIGHT_LIST = "PLAYER_IDLE_RIGHT_LIST";
    
    public static final String PLAYER_WALK_UP_LIST = "PLAYER_WALK_UP_LIST";
    public static final String PLAYER_WALK_DOWN_LIST = "PLAYER_WALK_DOWN_LIST";
    public static final String PLAYER_WALK_LEFT_LIST = "PLAYER_WALK_LEFT_LIST";
    public static final String PLAYER_WALK_RIGHT_LIST = "PLAYER_WALK_RIGHT_LIST";
    
    public static final String PLAYER_JUMP_UP_LIST = "PLAYER_JUMP_UP_LIST";
    public static final String PLAYER_JUMP_DOWN_LIST = "PLAYER_JUMP_DOWN_LIST";
    public static final String PLAYER_JUMP_LEFT_LIST = "PLAYER_JUMP_LEFT_LIST";
    public static final String PLAYER_JUMP_RIGHT_LIST = "PLAYER_JUMP_RIGHT_LIST";
    
    private final ArrayList<String> PLAYER_IDLE_UP;
    private final ArrayList<String> PLAYER_IDLE_DOWN;
    private final ArrayList<String> PLAYER_IDLE_LEFT;
    private final ArrayList<String> PLAYER_IDLE_RIGHT;
    
    private final ArrayList<String> PLAYER_WALK_UP;
    private final ArrayList<String> PLAYER_WALK_DOWN;
    private final ArrayList<String> PLAYER_WALK_LEFT;
    private final ArrayList<String> PLAYER_WALK_RIGHT;
    
    private final ArrayList<String> PLAYER_JUMP_UP;
    private final ArrayList<String> PLAYER_JUMP_DOWN;
    private final ArrayList<String> PLAYER_JUMP_LEFT;
    private final ArrayList<String> PLAYER_JUMP_RIGHT;
    
    public static final String WOMAN_IDLE_UP_00 = "WOMAN_UP";
    public static final String WOMAN_IDLE_DOWN_00 = "WOMAN_DOWN";
    public static final String WOMAN_IDLE_LEFT_00 = "WOMAN_LEFT";
    public static final String WOMAN_IDLE_RIGHT_00 = "WOMAN_RIGHT";
    
    
    public static final String WOMAN_IDLE_UP_LIST = "WOMAN_IDLE_UP_LIST";
    public static final String WOMAN_IDLE_DOWN_LIST = "WOMAN_IDLE_DOWN_LIST";
    public static final String WOMAN_IDLE_LEFT_LIST = "WOMAN_IDLE_LEFT_LIST";
    public static final String WOMAN_IDLE_RIGHT_LIST = "WOMAN_IDLE_RIGHT_LIST";
    
    public static final String WOMAN_WALK_UP_00 = "WOMAN_WALK_RIGHT_00";
    public static final String WOMAN_WALK_UP_01 = "WOMAN_WALK_RIGHT_01";
    public static final String WOMAN_WALK_UP_02 = "WOMAN_WALK_RIGHT_02";
    
    public static final String WOMAN_WALK_UP_LIST = "WOMAN_WALK_UP_LIST";
     
    private final ArrayList<String> WOMAN_WALK_UP = new ArrayList<>();
    
    public static final String WOMAN_WALK_RIGHT_00 = "WOMAN_WALK_RIGHT_00";
    public static final String WOMAN_WALK_RIGHT_01 = "WOMAN_WALK_RIGHT_01";
    public static final String WOMAN_WALK_RIGHT_02 = "WOMAN_WALK_RIGHT_02";
    
    public static final String WOMAN_WALK_RIGHT_LIST = "WOMAN_WALK_RIGHT_LIST";
 
    private final ArrayList<String> WOMAN_WALK_RIGHT = new ArrayList<>();

    
    public static final String WOMAN_WALK_LEFT_00 = "WOMAN_WALK_LEFT_00";
    public static final String WOMAN_WALK_LEFT_01 = "WOMAN_WALK_LEFT_01";
    public static final String WOMAN_WALK_LEFT_02 = "WOMAN_WALK_LEFT_02";

    public static final String WOMAN_WALK_LEFT_LIST = "WOMAN_WALK_DOWN_LIST";

    private final ArrayList<String> WOMAN_WALK_LEFT = new ArrayList<>();

    
    
    public static final String WOMAN_WALK_DOWN_00 = "WOMAN_WALK_DOWN_00";
    public static final String WOMAN_WALK_DOWN_01 = "WOMAN_WALK_DOWN_01";
    public static final String WOMAN_WALK_DOWN_02 = "WOMAN_WALK_DOWN_02";
    
    public static final String WOMAN_WALK_DOWN_LIST = "WOMAN_WALK_DOWN_LIST";
    
    private final ArrayList<String> WOMAN_WALK_DOWN = new ArrayList<>();
    
    public static final String ENTITY_SHADOW = "ENTITY_SHADOW";
    
    
    
    
    private final ImageManager im;
    
    private final HashMap<String, Image> imageMap;
    private final HashMap<String, ArrayList> imageListMap;
    
    {
        imageListMap = new HashMap<>();
        imageMap = new HashMap<>();
        
        im = new ImageManager(imageMap);
        
        PLAYER_WALK_UP = new ArrayList<>();
        PLAYER_WALK_DOWN = new ArrayList<>();
        PLAYER_WALK_LEFT = new ArrayList<>();
        PLAYER_WALK_RIGHT = new ArrayList<>();
        
        PLAYER_IDLE_UP = new ArrayList<>();
        PLAYER_IDLE_DOWN = new ArrayList<>();
        PLAYER_IDLE_LEFT = new ArrayList<>();
        PLAYER_IDLE_RIGHT = new ArrayList<>();
        
        PLAYER_JUMP_UP = new ArrayList<>();
        PLAYER_JUMP_DOWN = new ArrayList<>();
        PLAYER_JUMP_LEFT = new ArrayList<>();
        PLAYER_JUMP_RIGHT = new ArrayList<>();
        
        
        PLAYER_IDLE_UP.add(PLAYER_IDLE_UP_00);
        PLAYER_IDLE_DOWN.add(PLAYER_IDLE_DOWN_00);
        PLAYER_IDLE_LEFT.add(PLAYER_IDLE_LEFT_00);
        PLAYER_IDLE_RIGHT.add(PLAYER_IDLE_RIGHT_00);
        
        PLAYER_WALK_DOWN.add(PLAYER_WALK_DOWN_00);
        PLAYER_WALK_DOWN.add(PLAYER_WALK_DOWN_01);
        PLAYER_WALK_DOWN.add(PLAYER_WALK_DOWN_02);
        PLAYER_WALK_DOWN.add(PLAYER_WALK_DOWN_03);
        PLAYER_WALK_DOWN.add(PLAYER_WALK_DOWN_04);
        PLAYER_WALK_DOWN.add(PLAYER_WALK_DOWN_05);
        PLAYER_WALK_DOWN.add(PLAYER_WALK_DOWN_06);
        PLAYER_WALK_DOWN.add(PLAYER_WALK_DOWN_07);
        
        imageListMap.put(PLAYER_IDLE_UP_LIST, PLAYER_IDLE_UP);
        imageListMap.put(PLAYER_IDLE_DOWN_LIST, PLAYER_IDLE_DOWN);
        imageListMap.put(PLAYER_IDLE_LEFT_LIST, PLAYER_IDLE_LEFT);
        imageListMap.put(PLAYER_IDLE_RIGHT_LIST, PLAYER_IDLE_RIGHT);
        
        imageListMap.put(PLAYER_WALK_UP_LIST, PLAYER_WALK_UP);
        imageListMap.put(PLAYER_WALK_DOWN_LIST, PLAYER_WALK_DOWN);
        imageListMap.put(PLAYER_WALK_LEFT_LIST, PLAYER_WALK_LEFT);
        imageListMap.put(PLAYER_WALK_RIGHT_LIST, PLAYER_WALK_RIGHT);
        
        imageMap.put(MISSING_TEXTURE, ResourceTools.loadImageFromResource("letstakeawalk/resources/images/utility/missing_texture.png"));
        
        BufferedImage entityShadows = (BufferedImage) ResourceTools.loadImageFromResource("letstakeawalk/resources/images/utility/shadow.png");
        
        BufferedImage playerSprites = (BufferedImage) ResourceTools.loadImageFromResource("letstakeawalk/resources/images/player/player_spritesheet.png");
        BufferedImage timmySprites = (BufferedImage) ResourceTools.loadImageFromResource("letstakeawalk/resources/images/timmy/timmy_spritesheet.png");
        
        imageMap.put(ENTITY_SHADOW_BIG, entityShadows.getSubimage(0, 0, 16, 8));
        imageMap.put(ENTITY_SHADOW_MEDIUM, entityShadows.getSubimage(0, 8, 16, 8));
        imageMap.put(ENTITY_SHADOW_SMALL, entityShadows.getSubimage(0, 16, 16, 8));
        imageMap.put(ENTITY_SHADOW_TINY, entityShadows.getSubimage(0, 24, 16, 8));
        
        imageMap.put(PLAYER_IDLE_UP_00, playerSprites.getSubimage(0, 0, 28, 64));
        imageMap.put(PLAYER_IDLE_DOWN_00, playerSprites.getSubimage(0, 64, 28, 64));
        imageMap.put(PLAYER_WALK_DOWN_00, playerSprites.getSubimage(28, 64, 28, 64));
        imageMap.put(PLAYER_WALK_DOWN_01, playerSprites.getSubimage(56, 64, 28, 64));
        imageMap.put(PLAYER_WALK_DOWN_02, playerSprites.getSubimage(84, 64, 28, 64));
        imageMap.put(PLAYER_WALK_DOWN_03, playerSprites.getSubimage(112, 64, 28, 64));
        imageMap.put(PLAYER_WALK_DOWN_04, playerSprites.getSubimage(140, 64, 28, 64));
        imageMap.put(PLAYER_WALK_DOWN_05, playerSprites.getSubimage(168, 64, 28, 64));
        imageMap.put(PLAYER_WALK_DOWN_06, playerSprites.getSubimage(196, 64, 28, 64));
        imageMap.put(PLAYER_WALK_DOWN_07, playerSprites.getSubimage(224, 64, 28, 64));
        imageMap.put(PLAYER_IDLE_LEFT_00, playerSprites.getSubimage(0, 128, 28, 64));
        imageMap.put(PLAYER_IDLE_RIGHT_00, playerSprites.getSubimage(0, 192, 28, 64));
        
        imageMap.put(TIMMY_UP, timmySprites.getSubimage(0, 0, 28, 56));
        imageMap.put(TIMMY_UP_RIGHT, timmySprites.getSubimage(28, 0, 28, 56));
        imageMap.put(TIMMY_RIGHT, timmySprites.getSubimage(56, 0, 28, 56));
        imageMap.put(TIMMY_DOWN_RIGHT, timmySprites.getSubimage(84, 0, 28, 56));
        imageMap.put(TIMMY_DOWN, timmySprites.getSubimage(0, 56, 28, 56));
        imageMap.put(TIMMY_DOWN_LEFT, timmySprites.getSubimage(28, 56, 28, 56));
        imageMap.put(TIMMY_LEFT, timmySprites.getSubimage(56, 56, 28, 56));
        imageMap.put(TIMMY_UP_LEFT, timmySprites.getSubimage(84, 56, 28, 56));
        
        
        
        imageMap.put(WOMAN_WALK_DOWN_00, ResourceTools.loadImageFromResource("letstakeawalk/resources/images/player/basic-lady-01-walking-forward-00.jpg"));
        imageMap.put(WOMAN_WALK_DOWN_01, ResourceTools.loadImageFromResource("letstakeawalk/resources/images/player/basic-lady-01.jpg"));        
        imageMap.put(WOMAN_WALK_DOWN_02, ResourceTools.loadImageFromResource("letstakeawalk/resources/images/player/basic-lady-01-walking-forward-02.jpg"));
        WOMAN_WALK_DOWN.add(WOMAN_WALK_DOWN_00);
        WOMAN_WALK_DOWN.add(WOMAN_WALK_DOWN_01);
        WOMAN_WALK_DOWN.add(WOMAN_WALK_DOWN_02);
        WOMAN_WALK_DOWN.add(WOMAN_WALK_DOWN_01);
        imageListMap.put(WOMAN_WALK_DOWN_LIST, WOMAN_WALK_DOWN);

        
        imageMap.put(WOMAN_WALK_UP_00, ResourceTools.loadImageFromResource("letstakeawalk/resources/images/player/basic-lady-01-walking-backwards.jpg"));
        imageMap.put(WOMAN_WALK_UP_01, ResourceTools.loadImageFromResource("letstakeawalk/resources/images/player/basic-lady-01-facing-away.jpg"));        
        imageMap.put(WOMAN_WALK_UP_02, ResourceTools.loadImageFromResource("letstakeawalk/resources/images/player/basic-lady-01-walking-backwards-02.jpg"));
        WOMAN_WALK_UP.add(WOMAN_WALK_UP_00);
        WOMAN_WALK_UP.add(WOMAN_WALK_UP_01);
        WOMAN_WALK_UP.add(WOMAN_WALK_UP_02);
        WOMAN_WALK_UP.add(WOMAN_WALK_UP_01);
        imageListMap.put(WOMAN_WALK_UP_LIST, WOMAN_WALK_UP);        
        
        imageMap.put(WOMAN_WALK_LEFT_00, ResourceTools.loadImageFromResource("letstakeawalk/resources/images/player/basic-lady-01-walking-left.jpg"));
        imageMap.put(WOMAN_WALK_LEFT_01, ResourceTools.loadImageFromResource("letstakeawalk/resources/images/player/basic-lady-01-outline-facing-left.jpg"));        
        imageMap.put(WOMAN_WALK_LEFT_02, ResourceTools.loadImageFromResource("letstakeawalk/resources/images/player/basic-lady-01-walking-left-02.jpg"));
        WOMAN_WALK_LEFT.add(WOMAN_WALK_LEFT_00);
        WOMAN_WALK_LEFT.add(WOMAN_WALK_LEFT_01);
        WOMAN_WALK_LEFT.add(WOMAN_WALK_LEFT_02);
        WOMAN_WALK_LEFT.add(WOMAN_WALK_LEFT_01);
        imageListMap.put(WOMAN_WALK_LEFT_LIST, WOMAN_WALK_LEFT);  
        
        imageMap.put(WOMAN_WALK_RIGHT_00, ResourceTools.loadImageFromResource("letstakeawalk/resources/images/player/basic-lady-01-walking-right.jpg"));
        imageMap.put(WOMAN_WALK_RIGHT_01, ResourceTools.loadImageFromResource("letstakeawalk/resources/images/player/basic-lady-01-outline-facing-right.jpg"));        
        imageMap.put(WOMAN_WALK_RIGHT_02, ResourceTools.loadImageFromResource("letstakeawalk/resources/images/player/basic-lady-01-walking-right-02.jpg"));
        WOMAN_WALK_RIGHT.add(WOMAN_WALK_RIGHT_00);
        WOMAN_WALK_RIGHT.add(WOMAN_WALK_RIGHT_01);
        WOMAN_WALK_RIGHT.add(WOMAN_WALK_RIGHT_02);
        WOMAN_WALK_RIGHT.add(WOMAN_WALK_RIGHT_01);
        imageListMap.put(WOMAN_WALK_RIGHT_LIST, WOMAN_WALK_RIGHT); 
        
        
        
        //Idle images
        imageMap.put(WOMAN_IDLE_DOWN_00, ResourceTools.loadImageFromResource("letstakeawalk/resources/images/player/basic-lady-01.jpg"));   
        imageMap.put(WOMAN_IDLE_UP_00, ResourceTools.loadImageFromResource("letstakeawalk/resources/images/player/basic-lady-01-facing-away.jpg"));        
        imageMap.put(WOMAN_IDLE_LEFT_00, ResourceTools.loadImageFromResource("letstakeawalk/resources/images/player/basic-lady-01-outline-facing-left.jpg"));   
        imageMap.put(WOMAN_IDLE_RIGHT_00, ResourceTools.loadImageFromResource("letstakeawalk/resources/images/player/basic-lady-01-outline-facing-right.jpg"));    
    }
    
    @Override
    public BufferedImage getImage(String name){
        BufferedImage image = (BufferedImage) im.getImage(name);
        if (image == null) image = (BufferedImage) im.getImage(MISSING_TEXTURE);
        return image;
    }
    
    @Override
    public ArrayList<String> getImageList(String listName){
        ArrayList<String> arrayList = imageListMap.get(listName);
        if (arrayList == null || arrayList.isEmpty()) {
            arrayList = new ArrayList<>();
            arrayList.add(MISSING_TEXTURE);
        }
        return arrayList;
    }
}

