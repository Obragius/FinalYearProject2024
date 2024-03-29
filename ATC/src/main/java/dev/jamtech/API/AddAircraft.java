/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.API;

import dev.jamtech.Model.Aircraft;
import dev.jamtech.Model.GeoMap;
import dev.jamtech.Model.MapObject;
import dev.jamtech.Model.MotionObjectHeight;
import dev.jamtech.Model.MotionObjectMove;
import dev.jamtech.Model.MotionObjectSpeed;
import dev.jamtech.Model.Queue;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
@RestController
@RequestMapping("api/addAircraft")
@Service
public class AddAircraft {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GeoMap> addNewAircraft(@RequestBody Map payload)
    {
        // Sanitise Aircraft Callsign
        Pattern myPattern = Pattern.compile("<a.*>", Pattern.CASE_INSENSITIVE);
        Matcher myMatcher = myPattern.matcher((String)payload.get("sign"));
        if(myMatcher.find())
        {
            return new ResponseEntity("XSS detected",HttpStatus.NOT_ACCEPTABLE);
        }
        
        int mapID = (int)payload.get("mapID");
        // Check there is no aircraft with this callsign on the current map
        GeoMap myMap = mongoTemplate.find(new Query(Criteria.where("mapID").is(mapID)),GeoMap.class).get(0);
        for (MapObject myObject: myMap.getAllObjects())
        {
            if (myObject instanceof Aircraft)
            {
                Aircraft air1 = (Aircraft)myObject;
                if (air1.getCallsign().equals((String)payload.get("sign")))
                {
                    return new ResponseEntity(myMap,HttpStatus.NOT_ACCEPTABLE);
                }
            }
        }
        Aircraft myAir = new Aircraft((double)payload.get("xPos"),(double)payload.get("yPos"),(int)payload.get("angle"),(double)payload.get("speed"),(int)payload.get("altitude"),(String)payload.get("sign"));
        MotionObjectMove move = new MotionObjectMove(0,0);
        MotionObjectHeight height = new MotionObjectHeight(0.0,0);
        MotionObjectSpeed speed = new MotionObjectSpeed(0.0,0);
        move.setMotionObject(myAir);
        height.setMotionObject(myAir);
        speed.setMotionObject(myAir);
        mongoTemplate.update(GeoMap.class).matching(Criteria.where("mapID").is((int)payload.get("mapID"))).apply(new Update().push("allObjects").value(myAir)).first();
        Queue q1 = mongoTemplate.find(new Query(Criteria.where("connectedMapID").is(mapID)),Queue.class).get(0);
        q1.register(move);
        q1.register(height);
        q1.register(speed);
        mongoTemplate.update(Queue.class).matching(Criteria.where("connectedMapID").is(mapID)).apply(new Update().set("observerList",q1.getObserverList())).first();
        return new ResponseEntity(mongoTemplate.find(new Query(Criteria.where("mapID").is((int)payload.get("mapID"))),GeoMap.class),HttpStatus.CREATED);
    }
    
}