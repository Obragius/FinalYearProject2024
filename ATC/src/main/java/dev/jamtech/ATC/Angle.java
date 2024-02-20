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
        if (this.getValue() + value < 0)
        {
            this.value = (this.getValue() + value) + 360;
        }
        else if (this.getValue() + value > 360)
        {
            this.value = (this.getValue() + value) - 360;
        }
        else
        {
         this.value = this.getValue() + value;
        }
    }
    
    public void antiClockwise(double value)
    {
        if (this.getValue() - value < 0)
        {
            this.value = (this.getValue() - value) + 360;
        }
        else if (this.getValue() - value > 360)
        {
            this.value = (this.getValue() - value) - 360;
        }
        else
        {
         this.value = this.getValue() - value;
        }
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
        this.setValue(0);
    }
    
    private double value;
    
    
}
