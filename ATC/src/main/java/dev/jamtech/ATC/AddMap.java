/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
@RestController
@RequestMapping("api/addMap")
public class AddMap {
    
    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> addNewMap(@RequestBody Map payload)
    {
        return new ResponseEntity("Map was added",HttpStatus.CREATED);
    }
    
}
