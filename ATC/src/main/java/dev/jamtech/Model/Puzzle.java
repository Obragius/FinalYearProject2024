/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.Model;

import java.util.List;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor

// Puzzles added at v0.8
// These will store connected connectedMapID just like Queue
public class Puzzle {

    public List<Behaviour> getBehaviours() {
        return behaviours;
    }

    public void setBehaviours(List<Behaviour> behaviours) {
        this.behaviours = behaviours;
    }
    
    public void addBehaviour(Behaviour myBehaviour)
    {
        this.behaviours.add(myBehaviour);
    }

    public List<Hint> getHints() {
        return hints;
    }

    public void setHints(List<Hint> hints) {
        this.hints = hints;
    }
    
    private List<Behaviour> behaviours = new ArrayList();
    private List<Hint> hints;
    @Id
    private ObjectId id;
    private int puzzleID = new ObjectId().getTimestamp();
    
    // Puzzle infrastructure built from v0.8
    private int connectedMapID;
    
    public void connectMap(int mapID)
    {
        this.connectedMapID = mapID;
    }
    
    // -------------------------------------
    
    public boolean isComplete()
    {
        return false;
    }
    
    
}
