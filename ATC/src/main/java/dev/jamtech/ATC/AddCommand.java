/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
@RestController
@RequestMapping("api/addCommand")
public class AddCommand {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    
    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> addCoomand(@RequestBody Map payload)
    {
        int mapID = (int)payload.get("mapID");
        GeoMap myMap = mongoTemplate.find(new Query(Criteria.where("mapID").is(mapID)),GeoMap.class).get(0);
        Queue.getInstance().reset();
        Queue myQ = Queue.getInstance();
        for (MapObject myObject :myMap.getAllObjects())
        {
            if (myObject instanceof MotionObject)
            {
                MotionObjectAbstract myMotion = (MotionObjectAbstract)myObject;
                String myText = (String)payload.get("text");
                String myId = Integer.toString(myMotion.getId());
                if (myText.equals(myId))
                {
                    return new ResponseEntity("Aircraft found",HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity("Aircraft not found",HttpStatus.OK);
    }
    
}