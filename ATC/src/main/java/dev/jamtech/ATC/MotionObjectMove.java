/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
@Document
public class MotionObjectMove extends CommandObjectAbstract {

    public MotionObjectMove(double value, int direction)
    {
        super(value,direction,0.0,0.0);
    }
    
    @Override
    public void tick() 
    {
            this.motionObject.move();
    }

    @Override
    public boolean update(double time) {
        this.tick();
        return false;
    }
    
    
    
}
