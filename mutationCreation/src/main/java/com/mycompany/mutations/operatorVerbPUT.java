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
class operatorVerbPUT extends operator{
    
    public operatorVerbPUT(){
        
    }
    
    @Override
    public boolean isMutable(Event ev, String PCO){
        return ((ev.isReq()) && (super.isMutableToPCO(PCO,ev)));
    }
    @Override
    public Event mut(Event ev){
        Event newEv=new Event(ev);
        newEv.setVerb("PUT");
        return newEv;
    }
    
    

    @Override
    public Event createVerdictEvent(Event ev){
        Event newEvent= new Event();
        newEvent.setFrom(ev.getTo());
        newEvent.setTo(ev.getFrom());
        newEvent.setLigne("Resp");
        newEvent.setCode(401);
        newEvent.setToken("");
        newEvent.setBody("");
        return newEvent;
    }
    
}
