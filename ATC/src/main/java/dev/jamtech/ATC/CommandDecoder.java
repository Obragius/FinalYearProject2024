/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import java.util.Arrays;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
public class CommandDecoder {
    
    public static List<String> splitActions(String actions)
    {
        return Arrays.asList(actions.split(","));
    }
    
    public static CommandObjectAbstract decodeAction(String action, MotionObject target)
    {
        // Setup list of possible commands
        List<String> patterns = Arrays.asList("turn left heading",
                                              "turn right heading",
                                              "climb and maintain",
                                              "descend and maintain");
        int match = -1;
        for (int i = 0; i < patterns.size(); i++)
        {
            // Setup regex 
            Pattern myPattern = Pattern.compile(patterns.get(i), Pattern.CASE_INSENSITIVE);
            Matcher myMatcher = myPattern.matcher(action);
            if (myMatcher.find())
            {
                match = i;
            }
        }
        CommandObjectAbstract commandGiven;
        switch (match)
        {
            case 0 -> commandGiven = new MotionObjectTurn(Integer.parseInt(action.replace("turn left heading ", "")),1,target);
            case 1 -> commandGiven = new MotionObjectTurn(Integer.parseInt(action.replace("turn right heading ", "")),0,target);
            default -> commandGiven = null;
        }
        return commandGiven;
    }
    
}
