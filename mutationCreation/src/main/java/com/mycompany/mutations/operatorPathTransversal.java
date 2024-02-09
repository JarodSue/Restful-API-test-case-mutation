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
public class operatorPathTransversal extends operator {
    public operatorPathTransversal(){
        
    }
    @Override
    public boolean isMutable(Event ev, String PCO){
        return ((ev.isReq()) && (super.isMutableToPCO(PCO,ev)));
    }
    @Override
    public Event mut(Event ev){
        Event newEv=new Event(ev);
        if(!newEv.getURL().equals("")){
        if(newEv.getURL().substring(newEv.getURL().length() - 1).equals("/")){
            newEv.setURL(newEv.getURL()+"../../../etc/passwd");
        }
        else{
            newEv.setURL(newEv.getURL()+"/../../../etc/passwd");
        }
        }
        return newEv;
    }
    
    
            
    
    @Override
    public Event createVerdictEvent(Event ev){
        Event newEvent= new Event();
        newEvent.setFrom(ev.getTo());
        
        newEvent.setTo(ev.getFrom());
        newEvent.setLigne("Resp");
        newEvent.setCode(404);
        newEvent.setToken("");
        newEvent.setBody("");
        return newEvent;
    }
}
