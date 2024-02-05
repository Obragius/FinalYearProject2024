/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import java.util.ArrayList;
import java.util.List;

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
    // Increment for the action
    protected double inc;
    
    public static List<CommandObjectAbstract> commandFactory(String type, int numberOfObjects, double value, int direction)
    {
        List<CommandObjectAbstract> result = new ArrayList();
        for (int i = 0; i < numberOfObjects; i++)
        {
            switch (type)
            {
                case "Move" -> result.add(new MotionObjectMove(value,direction));
                case "Turn" -> result.add(new MotionObjectTurn(value,direction));
                case "Speed" -> result.add(new MotionObjectSpeed(value,direction));
                case "Acceleration" -> result.add(new MotionObjectAcceleration(value,direction));
            }
        }
        return result;
    }
    
    public CommandObjectAbstract(double value, int direction, double inc)
    {
        this.value = value;
        this.direction = direction;
        this.inc = inc;
    }

    @Override
    public void tick() {
        System.out.println("There is no command");
    }

    @Override
    public boolean update(double time) {
        tick();
        return false;
    }
    
}
