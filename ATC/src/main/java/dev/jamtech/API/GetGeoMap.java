/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.API;

import dev.jamtech.Model.GeoMap;
import dev.jamtech.Model.Queue;
import java.util.Map;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
@RequestMapping("api/getgeomap")
@Service
public class GetGeoMap {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private MapRepository mapRepository;
    
    @Autowired
    private QueueRepository queueRepository;
    
    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GeoMap> getGeoMap(@RequestBody Map payload)
    {
        int mapID = (int)payload.get("mapID");
        GeoMap myMap = mongoTemplate.find(new Query(Criteria.where("mapID").is(mapID)),GeoMap.class,"LockedMap").get(0);
        myMap.setMapID(new ObjectId().getTimestamp());
        myMap.setId(new ObjectId());
        Queue myQ = mongoTemplate.find(new Query(Criteria.where("connectedMapID").is(mapID)),Queue.class,"LockedQueue").get(0);
        myQ.setConnectedMapID(myMap.getMapID());
        myQ.setId(new ObjectId());
        mapRepository.insert(myMap);
        queueRepository.insert(myQ);
        return new ResponseEntity(myMap,HttpStatus.OK);
    }
}
