/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dev.jamtech.Model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Interface for any moving object in the simulation implemented by {@link MotionObjectAbstract}
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
@Document
public interface MotionObject {
    
    public int getId();
    /**
     * This method must change the latitude and longitude of the object
     */
    public void move();
    /**
     * This method must check if the {@link MotionObject} has achieved its target value
     */
    public boolean outcomeAchieved(double value, String action, int direction);
    /**
     * This method must change the {@link Angle} value of target object
     */
    public void changeDirection(double value,int direction, double inc);
    /**
     * This method must change the speed value of the target object
     */
    public void changeSpeed(double inc, double max);
    /**
     * This method must change the acceleration value of the target object
     */
    public void changeAcceleration(double value, int direction, double inc, double max,boolean stop);
    /**
     * This method must change the height value of the target object
     */
    public void changeHeight(double inc, double max);
    /**
     * This method must change the vSpeed value of the target object
     */
    public void changeVSpeed(double value, int direction, double inc, double max, boolean stop);
}
