/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
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
    protected double maxValue;
    
    public static List<CommandObjectAbstract> commandFactory(String type, int numberOfObjects, double value, int direction)
    {
        List<CommandObjectAbstract> result = new ArrayList();
        for (int i = 0; i < numberOfObjects; i++)
        {
            switch (type)
            {
                case "Move" -> result.add(new MotionObjectMove(value,direction));
                case "Turn" -> result.add(new MotionObjectTurn(value,direction));
                case "Speed" -> result.add(new MotionObjectAcceleration(value,direction));
                case "Height" -> result.add(new MotionObjectVSpeed(value,direction));
            }

        }
        return result;
    }
    
    public CommandObjectAbstract(double value, int direction, double inc,double maxValue)
    {
        this.value = value;
        this.direction = direction;
        this.inc = inc;
        this.maxValue = maxValue;
    }
    
    public CommandObjectAbstract(double value, int direction, double inc,double maxValue,MotionObject target)
    {
        this.value = value;
        this.direction = direction;
        this.inc = inc;
        this.maxValue = maxValue;
        this.motionObject = target;
    }
    
    public CommandObjectAbstract(double value, int direction, double inc, MotionObject target)
    {
        this.value = value;
        this.direction = direction;
        this.inc = inc;
        this.motionObject = target;
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
