/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.Model;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public class Behaviour
{
    
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public MotionObject getTarget() {
        return target;
    }

    public void setTarget(MotionObject target) {
        this.target = target;
    }
    private MotionObject target;
    private boolean complete;
    
    public void isComplete(MotionObjectAbstract target)
    {
        
    }
    
    
    
}
