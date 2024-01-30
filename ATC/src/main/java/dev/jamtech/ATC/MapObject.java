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
public abstract class MapObject {

    public Double getxPos() {
        return xPos;
    }

    public void setxPos(Double xPos) {
        this.xPos = xPos;
    }

    public Double getyPos() {
        return yPos;
    }

    public void setyPos(Double yPos) {
        this.yPos = yPos;
    }


    
    private Double xPos;
    private Double yPos;
    
}
