/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.API;

import dev.jamtech.Model.Behaviour;
import dev.jamtech.Model.Puzzle;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
@RequestMapping("api/addBehaviour")
public class AddBehaviour {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    
    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Puzzle> addBehavior(@RequestBody Map payload)
    {
        Behaviour myBehaviour = new Behaviour();
        int mapID = (int)payload.get("mapID");
        Puzzle myPuzzle = mongoTemplate.find(new Query(Criteria.where("connectedMapID").is(mapID)),Puzzle.class).get(0);
        myPuzzle.addBehaviour(myBehaviour);
        mongoTemplate.update(Puzzle.class).matching(Criteria.where("connectedMapID").is(mapID)).apply(new Update().set("behaviours",myPuzzle.getBehaviours())).first();
        return new ResponseEntity(myPuzzle,HttpStatus.OK);
    }
}
