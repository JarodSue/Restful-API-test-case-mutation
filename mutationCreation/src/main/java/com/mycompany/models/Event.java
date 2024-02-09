/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import org.apache.commons.lang3.math.NumberUtils;
/**
 *
 * @author jarod
 */
public class Event {
    public String ligne;
	public  String from;
	public  String to;
	public boolean error;
	public String token;
	private ArrayList<String> params;
	private ArrayList<String> paramsSess;
	private boolean labelAction;
        public String label;
        public boolean isARequestOfUser;
        public String URL;
        public String verb;
        public String key;
        public int code;
        public boolean isAReqForAbstractEvent;
        public boolean mockNeeded;
        public String body;
        public boolean haveAToken;
        public boolean json;
        public String date;
        public Event(){
        }
        
        public static boolean isNumeric(String string) {
            double intValue;


            if(string == null || string.equals("")) {
                System.out.println("String cannot be parsed, it is null or empty.");
                return false;
            }

            try {
                intValue = Double.parseDouble(string);
                return true;
            } catch (NumberFormatException e) {
            }
            return false;
        }
        
       
		
	
        public Event(Event ev){
            this.ligne=ev.getligne();
            this.from=ev.getFrom();
            this.to=ev.getTo();
            this.error=ev.getError();
            this.token=ev.getToken();
            this.params=ev.getParamsWithFromTo();
            this.paramsSess=ev.getParamsWithoutFromTo();
            this.labelAction=ev.getLabelAction();
            this.label=ev.getLabel();
            this.isARequestOfUser=ev.getIsARequestOfUser();
            this.URL=ev.getURL();
            this.verb=ev.getVerb();
            this.isAReqForAbstractEvent=ev.getisAReqForAbstractEvent();
            this.mockNeeded=ev.getMock();
            this.code=ev.getCode();
            this.body=ev.getBody();
            this.haveAToken = ev.token.contains("Bearer");
        }
        
        public boolean isSame(Event ev){
            if(
            (this.from.equals(ev.getFrom())) &&
            (this.to.equals(ev.getTo()) )&&
            (this.error==ev.getError() )&&
            (this.token.equals(ev.getToken()) )&&
            (this.params.equals(ev.getParamsWithFromTo()) )&&
            (this.paramsSess.equals(ev.getParamsWithoutFromTo()) )&&
            (this.labelAction==ev.getLabelAction() )&&
            (this.label.equals(ev.getLabel()) )&&
            (this.isARequestOfUser==ev.getIsARequestOfUser() )&&
            (this.URL.equals(ev.getURL()) )&&
            (this.verb.equals(ev.getVerb()) )&&
            (this.isAReqForAbstractEvent==ev.getisAReqForAbstractEvent() )&&
            (this.mockNeeded==ev.getMock() )&&
            (this.code==ev.getCode() )&&
            (this.body.equals(ev.getBody()) )){
                return true;
            }
            return false;
        }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    public void setFrom(String from) {
        this.from = from;
    }
    
    public void setTo(String to) {
        this.to = to;
    }
    public String getDate() {
        return this.date;
    }
    
    
    public void setIsARequest(boolean input){
         this.isARequestOfUser=input;
    }
    
    

	
	public boolean getLabelAction() {
		return labelAction;
	}

	public void setLabelAction(boolean labelAction) {
		this.labelAction = labelAction;
	}
	
	public ArrayList<String> getParamsWithoutFromTo() {
		return paramsSess;
	}
	
	public ArrayList<String> getParamsWithFromTo() {
		return params;
	}
        public void SetParamsWithoutFromTo(ArrayList<String> params) {
            this.paramsSess=params;
		return ;
	}
	
	public void SetParamsWithFromTo(ArrayList<String> params) {
            this.params=params;
		return ;
	}
        
        
        
        
	public boolean getError() {
		return error;
	}
	public void setError(boolean i) {
		this.error = i;
	}
	public String getFrom() {
		return from;
	}

	public String  getTo() {
		return to;
	}

	public String getligne() {
		return this.ligne;
	}
        
        public String getToken(){
            return this.token;
        }
          public void setToken(String token){
            this.token=token;
        }
        
        public boolean getIsARequestOfUser(){
            return this.isARequestOfUser;
        }
        public void setURL(String i) {
		this.URL = i;
	}
	public String getURL() {
		return URL;
	}
        public void setVerb(String i) {
		this.verb = i;
	}
	public String getVerb() {
		return verb;
	}
        public void setCode(int i) {
		this.code = i;
	}
	public int getCode() {
		return code;
	}
        public void setMock(boolean isAMockNeeded) {
		this.mockNeeded = isAMockNeeded;
	}
         public void setLigne(String ligne) {
		this.ligne = ligne;
	}
	public boolean getMock() {
		return mockNeeded;
	}
        
        public boolean getisAReqForAbstractEvent() {
		return isAReqForAbstractEvent;
	}
        
        public void setisAReqForAbstractEvent(boolean isAReq){
		this.isAReqForAbstractEvent=isAReq;
	}
        
        public boolean isReq() {
            return this.code==0;
	}
         public void setLabel(String s) {
            this.label=s;
	}
          public String getLabel() {
            return this.label;
	}

	/**
	 * Check if the event is a response.
        * @return 
	 */
	public boolean isResp() {
        return this.ligne.contains("esponse") | this.toString().contains("Resp") | this.toString().contains("resp") | this.code!=0;
                
	}


        
           public Event abstractingEvent(){
            Event newEv=new Event();
            if (this.isReq()){
                newEv.isAReqForAbstractEvent=true;
            }
            newEv.URL=this.getURL();
            newEv.ligne="";
            newEv.from=this.getFrom();
            newEv.to=this.getTo();
            newEv.error=this.getError();
            newEv.code=this.code;
            newEv.body=this.body;
            newEv.verb=this.verb;
            newEv.token="";
            newEv.paramsSess=this.getParamsWithoutFromTo();
            newEv.labelAction=this.getLabelAction();
            newEv.isARequestOfUser=this.getIsARequestOfUser();
            newEv.haveAToken = this.token.contains("Bearer");
            newEv.label=this.getLabel();
            return newEv;
        }
           
           public static boolean comparingAbstractEvents(Event first, Event second){
               if(first.getURL()!=null){
                    if(first.getBody().equals(second.getBody())){
                         if(first.getURL().equals(second.getURL())){
                             if(first.getVerb().equals(second.getVerb())){
                                 if(first.getFrom().equals(second.getFrom())){
                                     if(first.getTo().equals(second.getTo())){
                                         if(first.haveAToken==second.haveAToken){
                                             return true;
                                         }
                                     }
                                 }

                             }
                         }
                    }
               }
               else{
                                 if(first.getFrom().equals(second.getFrom())){
                                     if(first.getTo().equals(second.getTo())){
                                         if(first.haveAToken==second.haveAToken){
                                             return true;
                                         }
                                     
                         }
                    }
               }
            return false;
        }

    public void setHaveAToken(boolean b) {
        this.haveAToken=b;
                }
           
    public boolean getHaveAToken() {
        return this.haveAToken;
    }
           
    
}
