/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import java.math.BigDecimal;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
public class Angle {

    
    public void clockwise(double value)
    {
        this.value = (this.getValue() + value) % 360;
    }
    
    public void antiClockwise(double value)
    {
        this.value = (this.getValue() - value) % 360;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    public Angle(double value)
    {
        this.setValue(value);
    }
    
    public Angle()
    {
        
    }
    
    private double value;
    
    
}
