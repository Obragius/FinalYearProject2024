/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
public abstract class MotionObjectAbstract extends MapObject implements MotionObject {

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getvSpeed() {
        return vSpeed;
    }

    public void setvSpeed(double vSpeed) {
        this.vSpeed = vSpeed;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }
    
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
    
    private double speed;
    private double acceleration;
    private double height;
    private double vSpeed;
    private double id;

    public Angle getAngle() {
        return angle;
    }

    public void setAngle(Angle angle) {
        this.angle = angle;
    }
    private Angle angle;
    
}
