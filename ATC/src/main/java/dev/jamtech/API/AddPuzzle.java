/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.API;

import dev.jamtech.Model.GeoMap;
import dev.jamtech.Model.Puzzle;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
@RequestMapping("api/addPuzzle")
@Service
public class AddPuzzle {
    
    @Autowired
    private PuzzleRepository puzzleRepository;
    
    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<GeoMap> addPuzzle(@RequestBody Map payload)
    {
        Puzzle myPuzzle = new Puzzle();
        int mapID = (int)payload.get("mapID");
        myPuzzle.connectMap(mapID);
        puzzleRepository.insert(myPuzzle);
        return new ResponseEntity(myPuzzle,HttpStatus.CREATED);
    }
}
