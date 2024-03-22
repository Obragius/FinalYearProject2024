/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.API;

import dev.jamtech.Model.CommandObjectAbstract;
import dev.jamtech.Model.GeoMap;
import dev.jamtech.Model.MapObject;
import dev.jamtech.Model.MotionObject;
import dev.jamtech.Model.MotionObjectAbstract;
import dev.jamtech.Model.Observer;
import dev.jamtech.Model.Queue;
import java.util.Map;
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
@RequestMapping("api/tick")
@Service
public class MapTickService {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GeoMap> tickMap(@RequestBody Map payload)
    {
        int mapID = (int)payload.get("mapID");
        GeoMap myMap = mongoTemplate.find(new Query(Criteria.where("mapID").is(mapID)),GeoMap.class).get(0);
        Queue myQ = mongoTemplate.find(new Query(Criteria.where("connectedMapID").is(mapID)),Queue.class).get(0);
        for (MapObject myObject :myMap.getAllObjects())
        {
            if (myObject instanceof MotionObject)
            {
                MotionObjectAbstract myAir = (MotionObjectAbstract)myObject;
                for (Observer myObserver :myQ.getObserverList())
                {
                    if (myObserver instanceof CommandObjectAbstract)
                    {
                        CommandObjectAbstract myCommand = (CommandObjectAbstract)myObserver;
                        if (myCommand.getMotionObject().getId() == myAir.getId())
                        {
                            myCommand.setMotionObject(myAir);
                        }
                    }
                }
            }
        }
        myQ.notifyObservers();
        mongoTemplate.update(GeoMap.class).matching(Criteria.where("mapID").is(mapID)).apply(new Update().set("allObjects",myMap.getAllObjects())).first();
        mongoTemplate.update(Queue.class).matching(Criteria.where("connectedMapID").is(mapID)).apply(new Update().set("observerList",myQ.getObserverList())).first();
        return new ResponseEntity(myMap,HttpStatus.OK);
        
    }
    
}
