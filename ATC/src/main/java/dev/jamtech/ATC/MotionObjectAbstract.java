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
    public void changeDirection(double value, int direction)
    {
        switch(direction)
        {
            case 0 -> this.angle.clockwise(value);
            case 1 -> this.angle.antiClockwise(value);
        }
    }
    
    @Override
    public void changeHeight(double value, int direction)
    {
        switch(direction)
        {
            case 0 -> this.setHeight(value+this.getHeight());
            case 1 -> this.setHeight(this.getHeight()-value);
        }
    }
    
    @Override
    public void changeVSpeed(double value, int direction)
    {
        switch(direction)
        {
            case 0 -> this.setvSpeed(value+this.getvSpeed());
            case 1 -> this.setvSpeed(this.getvSpeed()-value);
        }
    }
    
    
    @Override
    public void changeSpeed(double value, int direction)
    {
        switch(direction)
        {
            case 0 -> this.setSpeed(value+this.getSpeed());
            case 1 -> this.setSpeed(this.getSpeed()-value);
        }
    }
    
    @Override
    public void changeAcceleration(double value, int direction)
    {
        switch(direction)
        {
            case 0 -> this.setAcceleration(value+this.getAcceleration());
            case 1 -> this.setAcceleration(this.getAcceleration()-value);
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
