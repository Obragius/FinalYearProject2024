/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import java.util.List;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
public class Map {

    public List<MapObject> getAllObjects() {
        return allObjects;
    }

    public void setAllObjects(List<MapObject> allObjects) {
        this.allObjects = allObjects;
    }
    
    private List<MapObject> allObjects;
    
}
