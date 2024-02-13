/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mutations;

import com.mycompany.models.Event;

/**
 *
 * @author jarod
 */
class operator {    
    
    public operator() {
    }
    
    //return if we can mutate the transition
    public boolean isMutableToPCO(String PCO, Event ev){
        return (ev.getTo().equals(PCO));
    }
    //override for special condition
    public boolean isMutable(Event ev, String PCO){
        return true;
    }
    //override for the new mutated event
     public Event mut(Event ev){
      return ev;
    }
     //override for the verdict that you need
    public Event createVerdictEvent(Event ev){
         return ev;
    }
}
