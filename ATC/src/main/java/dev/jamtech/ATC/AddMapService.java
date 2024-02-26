/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
@Service
public class AddMapService {
    
    @Autowired
    private MapRepository mapRepository;
    
    @Autowired
    private QueueRepository queueRepository;
    
    public GeoMap createMap() 
    {
        GeoMap myMap = new GeoMap();
        mapRepository.insert(myMap);
        Queue q1 = Queue.getInstance();
        q1.reset();
        q1.connectMap(myMap.getMapID());
        queueRepository.insert(q1);
        
        return myMap;
    }
    
}
