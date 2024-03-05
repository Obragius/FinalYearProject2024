/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.API;

import dev.jamtech.Model.GeoMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
@Service
public class MapService {
    
    @Autowired
    private MapRepository mapRepository;
    public List<GeoMap> getMaps()
    {
        return mapRepository.findAll();
    }
    
}
