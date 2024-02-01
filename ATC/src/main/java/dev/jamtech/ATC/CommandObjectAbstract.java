/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public abstract class CommandObjectAbstract implements Observer, Command {
    
    public MotionObject getMotionObject() {
        return motionObject;
    }

    public void setMotionObject(MotionObject motionObject) {
        this.motionObject = motionObject;
    }
    
    protected MotionObject motionObject;
    protected double value;
    protected int direction;
    
    public CommandObjectAbstract(double value, int direction)
    {
        this.value = value;
        this.direction = direction;
    }

    @Override
    public void tick() {
        System.out.println("There is no command");
    }

    @Override
    public void update(double time) {
        tick();
    }
    
}
