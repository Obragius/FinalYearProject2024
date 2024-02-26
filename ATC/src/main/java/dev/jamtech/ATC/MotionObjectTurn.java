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
public class MotionObjectTurn extends CommandObjectAbstract{
    
    public MotionObjectTurn(double value, int direction)
    {
        super(value,direction,5,0.0);
    }
    
    public MotionObjectTurn(double value, int direction, MotionObject target)
    {
        super(value,direction,5,target);
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
