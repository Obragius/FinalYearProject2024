/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.jamtech.ATC;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Daniels Zazerskis K1801606 <dev.jamtech>
 */
public class Queue {
    
    private List<Observer> observerList;
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
    }
    
    private Queue()
    {
        this.observerList = new ArrayList();
        this.speed = 1;
        this.time = new TimeStamp();
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
