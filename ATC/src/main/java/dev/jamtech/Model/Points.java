/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public class Points {
    
    public Points()
    {
    }

    public List<NavAid> getAllPoints() {
        return allPoints;
    }

    public void setAllPoints(List<NavAid> allPoints) {
        this.allPoints = allPoints;
    }
    
    public void addPoint(NavAid point)
    {
        this.allPoints.add(point);
    }
    
    private List<NavAid> allPoints = new ArrayList();
    
}
