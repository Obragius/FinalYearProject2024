/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.Model;


import java.util.Arrays;
import java.util.List;

/**
 * Geographical calculator is a class responsible for calculations regarding
 * the position of {@link MapObject} on the {@link Map}.
 * All the methods are static and are used to move the objects on the map
 * and calculate target positions. 
 * This class stores radius of the Earth in kilometers as one of it's static attributes
 * Some formulas are taken from Bearing and Distance Calculator Calculation Methods (no date). Available at: 
 * http://www.geomidpoint.com/destination/calculation.html} (Accessed: 30 January 2024). 
 * and Latitude Longitude Distance Calculator (no date). Available at: 
 * https://www.omnicalculator.com/other/latitude-longitude-distance (Accessed: 7 February 2024).
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
public class GeographicalCalculator {
    
    static Double R = 6372.7976;
    
    /**
     * This method returns a distance between two points using the longitude and latitude
     * in degrees. This method uses spherical mode. 
     * Method converts all the values to radians using the standard {@link Math} library
     * @param x1 The latitude of the first point
     * @param y1 The longitude of the first point
     * @param x2 The latitude of the second point
     * @param y2 The longitude of the second point
     * @return the distance between the points in metres.
     */
    public static double distanceCalc(Double x1, Double y1, Double x2, Double y2)
    {
        x1 = Math.toRadians(x1);
        y1 = Math.toRadians(y1);
        x2 = Math.toRadians(x2);
        y2 = Math.toRadians(y2);
        Double d = 2 * GeographicalCalculator.R * Math.asin(Math.sqrt(Math.pow(Math.sin((x2 - x1)/2), 2) + Math.cos(x1) * Math.cos(x2) * Math.pow(Math.sin((y2 - y1)/2),2)));
        return d*1000;
    }
    
    
    /**
     * This method returns a List of two points based on starting position, distance and angle.
     * The points must be in degrees
     * Distance must in m
     * Angle should be between 360 and 0
     * This method uses spherical mode. 
     * Method converts all points to radians using the standard {@link Math} library
     * @param x1 The latitude of the starting point
     * @param y1 The longitude of the starting point
     * @param distance The distance from the starting point (Commonly uses the speed assuming m/s and one second of travel)
     * @param angle The bearing from the starting point
     * @return List of (double Latitude,double Longitude) using standard {@link List}
     */
    public static List<Double> coordCalc(Double x1, Double y1, Double distance, Double angle)
    {
        distance = (distance/1000) / GeographicalCalculator.R;
        angle = Math.toRadians(angle);
        x1 = Math.toRadians(x1);
        y1 = Math.toRadians(y1);
        Double x2 = Math.asin(Math.sin(x1)*Math.cos(distance) + Math.cos(x1) * Math.sin(distance) * Math.cos(angle));
        Double y2 = y1 + Math.atan2(Math.sin(angle)*Math.sin(distance)*Math.cos(x1), Math.cos(distance) - Math.sin(x1) * Math.sin(x2));
        x2 = Math.toDegrees(x2);
        y2 = Math.toDegrees(y2);
        //x2 = Math.round(x2 * 100000000.0) /100000000.0;
        //y2 = Math.round(y2 * 100000000.0) /100000000.0;
        return Arrays.asList(x2,y2);
    }
    
    /**
     * This method returns an angle between two points
     * This method uses spherical mode. 
     * Method converts all points to radians using the standard {@link Math} library
     * @param origin the list of latitude and longitude of the first point
     * @param destination the list of latitude and longitude of the second point
     * @return The angle between the given points as a double
     */
    public static double bearingCalc(List<Double> origin, List<Double> destination)
    {
        // There was an issue here, where math.sin only accpts radians
        double X = Math.cos(Math.toRadians(destination.get(0)))*Math.sin(Math.toRadians(Math.abs(origin.get(1)-destination.get(1))));
        double Y = Math.cos(Math.toRadians(origin.get(0))) * Math.sin(Math.toRadians(destination.get(0))) - Math.sin(Math.toRadians(origin.get(0))) * Math.cos(Math.toRadians(destination.get(0))) * Math.cos(Math.toRadians(Math.abs(origin.get(1)-destination.get(1))));
        
        //Resolving issue where horizontal bearing is incorrect
        if (origin.get(1)>destination.get(1))
        {
            return 360 - Math.toDegrees(Math.atan2(X, Y));
        }
        return Math.toDegrees(Math.atan2(X, Y));
    }
    
}
