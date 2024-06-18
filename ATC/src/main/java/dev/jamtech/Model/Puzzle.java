/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.Model;

import java.util.List;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public class Puzzle {

    public List<Behavior> getBehaviors() {
        return behaviors;
    }

    public void setBehaviors(List<Behavior> behaviors) {
        this.behaviors = behaviors;
    }

    public List<Hint> getHints() {
        return hints;
    }

    public void setHints(List<Hint> hints) {
        this.hints = hints;
    }
    
    private List<Behavior> behaviors;
    private List<Hint> hints;
    
    public boolean isComplete()
    {
        return false;
    }
    
    
}
