/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public class MotionObjectAcceleration extends CommandObjectAbstract {
    
    public MotionObjectAcceleration(double value, int direction)
    {
        super(value,direction);
    }

    @Override
    public void tick() {
        this.motionObject.changeAcceleration(this.value, this.direction);
    }

    @Override
    public void update(double time) {
        tick();
    }
    
}
