/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
@Document
@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor
public class MotionObjectTurn extends CommandObjectAbstract{
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public MotionObjectTurn(double value, int direction)
    {
        super(value,direction,5,0.0);
    }
    
    public MotionObjectTurn(double value, int direction, MotionObject target)
    {
        super(value,direction,5,target);
    }
    
    // For calculating turning to a point
    public MotionObjectTurn(String nav, MotionObject target, Points points)
    {
        Aircraft a1 = (Aircraft)target;
        NavAid found = new NavAid();
        for (NavAid candidate :points.getAllPoints())
        {
            if (candidate.getName().equals(nav))
            {
                found = candidate;
                break;
            }
        }
        double value = GeographicalCalculator.bearingCalc(a1.getPos(),found.getPos());
        super.setValue(value);
        super.setDirection(0);
        super.setInc(5);
        super.setMotionObject(target);
    }

    @Override
    public void tick() {
        this.motionObject.changeDirection(this.value, this.direction,this.inc);
    }

    @Override
    public boolean update(double time) {
        if (this.motionObject.outcomeAchieved(value, "Turn",this.direction))
        {
            return true;
        }
        this.tick();
        return false;
    }
    
    
}
