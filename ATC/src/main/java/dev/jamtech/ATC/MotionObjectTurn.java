/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public class MotionObjectTurn extends CommandObjectAbstract{
    
    public MotionObjectTurn(double value, int direction)
    {
        super(value,direction,10);
    }

    @Override
    public void tick() {
        this.motionObject.changeDirection(this.value, this.direction,this.inc);
    }

    @Override
    public void update(double time) {
        if (this.motionObject.outcomeAchieved(value, "Turn"))
        {
            Queue.getInstance().unregister(this);
        }
        else
        {
            this.tick();
        }
    }
    
    
}
