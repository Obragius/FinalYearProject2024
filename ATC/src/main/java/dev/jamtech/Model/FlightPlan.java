/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.Model;

import java.util.List;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
public class FlightPlan {

    public Airfield getOrigin() {
        return origin;
    }

    public void setOrigin(Airfield origin) {
        this.origin = origin;
    }

    public Airfield getDestination() {
        return destination;
    }

    public void setDestination(Airfield destination) {
        this.destination = destination;
    }

    public List<STAR> getStars() {
        return stars;
    }

    public void setStars(List<STAR> stars) {
        this.stars = stars;
    }
    
    private Airfield origin;
    private Airfield destination;
    private List<STAR> stars;
    
}
