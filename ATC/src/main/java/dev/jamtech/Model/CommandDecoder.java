/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.Model;

import java.util.Arrays;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * CommandDecoder is a class which allows to decode messages
 * into their correspoding command objects. This is used when
 * ATC gives a text command to an aircraft and that command
 * needs to be performed by the Aircraft.
 * @author Daniels Zazerskis k1801606 <dev.jamtech>
 */
public class CommandDecoder {
    
    /**
     * This method splits the text into individual actions
     * to be decoded by the {@link decodeAction} method
     * @param actions The string containing all of the actions, separated by commas
     * @return The list containing individual actions to be sent for decoding
     */
    public static List<String> splitActions(String actions)
    {
        return Arrays.asList(actions.split(","));
    }
    
    /**
     * This method converts action into a {@link CommandObjectAbstract} by using a
     * manually selected list of possible actions
     * @param action The string containing the action in the form of "Action Number" where
     * Action is the actual expected command and
     * Number is a numeric value as the expected outcome of this action
     * @param target The aircraft which is to perform the action given
     * @return CommandObjectAbstract which contains the target of the command and needs to be registered with {@link Queue} object
     */
    public static CommandObjectAbstract decodeAction(String action, MotionObject target)
    {
        System.out.println(action);
        // Setup list of possible commands
        List<String> patterns = Arrays.asList("turn left heading",
                                              "turn right heading",
                                              "climb and maintain",
                                              "descend and maintain",
                                              "maintain ");
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
            case 2 -> commandGiven = new MotionObjectVSpeed(Integer.parseInt(action.replace("climb and maintain ", "")),0,target);
            case 3 -> commandGiven = new MotionObjectVSpeed(Integer.parseInt(action.replace("descend and maintain ", "")),1,target);
            case 4 -> commandGiven = new MotionObjectAcceleration(Integer.parseInt(action.replace("maintain ","").replace(" knots",""))/1.94384,-1,target);
            default -> commandGiven = null;
        }
        if (match != -1)
        {
            System.out.println("Match found");
        }
        if (match == 4)
        {
            if (commandGiven.getValue() <= ((Aircraft)target).getSpeed())
            {
                commandGiven.setDirection(1);
            }
            else
            {
                commandGiven.setDirection(0);
            }
        }
        return commandGiven;
    }
    
}
