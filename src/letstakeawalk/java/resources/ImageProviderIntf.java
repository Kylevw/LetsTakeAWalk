/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package letstakeawalk.java.resources;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Kyle van Wiltenburg
 */
public interface ImageProviderIntf {
    
    public BufferedImage getImage(String name);
    public ArrayList<String> getImageList(String listName);
    public BufferedImage getTintedImage(BufferedImage image, Color tintColor);
    
}
