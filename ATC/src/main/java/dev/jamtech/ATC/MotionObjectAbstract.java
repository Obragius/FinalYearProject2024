/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import java.util.List;

/**
 * Implements {@link MotionObject} interface and extends {@link MapObject}
 * This is the parent class of any moving object in the simulation.
 * Is extended by {@link Aircraft}
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
    public boolean outcomeAchieved(double value, String action, int direction)
    {
        if (direction == 0)
        {   
            return switch (action)
            {
                case "Turn" -> this.angle.getValue() == value;
                case "Speed" -> this.speed > value;
                case "Height" -> this.height > value;
                default -> false;
            };
        }
        else
        {
           return switch (action)
            {
                case "Turn" -> this.angle.getValue() == value;
                case "Speed" -> this.speed < value;
                case "Height" -> this.height < value;
                default -> false;
            }; 
        }
    }
    
    @Override
    public void changeHeight(double inc, double max)
    {
        double value = this.getvSpeed();
        double deltaValue;
        if (Math.abs(value) >= inc)
        {
            if (value >= 0)
            {
                deltaValue = inc;
            }
            else
            {
                deltaValue = -inc;
            }
        }
        else
        {
            deltaValue = value;
        }
        this.setHeight(deltaValue+this.getHeight());
        
    }
    
    @Override
    public void changeVSpeed(double value, int direction, double inc, double max, boolean stop)
    {
        double deltaValue;
        switch(direction)
        {
            case 0 : 
            {
                if (this.getHeight() + 50 > value )
                {
                    max = 12;
                }
                if (this.getHeight() + 40 > value )
                {
                    max = 8;
                }
                if (this.getHeight() + 30 > value )
                {
                    max = 5;
                }
                if (Math.abs(this.getvSpeed()) > max)
                {
                    deltaValue = - (this.getvSpeed() - max);
                }
                else
                {
                    deltaValue = inc;
                }
                if (this.getHeight() + 5 >= value )
                {
                    deltaValue = (value - this.getHeight() - this.getvSpeed());
                }
                if (stop)
                {
                    max = 5;
                    deltaValue = - this.getvSpeed();
                }
                this.setvSpeed(deltaValue+this.getvSpeed(),max);
                break;
            }
            case 1 : 
            {
                if (this.getHeight() - 50 < value )
                {
                    max = 12;
                }
                if (this.getHeight() - 40 < value )
                {
                    max = 8;
                }
                if (this.getHeight() - 30 < value )
                {
                    max = 5;
                }
                if (Math.abs(this.getvSpeed()) > max)
                {
                    deltaValue = - ( this.getvSpeed() + max);
                }
                else
                {
                    deltaValue = -inc;
                }
                if (this.getHeight() - 5 <= value )
                {
                    deltaValue = ( value - this.getHeight() - this.getvSpeed());
                }
                if (stop)
                {
                    max = 5;
                    deltaValue = - this.getvSpeed();
                }
                this.setvSpeed(deltaValue+this.getvSpeed(),max);
                break;
            }
        }
    }
    
    
    @Override
    public void changeSpeed(double inc,double max)
    {
        double value = this.getAcceleration();
        double deltaValue;
        if (Math.abs(value) >= inc)
        {
            if (value >= 0)
            {
                deltaValue = inc;
            }
            else
            {
                deltaValue = -inc;
            }
        }
        else
        {
            deltaValue = value;
        }
        this.setSpeed(deltaValue+this.getSpeed());
        
    }
    
    @Override
    public void changeAcceleration(double value, int direction, double inc, double max, boolean stop)
    {
        double deltaValue;
        switch(direction)
        {
            case 0 : 
            {
                if (this.getSpeed() + 10 > value )
                {
                    max = 3;
                }
                else if (this.getSpeed() + 5 > value )
                {
                    max = 2;
                }
                if (Math.abs(this.getAcceleration()) > max)
                {
                    deltaValue = - (this.getAcceleration() - max);
                }
                else
                {
                    deltaValue = inc;
                }
                if (this.getSpeed() + 2 >= value )
                {
                    deltaValue = (value - this.getSpeed() - this.getAcceleration());
                }
                if (stop)
                {
                    max = 2;
                    deltaValue = - this.getAcceleration();
                }
                this.setAcceleration(deltaValue+this.getAcceleration(),max);
                break;
            }
            case 1 : 
            {
                if (this.getSpeed() - 5 < value )
                {
                    max = 2;
                }
                
                else if (this.getSpeed() - 10 < value )
                {
                    max = 3;
                }
                if (Math.abs(this.getAcceleration()) > max)
                {
                    deltaValue = - ( this.getAcceleration() + max);
                }
                else
                {
                    deltaValue = -inc;
                }
                if (this.getSpeed() - 2 <= value )
                {
                    deltaValue = ( value - this.getSpeed() - this.getAcceleration());
                }
                if (stop)
                {
                    max = 2;
                    deltaValue = - this.getAcceleration();
                }
                this.setAcceleration(deltaValue+this.getAcceleration(),max);
                break;
            }
        }
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getAcceleration() {
        return this.acceleration;
    }

    public void setAcceleration(double acceleration,double max) {
        if (Math.abs(acceleration) <= max)
        {
            this.acceleration = acceleration;
        }
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

    public void setvSpeed(double vSpeed, double max) {
        if (Math.abs(vSpeed) <= max)
        {
            this.vSpeed = vSpeed;
        }
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }
    
    /**
    * Tracks the speed of the motion object
    */
    private double speed;
    /**
    * Tracks the acceleration of the motion object
    */
    private double acceleration;
    /**
    * Tracks the height of the motion object
    */
    private double height;
    /**
    * Tracks the vertical speed of the motion object
    */
    private double vSpeed;
    /**
    * Unused attribute, might be used later to use with persistent storage
    */
    private double id;

    

    public Angle getAngle() {
        return angle;
    }

    public void setAngle(Angle angle) {
        this.angle = angle;
    }
    
    /**
    * Stores {@link Angle} object with the value of the angle of direction
    */
    private Angle angle;
    
}
