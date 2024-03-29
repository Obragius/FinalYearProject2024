/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.Model;

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
public class MotionObjectSpeed extends CommandObjectAbstract {

    
    public MotionObjectSpeed(double value, int direction)
    {
        super(value,direction,5.0,233.0);
    }
    

    @Override
    public void tick() {
        this.motionObject.changeSpeed(this.inc, this.maxValue);
    }

    @Override
    public boolean update(double time) {
        this.tick();
        return false;
    }
    
}
