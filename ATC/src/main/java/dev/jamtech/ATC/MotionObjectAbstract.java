/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
public abstract class MotionObjectAbstract extends MapObject implements MotionObject {
    
    public MapObject createObject()
    {
        return null;
    }
    
    @Override
    public void move()
    {
        List<Double> newCoord = GeographicalCalculator.coordCalc(this.getxPos(), this.getyPos(), this.getSpeed(), this.getAngle().getValue());
        this.setxPos(newCoord.get(0));
        this.setyPos(newCoord.get(1));
    }
    
    @Override
    public void changeDirection(double value, int direction, double inc)
    {
        double deltaValue;
        if (value >= this.angle.getValue())
        {
            deltaValue = switch(direction)
            {
                case 0 -> value - this.angle.getValue();
                case 1 -> 360.0 - value + this.angle.getValue();
                default -> 0.0;
            };
        }
        else
        {
            deltaValue = switch(direction)
            {
                case 0 -> 360.0 - this.angle.getValue() + value;
                case 1 -> this.angle.getValue() - value;
                default -> 0.0;
            };
        }
        double finalValue;
        if (deltaValue >= inc)
        {
            finalValue = inc;
        }
        else
        {
            finalValue = deltaValue;
        }
        switch (direction)
        {
            case 0 -> this.angle.clockwise(finalValue);
            case 1 -> this.angle.antiClockwise(finalValue);
        }
    }
    
    @Override
    public boolean outcomeAchieved(double value, String action)
    {
        return switch (action)
        {
            case "Turn" -> this.angle.getValue() == value;
            case "Speed" -> this.speed == value;
            case "VSpeed" -> this.vSpeed == value;
            case "Accelerate" -> this.acceleration == value;
            case "Height" -> this.height == value;
            default -> false;
        };
    }
    
    @Override
    public void changeHeight(double value, int direction, double inc)
    {
        double deltaValue;
        if (value >= inc)
        {
            deltaValue = inc;
        }
        else
        {
            deltaValue = value;
        }
        switch(direction)
        {
            case 0 -> this.setHeight(deltaValue+this.getHeight());
            case 1 -> this.setHeight(this.getHeight()-deltaValue);
        }
    }
    
    @Override
    public void changeVSpeed(double value, int direction, double inc)
    {
        double deltaValue;
        if (value >= inc)
        {
            deltaValue = inc;
        }
        else
        {
            deltaValue = value;
        }
        switch(direction)
        {
            case 0 -> this.setvSpeed(deltaValue+this.getvSpeed());
            case 1 -> this.setvSpeed(this.getvSpeed()-deltaValue);
        }
    }
    
    
    @Override
    public void changeSpeed(double value, int direction, double inc)
    {
        double deltaValue;
        if (value >= inc)
        {
            deltaValue = inc;
        }
        else
        {
            deltaValue = value;
        }
        switch(direction)
        {
            case 0 -> this.setSpeed(deltaValue+this.getSpeed());
            case 1 -> this.setSpeed(this.getSpeed()-deltaValue);
        }
    }
    
    @Override
    public void changeAcceleration(double value, int direction, double inc)
    {
        double deltaValue;
        if (value >= inc)
        {
            deltaValue = inc;
        }
        else
        {
            deltaValue = value;
        }
        switch(direction)
        {
            case 0 -> this.setAcceleration(deltaValue+this.getAcceleration());
            case 1 -> this.setAcceleration(this.getAcceleration()-deltaValue);
        }
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getvSpeed() {
        return vSpeed;
    }

    public void setvSpeed(Double vSpeed) {
        this.vSpeed = vSpeed;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }
    
    private Double speed;
    private Double acceleration;
    private Double height;
    private Double vSpeed;
    private Double id;

    

    public Angle getAngle() {
        return angle;
    }

    public void setAngle(Angle angle) {
        this.angle = angle;
    }
    private Angle angle;
    
}
