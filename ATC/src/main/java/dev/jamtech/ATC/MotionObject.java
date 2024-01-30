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
    public void changeDirection(double value,int direction);
    public void changeSpeed(double value, int direction);
    
}
