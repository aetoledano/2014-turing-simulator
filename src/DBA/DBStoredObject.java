/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DBA;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author raven
 */
public class DBStoredObject {
    String name,alpha,description;
    ImageIcon photo;
    Object net;
    Point[] locations;

    /**
     *
     * @param name
     * @param alpha
     * @param description
     * @param photo
     * @param net
     * @param loc
     * 
     */
    public DBStoredObject(String name, String alpha, String description, Image photo, Object net, Point[] loc) {
        this.name = name;
        this.alpha = alpha;
        this.description = description;
        this.photo = new ImageIcon(photo);
        this.net = net;
        this.locations = loc;
    }

    public String getName() {
        return name;
    }

    public String getAlphabet() {
        return alpha;
    }

    public String getDescription() {
        return description;
    }

    public ImageIcon getPhoto() {
        return photo;
    }

    public Object getNet() {
        return net;
    }

    public Point[] getLocations() {
        return locations;
    }


    
}
