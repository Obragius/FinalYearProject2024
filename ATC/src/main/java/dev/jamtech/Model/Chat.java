/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.Model;

import java.util.List;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public class Chat {

    public List<Log> getAllComs() {
        return allComs;
    }

    public void setAllComs(List<Log> allComs) {
        this.allComs = allComs;
    }
    
    private List<Log> allComs;
    
    
    
}
