/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import java.util.HashSet;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
@Data
@Document
public class Aircraft extends MotionObjectAbstract {
    
    public static int airNum = 0;

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
    
    public Aircraft(double xPos, double yPos, double speed, Angle angle)
    {
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setSpeed(speed);
        this.setAngle(angle);
        this.setAcceleration(0.0, 0.0);
    }
    
    public Aircraft(double xPos, double yPos, double speed, double height)
    {
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setSpeed(speed);
        this.setvSpeed(0.0, 0.0);
        this.setAcceleration(0.0, 0.0);
        this.setHeight(height);
    }
    
    public Aircraft(double xPos, double yPos, double speed, Angle angle,double acceleration)
    {
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setSpeed(speed);
        this.setAngle(angle);
        this.setAcceleration(acceleration,5);
        
    }
    
    public Aircraft(double xPos, double yPos, double angle)
    {
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.setAngle(new Angle(angle));
        this.setSpeed(100);
        this.setHeight(12000);
        this.setvSpeed(0.0, 0.0);
        this.setAcceleration(0.0, 0.0);
        this.setId(new ObjectId().getTimestamp()+Aircraft.getIdNum());
    }
    
    public static int getIdNum()
    {
        Aircraft.airNum += 1;
        return Aircraft.airNum;
    }
    
    public Aircraft()
    {
        this.setxPos(0.0);
        this.setyPos(0.0);
        this.setAngle(new Angle());
    }
    
    private String callsign;
    private FlightPlan plan;
    private AircraftPath path;
    
}
