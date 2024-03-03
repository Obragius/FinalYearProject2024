/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
@Document
@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor
public class MotionObjectAcceleration extends CommandObjectAbstract {
    
    public MotionObjectAcceleration(double value, int direction)
    {
        super(value,direction,0.5, 1.5);
    }
    
    public MotionObjectAcceleration(double value, int direction,MotionObject target)
    {
        super(value,direction,0.5, 1.5,target);
    }

    @Override
    public void tick() {
        this.motionObject.changeAcceleration(this.value, this.direction, this.inc, this.maxValue, false);
    }

    @Override
    public boolean update(double time) {
        if (this.motionObject.outcomeAchieved(value, "Speed",this.direction))
        {
            this.motionObject.changeAcceleration(value, direction, inc, maxValue, true);
            return true;
        }
        this.tick();
        return false;
    }
    
}
