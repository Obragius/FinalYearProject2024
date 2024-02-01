/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public class MotionObjectMove implements Command, Observer {

    public MotionObject getMotionObject() {
        return motionObject;
    }

    public void setMotionObject(MotionObject motionObject) {
        this.motionObject = motionObject;
    }
    
    private MotionObject motionObject;

    @Override
    public void tick(int tickNum) {
        for(int i = 0; i < tickNum; i++)
        {
            this.motionObject.move();
        }
    }

    @Override
    public void update(double time) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
