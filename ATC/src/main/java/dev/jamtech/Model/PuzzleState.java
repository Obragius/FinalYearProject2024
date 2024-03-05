/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dev.jamtech.Model;

import java.util.List;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public interface PuzzleState {
    
    public void loadPuzzle(int id);
    public void restartPuzzle();
    public void showHint();
    public void showGoals();
    public List<Observer> getBehavior(int time);
    
}
