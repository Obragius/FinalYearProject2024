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
public class GivenActionList extends ActionList {
    
    public List<Observer> generateActionFromMessage(Message message)
    {
        Aircraft target;
        if (message.getOriginator().equals("ATC"))
        {
            GeoMap map = MapOriginator.getInstance().getMap();
            for (MapObject o1 : map.getAllObjects())
            {
                if (o1.getClass().equals("Aircraft"))
                {
                    Aircraft a1 = (Aircraft)o1;
                    if (a1.getCallsign().equals(message.getReciever()))
                    {
                        target = a1;
                    }
                }
            }
        }
        return null;
    }
    
}
