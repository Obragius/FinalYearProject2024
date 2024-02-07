/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public class MotionObjectVSpeed extends CommandObjectAbstract {
    
    public MotionObjectVSpeed(double value, int direction)
    {
        super(value,direction,5.0,17.0);
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
