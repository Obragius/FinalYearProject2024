/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public class MotionObjectSpawn implements Observer {
    
    public void addToMap(String type)
    {
        switch(type)
        {
            case "aircraft" -> MapOriginator.getInstance().addToMap(new Aircraft());
            case "SID" -> MapOriginator.getInstance().addToMap(new SID());
            case "STAR" -> MapOriginator.getInstance().addToMap(new STAR());
            case "Airfield" -> MapOriginator.getInstance().addToMap(new Airfield());
        }
    }

    @Override
    public void update(double time) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
