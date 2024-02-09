/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.mycompany.mutations;

import com.mycompany.models.Conversation;
import com.mycompany.models.Event;
import com.mycompany.models.TestCase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*
* @author jarod
*/
public class createNewTestCaseMutated {
    
    public static ArrayList<TestCase> mainTestMutation(TestCase tc, boolean strat1, boolean strat2, int numberOfMutatedTestMaximumPerTestCase){
        // init strategy 2, no impact if strat2 is not choosed
        int numberOfMutatedTestCreatedPerTestCase=0;
        String PCO=tc.getEventHere().getTo();
        
        
        
        
        List<operator> listOfOperators = new ArrayList<>();
        //example of adding operators
        /*List<operator> listOfOperators = new ArrayList<>();
        listOfOperators.add(new operatorSessionManagementAttack());
        listOfOperators.add(new operatorVerbPOST());
        listOfOperators.add(new operatorVerbGET());
        listOfOperators.add(new operatorVerbPUT());
        listOfOperators.add(new operatorVerbDELETE());
        listOfOperators.add(new operatorToken());
        listOfOperators.add(new operatorPathTransversal());*/
        listOfOperators.add(new operatorVerbPOST());
        //loop for each operator, we loop each test cases
        ArrayList<TestCase> allNewTestCases= new ArrayList<>();
        for(operator op : listOfOperators){
            if(tc.getEventHere() != null){
                ArrayList<TestCase> testCasesToAdd = takeABranch(tc, op, new ArrayList<TestCase>(), new ArrayList<TestCase>(), new ArrayList<Event>(), PCO,strat1);
                for(TestCase test : testCasesToAdd){
                    
                    //strat 2, break if we encounter the number maximum of mutated
                    //test cases that can be created from a single TC
                    if((numberOfMutatedTestCreatedPerTestCase==numberOfMutatedTestMaximumPerTestCase) && (strat2)){
                        numberOfMutatedTestCreatedPerTestCase=0;
                        break;
                    }
                    else{
                        numberOfMutatedTestCreatedPerTestCase++;
                    }
                    
                    
                    allNewTestCases.add(test);
                }
            }
        }
       return allNewTestCases;
    }
    
    
    
    private static ArrayList<TestCase> takeABranch(TestCase tc, operator ope,ArrayList<TestCase> arrayTC,ArrayList<TestCase> AllNewTests, ArrayList<Event> alreadyUsedEvent,String PCO, boolean strat1){
        //init the boolean for the first strategy
        boolean b = true;
        
        //first strategy
        if(strat1){
            for(Event isInside : alreadyUsedEvent){
                if(tc.getEventHere().isSame(isInside)){
                    b=false;
                }
            }
        }
        
        if((ope.isMutable(tc.getEventHere(), PCO)) && (b)){
            if(strat1){
                alreadyUsedEvent.add(new Event(tc.getEventHere()));
            }
            //mutate the event
            Event newTCWithOpe=ope.mut(tc.getEventHere());
            
            //create the verdict
            Event verdictOpe=ope.createVerdictEvent(tc.getEventHere());
            
            //create a new test case with the new event and one with the verdict one from the mutated tc
            TestCase newMutatedTC=new TestCase(tc,newTCWithOpe);
            TestCase newVerdictTC=new TestCase(tc,verdictOpe);
            
            //add the verdict to the list of transitions for the mutated event
            ArrayList<TestCase> forVerdict=new ArrayList<TestCase>();
            forVerdict.add(newVerdictTC);
            newMutatedTC.setTree(forVerdict);
            
            //recursion while we're not at the end of a branch of the test advance then we create the test cases backward
            if(!arrayTC.isEmpty()){
                ArrayList<TestCase> toCopy= new ArrayList<TestCase>();
                for(int i=1;i<arrayTC.size();i++){
                    toCopy.add(new TestCase(arrayTC.get(i)));
                }
                TestCase newTC=createNewTc(toCopy, arrayTC.get(0));
                if(!newTC.getTree().isEmpty()){

                    TestCase bellies=newTC.getTree().get(0);

                    while(!bellies.getTree().isEmpty()){
                        
                        bellies=bellies.getTree().get(0);

                    }
                    ArrayList<TestCase> toAdd=new ArrayList<TestCase>();
                    toAdd.add(newMutatedTC);
                    bellies.setTree(toAdd);
                    AllNewTests.add(newTC) ;
                }
                else{
                    ArrayList<TestCase> toAdd=new ArrayList<TestCase>();
                    toAdd.add(newMutatedTC);
                    newTC.setTree(toAdd);
                    AllNewTests.add(newTC) ;
                    
                }
                
            }
            else{
                AllNewTests.add(newMutatedTC) ;
            }
            
            
            
        }
        
        if(!tc.getTree().isEmpty()){
            arrayTC.add(tc);

                for(TestCase branch : tc.getTree()){
                    
                    ArrayList<TestCase> newarrayTC=new ArrayList<TestCase>();
                    for(TestCase tcToCopy : arrayTC){
                        newarrayTC.add(new TestCase(tcToCopy));
                    }
                    for(TestCase tcBranch : takeABranch(branch, ope, newarrayTC, new ArrayList<TestCase>(),alreadyUsedEvent,PCO, strat1)){
                        AllNewTests.add(tcBranch);
                    };
                }
            
                
        }
        return AllNewTests;
    }
        
        
        public static TestCase createNewTc(ArrayList<TestCase> arrayTC, TestCase tcNow){

            
            if(!arrayTC.isEmpty()){
                TestCase testToSave=new TestCase(arrayTC.get(0));
                arrayTC.remove(0);
                TestCase newTestCase= new TestCase(tcNow, true);
                TestCase newTree=createNewTc(arrayTC,testToSave);
                ArrayList<TestCase> toAddNewTCAsTree= new ArrayList<>();
                toAddNewTCAsTree.add(newTree);
                newTestCase.setTree(toAddNewTCAsTree);
                return newTestCase;
                
            }
            else{
                TestCase newTestCase= new TestCase(tcNow, true);
                return newTestCase;
            }
            

            
            
        }

    
    
    

}
