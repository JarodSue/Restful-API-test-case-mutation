/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mutations;

import com.mycompany.models.Event;
import com.mycompany.models.TestCase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jarod
 */
public class mutationTokenRemoval {
   public Event deletetoken(Event ev){
       ArrayList<String> params=ev.getParamsWithFromTo();
       ArrayList<String> newParams=new ArrayList<String>();
       for(String param : params){
           if(!param.contains("token")){
               newParams.add(param);
           }
       }
       Event newEv = new Event(ev);
       newEv.SetParamsWithFromTo(newParams);
       return ev;
   }
}



    