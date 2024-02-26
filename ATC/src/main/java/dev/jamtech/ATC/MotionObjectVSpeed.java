/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotionObjectVSpeed extends CommandObjectAbstract {
    
    public MotionObjectVSpeed(double value, int direction)
    {
        super(value,direction,5.0,17.0);
    }
    
    public MotionObjectVSpeed(double value, int direction, MotionObject target )
    {
        super(value,direction,5.0,17.0,target);
    }
    

    @Override
    public void tick() {
        this.motionObject.changeVSpeed(this.value, this.direction,this.inc,this.maxValue,false);
    }

    @Override
    public boolean update(double time) {
        if (this.motionObject.outcomeAchieved(value, "Height",this.direction))
        {
            this.motionObject.changeVSpeed(value, direction, inc, maxValue, true);
            return true;
        }
        this.tick();
        return false;
    }
    
}
