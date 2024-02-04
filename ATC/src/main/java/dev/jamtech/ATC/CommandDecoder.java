/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
public class CommandDecoder {
    
    public static List<String> decodeString(String actions)
    {
        return Arrays.asList(actions.split(","));
    }
    
    public static MotionObjectAbstract decodeAction(String string)
    {
        
    }
    
}
