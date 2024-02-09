/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mutations;

import com.mycompany.models.Event;
import java.util.ArrayList;

/**
 *
 * @author jarod
 */
public class alreadyAddedEvent {
    public ArrayList<Event> arrayOfEvent;
    public alreadyAddedEvent(){
        this.arrayOfEvent=new ArrayList<Event>();
    }
    public void addEvent(Event e){
        this.arrayOfEvent.add(e);
    }
    
    public void setArrayOfEvent(ArrayList<Event> e){
        this.arrayOfEvent=e;
    }
    public ArrayList<Event> getArrayOfEvent(Event e){
        return this.arrayOfEvent;
    }
    
    
}
