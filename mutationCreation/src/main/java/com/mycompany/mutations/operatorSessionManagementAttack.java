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
public class operatorSessionManagementAttack  extends operator {
    public operatorSessionManagementAttack(){
        
    }
    @Override
    public boolean isMutable(Event ev, String PCO){
        return (super.isMutableToPCO(PCO,ev));
    }
    @Override
    public Event mut(Event ev){
        Event newEv=new Event(ev);
        newEv.setLabel("wait");
        return newEv;
    }
    
    
            
    
    @Override
    public Event createVerdictEvent(Event ev){
        Event newEvent= new Event();
        newEvent.setFrom(ev.getTo());
        
        newEvent.setTo(ev.getFrom());
        newEvent.setLigne("Resp");
        newEvent.setCode(500);
        newEvent.setToken("");
        newEvent.setBody("");
        return newEvent;
    }
}
