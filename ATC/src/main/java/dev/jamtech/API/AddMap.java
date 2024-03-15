/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.API;

import dev.jamtech.Model.GeoMap;
import java.io.FileNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
@RestController
@RequestMapping("api/addMap")
@CrossOrigin(origins = "http://localhost:3000")
public class AddMap {
    
    @Autowired
    private AddMapService myMapService;
    
    @PostMapping
    public ResponseEntity<GeoMap> addNewMap(@RequestBody GeoMap payload) throws FileNotFoundException
    {
        GeoMap newMap = myMapService.createMap();
        return new ResponseEntity(newMap,HttpStatus.CREATED);
    }
    
}
