/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
public class MapOriginator implements Observer {

    public GeoMap getMap() {
        return map;
    }

    public void setMap(GeoMap map) {
        this.map = map;
    }
    
    private GeoMap map;
    private static MapOriginator instance;
    
    public MapMemento storeInMapMemento()
    {
        return new MapMemento(this.getMap());
    }
    
    public void restoreFromMapMemento(MapMemento memento)
    {
        this.setMap(memento.getMap());
    }
    
    public void addToMap(MapObject mapObject)
    {
        this.map.addObjects(mapObject);
    }
    
    private MapOriginator()
    {
        
    }
    
    public void removeFromMap(MapObject mapObject)
    {
        this.map.removeObject(mapObject);
    }
    
    public static MapOriginator getInstance()
    {
        if (MapOriginator.instance == null)
        {
            MapOriginator.instance = new MapOriginator();
        }
        return MapOriginator.instance;
    }

    @Override
    public boolean update(double time) {
        this.storeInMapMemento();
        return false;
    }
    
}
