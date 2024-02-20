/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dev.jamtech.ATC;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
@Repository
public interface MapRepository extends MongoRepository<GeoMap, ObjectId>{
    
}
