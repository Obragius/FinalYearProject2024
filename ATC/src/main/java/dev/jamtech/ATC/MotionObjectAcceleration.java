/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public class MotionObjectAcceleration implements Command, Observer {

    public MotionObject getMotionObject() {
        return motionObject;
    }

    public void setMotionObject(MotionObject motionObject) {
        this.motionObject = motionObject;
    }
    
    private MotionObject motionObject;
    private double value;
    private int direction;
    
    public MotionObjectAcceleration(double value, int direction)
    {
        this.value = value;
        this.direction = direction;
    }

    @Override
    public void tick() {
        this.motionObject.changeAcceleration(value, direction);
    }

    @Override
    public void update(double time) {
        tick();
    }
    
}
