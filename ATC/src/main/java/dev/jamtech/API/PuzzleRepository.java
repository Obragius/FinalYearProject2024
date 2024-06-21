/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dev.jamtech.API;

import dev.jamtech.Model.Puzzle;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
@Repository
public interface PuzzleRepository extends MongoRepository<Puzzle, ObjectId> {
    
}
