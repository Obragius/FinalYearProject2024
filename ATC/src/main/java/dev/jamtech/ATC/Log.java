/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public class Log {

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public TimeStamp getTime() {
        return time;
    }

    public void setTime(TimeStamp time) {
        this.time = time;
    }

    public GivenActionList getAction() {
        return action;
    }

    public void setAction(GivenActionList action) {
        this.action = action;
    }
    
    private Message message;
    private TimeStamp time;
    private GivenActionList action;
    
}
