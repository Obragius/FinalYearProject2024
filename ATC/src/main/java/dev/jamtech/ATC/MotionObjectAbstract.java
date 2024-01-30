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
public abstract class MotionObjectAbstract extends MapObject implements MotionObject {
    
    public MapObject createObject()
    {
        return null;
    }
    
    @Override
    public void move()
    {
        
    }
    
    @Override
    public void changeDirection(double value, int direction)
    {
        
    }
    
    @Override
    public void changeSpeed(double value, int direction)
    {
        
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
