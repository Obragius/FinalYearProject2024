/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dev.jamtech.ATC;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
public interface MotionObject {
    
    public void move();
    public boolean outcomeAchieved(double value, String action, int direction);
    public void changeDirection(double value,int direction, double inc);
    public void changeSpeed(double inc, double max);
    public void changeAcceleration(double value, int direction, double inc, double max,boolean stop);
    public void changeHeight(double inc, double max);
    public void changeVSpeed(double value, int direction, double inc);
}
