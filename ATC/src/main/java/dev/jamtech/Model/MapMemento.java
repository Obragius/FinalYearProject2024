/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.Model;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
public class MapMemento {

    public GeoMap getMap() {
        return map;
    }
    
    private GeoMap map;
    
    public MapMemento(GeoMap map)
    {
        this.map = map;
    }
    
    
    
}
