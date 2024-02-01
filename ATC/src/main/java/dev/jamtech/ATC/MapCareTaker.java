/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
public class MapCareTaker {
    
    private List<MapMemento> mapsList;
    // Sets the number of maps saved in the object
    private int storage;
    
    public MapCareTaker(int storage)
    {
        this.mapsList = new ArrayList();
        this.storage = storage;
    }
    
    public void addMapMemento(MapMemento memento)
    {
        if (this.mapsList.size() >= this.storage)
        {
            this.mapsList.remove(0);
        }
        this.mapsList.add(memento);
    }
    
    public MapMemento getMemento(int index)
    {
        return this.mapsList.get(index);
    }
    
}
