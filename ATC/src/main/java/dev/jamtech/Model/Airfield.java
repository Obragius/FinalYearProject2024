/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.Model;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
public class Airfield extends StationObjectAbstract {
    
    private ILS ils;
    private Runway runway;
    
    public Airfield()
    {
        this.ils = new ILS();
        this.runway = new Runway();
    }
            
    
}
