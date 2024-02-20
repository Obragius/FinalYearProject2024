/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class AddAircraft {
    
    @Autowired
    private MapRepository myMapRepository;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Map> addNewAircraft(@RequestBody String xPos, String yPos, ObjectId mapID)
    {
        System.out.println(xPos);
        System.out.println(yPos);
        Aircraft myAir = new Aircraft(Double.parseDouble(xPos),Double.parseDouble(yPos));
        mongoTemplate.update(Map.class).matching(Criteria.where("id").is(mapID)).apply(new Update().push("allObjects").value(myAir)).first();
        return new ResponseEntity(myMapRepository.findById(mapID),HttpStatus.CREATED);
    }
    
}