/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public class GeographicalCalculator {
    
    static Double R = 6372.7976;
    
    // Great circle distance (Maybe innacurate)
    public static double distanceCalc(Double x1, Double y1, Double x2, Double y2)
    {
        x1 = Math.toRadians(x1);
        y1 = Math.toRadians(y1);
        x2 = Math.toRadians(x2);
        y2 = Math.toRadians(y2);
        Double d = 2 * GeographicalCalculator.R * Math.asin(Math.sqrt(Math.pow(Math.sin((x2 - x1)/2), 2) + Math.cos(x1) * Math.cos(x2) * Math.pow(Math.sin((y2 - y1)/2),2)));
        return d*1000;
    }
    
    public static List<Double> coordCalc(Double x1, Double y1, Double distance, Double angle)
    {
        distance = (distance/1000) / GeographicalCalculator.R;
        angle = Math.toRadians(angle);
        x1 = Math.toRadians(x1);
        y1 = Math.toRadians(y1);
        Double x2 = Math.asin(Math.sin(x1)*Math.cos(distance) + Math.cos(x1) * Math.sin(distance) * Math.cos(angle));
        Double y2 = y1 + Math.atan2(Math.sin(angle)*Math.sin(distance)*Math.cos(x1), Math.cos(distance) - Math.sin(x1) * Math.sin(x2));
        x2 = x2*180/Math.PI;
        y2 = y2*180/Math.PI;
        DecimalFormat df = new DecimalFormat("#.########");
        df.setRoundingMode(RoundingMode.CEILING);
        x2 = Math.round(x2 * 100000000.0) /100000000.0;
        y2 = Math.round(y2 * 100000000.0) /100000000.0;
        return Arrays.asList(x2,y2);
    }
    
}
