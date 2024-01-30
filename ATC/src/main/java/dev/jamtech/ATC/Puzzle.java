/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import java.util.List;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public class Puzzle {
    
    private PuzzleState puzzleState;
    private PuzzleState puzzleNotLoaded;
    private PuzzleState puzzleLoaded;
    private PuzzleState puzzleStarted;
    private PuzzleState puzzleHintAvailable;
    private PuzzleState puzzleFailed;
    private PuzzleState puzzleFinished;
    
    public void setPuzzleState(PuzzleState state)
    {
        
    }
    
    public void loadPuzzle(int id)
    {
        
    }
    
    public void restartPuzzle()
    {
        
    }
    
    public void showHint()
    {
        
    }
    
    public void showGoals()
    {
        
    }
    
    public List<Observer> getBehavior(double time)
    {
        return null;
    }
    
    
}
