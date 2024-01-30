/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
public class Aircraft extends MotionObjectAbstract {

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public FlightPlan getPlan() {
        return plan;
    }

    public void setPlan(FlightPlan plan) {
        this.plan = plan;
    }

    public AircraftPath getPath() {
        return path;
    }

    public void setPath(AircraftPath path) {
        this.path = path;
    }
    
    private String callsign;
    private FlightPlan plan;
    private AircraftPath path;
    
}
