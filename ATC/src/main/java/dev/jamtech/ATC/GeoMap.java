/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoMap {

    public List<MapObject> getAllObjects() {
        return allObjects;
    }

    public void setAllObjects(List<MapObject> allObjects) {
        this.allObjects = allObjects;
    }
    
    public void addObjects(MapObject object)
    {
        this.allObjects.add(object);
    }
    
    public void removeObject(MapObject object)
    {
        this.allObjects.remove(object);
    }
    
    @Id
    private ObjectId id;
    private int mapID = new ObjectId().getTimestamp();
    private List<MapObject> allObjects = new ArrayList();
    
}
