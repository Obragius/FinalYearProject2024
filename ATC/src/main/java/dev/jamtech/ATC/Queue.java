/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Queue {
    
    @Id
    private ObjectId id;
    private List<Observer> observerList;
    private int connectedMapID;
    private static Queue instance;
    private TimeStamp time;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    private int speed;
    
    public void register(Observer observer)
    {
        Iterator<Observer> myIter = this.observerList.listIterator();
        while (myIter.hasNext())
        {
            Observer myObserver = myIter.next();
            if (myObserver.getClass() == observer.getClass())
            {
                CommandObjectAbstract myCommand = (CommandObjectAbstract)myObserver;
                CommandObjectAbstract givenCommand = (CommandObjectAbstract)observer;
                if (myCommand.getMotionObject().getId() == givenCommand.getMotionObject().getId())
                {
                    myIter.remove();
                }
            }
        }
        this.observerList.add(observer);
    }
    
    public void unregister(Observer observer)
    {
        this.observerList.remove(observer);
    }
    
    public void notifyObservers()
    {
        for (int i = 0; i < this.speed; i++)
        {
            boolean unregister;
            Iterator<Observer> myIter = this.observerList.listIterator();
            while (myIter.hasNext())
            {
               Observer myObserver = myIter.next();
               unregister = myObserver.update(this.time.getCurrentTime());
               if (unregister)
               {
                  myIter.remove();
               }
            }
        }
    }
    
    public void reset()
    {
        this.observerList = new ArrayList();
        this.id = new ObjectId();
    }
    
    private Queue()
    {
        this.observerList = new ArrayList();
        this.speed = 1;
        this.time = new TimeStamp();
        this.id = new ObjectId();
    }
    
    private Queue(int mapID)
    {
        this.observerList = new ArrayList();
        this.speed = 1;
        this.time = new TimeStamp();
        this.connectedMapID = mapID;
        this.id = new ObjectId();
    }
    
    public void connectMap(int mapID)
    {
        this.connectedMapID = mapID;
    }
    
    public void setId(ObjectId id)
    {
        this.id = id;
    }
    
    public ObjectId getId()
    {
        return this.id;
    }
    
    public static Queue getInstance()
    {
        if (Queue.instance == null)
        {
            Queue.instance = new Queue();
        }
        return Queue.instance;
    }
    
    @Override
    public String toString()
    {
        return this.observerList.toString();
    }
    
    
    
}
