/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
@RestController
@RequestMapping("/api/app")
public class MapController {
    
    @Autowired
    private MapService mapService;
    
    @GetMapping
        
        public ResponseEntity<List<Map>> getAllMaps()
        {
            return new ResponseEntity<List<Map>>(mapService.getMaps(),HttpStatus.OK);
        }
    
}
