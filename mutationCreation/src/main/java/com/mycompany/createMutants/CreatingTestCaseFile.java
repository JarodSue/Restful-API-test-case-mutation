/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.createMutants;

import com.mycompany.models.Event;
import com.mycompany.models.TestCase;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author jarod
 */
public class CreatingTestCaseFile {
    public static boolean isMultiple;
    public static int numberOfResponses;
    
    public static int createFile(ArrayList<TestCase> test, String PCO, String resultsLocation,int i) throws IOException, InterruptedException {
        int nbTestCreated=0;
        numberOfResponses=0;
        try {
            int numberOfTests=i;
            for(TestCase tc : test){
                TestCase nameTC=test.get(numberOfTests-i);
                    ArrayList<Event> arrayOfRequestAndResponses=nameTC.gettestEvents(); 
                    if(arrayOfRequestAndResponses!=null){
                    nbTestCreated++;
                    
                 //where the results are gonna be saved
                File myObj = new File(resultsLocation+"\\mutantTestNumber"+numberOfTests+"Tests.java");
                if (myObj.createNewFile()) {
                  PrintWriter writer = new PrintWriter(myObj);
                  writer.println(createStringForTestCase(nameTC,numberOfTests));
                  writer.close();
                } else {
                    myObj.delete();
                    //if the results file already exist, delete it and recreate it
                     //File myObj2 = new File("C:\\Users\\jarod\\Documents\\tests\\logsDernier\\logs\\conditionAcc\\testNumber"+numberOfTests+ "ForPCO" + PCO +"Tests.java");               
                     File myObj2 = new File(resultsLocation+"\\mutantTestNumber"+numberOfTests+"Tests.java");
                     PrintWriter writer = new PrintWriter(myObj2);
                  writer.println(createStringForTestCase(nameTC,numberOfTests));
                  writer.close();
                }
                    }
                numberOfTests++;
            }
            
            
              } 
        catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
        

    return nbTestCreated;
}
    
    private static String createStringForTestCase(TestCase oneTestCase,int numberOfTests){
        
        isMultiple=false;
        String PCO=oneTestCase.getPCO();
        int NameForTheFile=numberOfTests;
        int nbTimesMockCalled=0;
            
            ArrayList<Event> allRequestsAndWaitedResponsesArray=oneTestCase.gettestEvents();
            Event firstRequest=allRequestsAndWaitedResponsesArray.get(0);
            ArrayList<String> strToAdd=createStr(oneTestCase,firstRequest, false);
            String strForEachTest=strToAdd.get(0);
            String strForEachMock=strToAdd.get(1);
            String strVerifyingMock="";
            HashMap<String,Integer> hashMapOfUrls=new HashMap<>();
            if(strToAdd.get(2).length()>1){
                String verifyingMock=strToAdd.get(2).substring(0,strToAdd.get(2).length()-1);
                String[] separateverifyingMock= verifyingMock.split(";");
                for(String url :separateverifyingMock){
                    Integer value = hashMapOfUrls.get(url);
                    if (value == null) {
                       value = 1;
                    } else {
                       value += 1;
                    }
                    hashMapOfUrls.put(url,value);
                }
            }
            strVerifyingMock=verifyMock(strVerifyingMock,hashMapOfUrls);
 
            
        String str ="\n" +
"\n"+
"import static com.consol.citrus.actions.ReceiveMessageAction.Builder.receive;\n" +
"import org.mockserver.matchers.Times;\n"+
"import static com.consol.citrus.container.Conditional.Builder.conditional;\n"+
"import static org.junit.jupiter.api.Assertions.assertEquals;\n" +
"import com.consol.citrus.context.TestContext;\n" +
"import com.consol.citrus.annotations.CitrusResource;\n" +
"import static org.testng.Assert.assertTrue;"+
"import com.consol.citrus.annotations.CitrusTest;\n" +
"import static com.consol.citrus.dsl.MessageSupport.MessageHeaderSupport.fromHeaders;\n" +
"import com.consol.citrus.dsl.endpoint.CitrusEndpoints;\n" +
"import static com.consol.citrus.http.actions.HttpActionBuilder.http;\n" +
"import com.consol.citrus.http.client.HttpClient;\n" +
"import com.consol.citrus.http.message.HttpMessageHeaders;\n" +
"import com.consol.citrus.message.MessageType;\n" +
"import com.consol.citrus.testng.spring.TestNGCitrusSpringSupport;\n" +
"import org.junit.jupiter.api.*;\n" +
"import java.util.logging.Logger;\n" +
"import org.mockserver.verify.VerificationTimes;\n" +
"import java.io.FileNotFoundException;\n" +
"import java.util.concurrent.TimeUnit;\n" +
"import org.mockserver.integration.ClientAndServer;\n" +
"import static org.mockserver.integration.ClientAndServer.startClientAndServer;\n" +
"import static org.mockserver.model.HttpRequest.request;\n" +
"import static org.mockserver.model.HttpResponse.response;\n" +
"import org.mockserver.model.Header;"+
"import org.springframework.http.MediaType;\n" +
"import org.testng.annotations.Test;\n"+
"import org.testng.annotations.AfterClass;\n"+
"import org.testng.annotations.BeforeClass;\n"+
"import org.testng.annotations.Test;\n"+
"import java.util.concurrent.TimeUnit;\n"+
"public class testNumber" + NameForTheFile + "ForPCO" + PCO + "Tests extends TestNGCitrusSpringSupport{\n" +
"    @CitrusResource\n"+
"    private TestContext context;\n"+
"     private static ClientAndServer mockServer;\n" +
"private static final Logger log = Logger.getLogger(\"autoGeneratedTests\");\n" +
"\n" +
"    \n" +                            
"        @BeforeClass\n" +
"        public static void setup() {\n" +
"           mockServer = startClientAndServer(1080);\n"+
strForEachMock+
"               };\n" +
                

"\n" +
"\n" +
"    @Test\n" +
"    @CitrusTest\n" +
"    public void testAlgo1() throws FileNotFoundException, InterruptedException{\n"    +            
"          String token=\"\";\n"+   
"          String body=\"\";\n"+   
"          String status=\"\";\n"+   
"                            HttpClient toClient = CitrusEndpoints\n" +
"                                .http()\n" +
"                                .client()\n" +
"                                .requestUrl(\"http://localhost:"+"8080"+"/\")\n" +
"                                .build();\n" +
                 strForEachTest+   
"           verif();\n" +
"               }        \n" +
"\n" +
                
"            public static void verif() {\n";
                if(!isMultiple){
                        str+=
                        strVerifyingMock;
               }
                str+=
"           }\n" +
"\n"+ 
"        @AfterClass \n" +
"        public void stopServer() { \n" +
"            mockServer.stop();\n" +
"        }\n" +
"    } ";
        return str;
        
    }

    private static String verifyMock(String str, HashMap<String, Integer> mapOfPaths){
       for(Entry<String, Integer> entry : mapOfPaths.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            str+=
"        mockServer.verify(\n" +
"                        request().withPath(\""+key+"\"),\n" +
"                        VerificationTimes.exactly("+value+")\n" +
"        ); \n ";
        }
        
        
                    
        return str;
    }
    
    
    
    private static String createStringForEachTestCaseWhenRequestFromUser(Event ev,String str,String token){
        
         if(ev.getLabel().equals("wait")){
                                    str+="\n                TimeUnit.MINUTES.sleep(1); "
                                            + "   \n";
                                }
        str+=
       
"                            $(http()\n" +
"                                .client(toClient)\n" +
"                                .send()\n" +
"                                ."+ev.getVerb().toLowerCase()+"(\""+ev.getURL()+"\")\n" +
                "                                .message()\n" ;
        if(!ev.getBody().equals("")){
            
str+="                                   .header(\"Content-Type\", \"application/json\")\n"
        ;}
        if(!ev.getBody().equals("")){
            
            str+=
    
"                                .body(\""+ev.getBody().replace("\"", "\\\"")+"\")\n";
            
        }
        if(!token.equals("")){
            str+= "                                   .header(\"Authorization\", \""+token+"\")\n";
         }  
        str+="                              .accept(MediaType.ALL_VALUE));\n";
              
        return str;
    }
    private static String createStringForEachTestCaseWhenResponseFromTestedService(Event ev,String str,String token, boolean isMultipleBody){
            str+=
"                            $(receive(toClient)\n" +
"                                .message()\n" +
"                                .type(MessageType.PLAINTEXT)\n"+
"                                .name(\"Response"+numberOfResponses+"\")\n";
                str+="                                .extract(fromHeaders()\n"+
                        "                                    .header(HttpMessageHeaders.HTTP_STATUS_CODE, \"statusCode\")\n";

            if(!token.equals("")){
            str+= "                                   .header(\"Authorization\", \"token\")\n";
         } 
            
            str+=
"));        \n";
 
 
                String Status=Integer.toString(ev.getCode());

                String FirstPossibleAnswer=ev.getBody().replace("\"", "\\\"");

                str+="\n"+
"                variable(\"body\",\n"+
"                \"citrus:message(Response"+numberOfResponses+".body())\");\n"+
"                variable(\"status\", \"${statusCode}\");\n";
                if(!token.equals("")){
                    str+=
"                variable(\"token\", \"${token}\");\n"+
"                token = context.getVariable(\"token\");\n";
                }
                str+=
"                body = context.getVariable(\"body\");\n"+
"                status = context.getVariable(\"status\");\n"+
"                if ((status.equals(\""+ev.getCode()+"\"))";                       
               
                 /*if((!FirstPossibleAnswer.equals("")) && (!FirstPossibleAnswer.contains("timestamp"))){
                     str+="&& (body.equals(\""+FirstPossibleAnswer+"\"))";
                }*/
                 if(!token.equals("")){
                     str+=" && (token.equals(\""+token+"\"))";
                }
                str+=
"){assertTrue(true);}\n"+
"                else {Assumptions.assumeTrue(false ,\"Inconclusive\") ;}\n";
            numberOfResponses++;
        return str;
    }
    
   

    private static String createStringForEachMockServerWhenRequest(String str,String URL,String method,String token){
        
    str+=
            
"               mockServer.when(\n"+
"                    request()\n"+
"                      .withMethod(\""+method+"\")\n"+
"                      .withPath(\""+URL+"\")\n";
            if(!token.equals("")){
            str+= 
"                      .withHeader(\"Authorization\", \""+token+"\")";
         }
            str+=",Times.exactly(1))";
        return str;
    }
    
        private static String createStringForEachMockServerWhenResponse(String str, String bodyOfReturn, String code,String token, int delay){
            
            str+=
"       .respond(\n" +
"                          response()\n" +
"                            .withStatusCode("+Integer.parseInt(code)+")\n";
            if(bodyOfReturn!=null){

                bodyOfReturn=bodyOfReturn.replace("\"", "\\\"");

                    str+="                            .withBody(\""+bodyOfReturn+"\")\n"+
                         "                              .withHeader(\"Content-Type\",\"application/json\")"   ;
            }
            
            if(!token.equals("")){
            str+= "                                   .withHeaders(\n" +
"                      new Header(\"Authorization\", \""+token+"\"))\n";
         }
            str+=
"                            .withDelay(TimeUnit.SECONDS,"+delay+")\n" +
"                        );\n"+

"           \n" +
"\n" 
            ;
            return str;
    }
        
        public static ArrayList<String> createStr( TestCase test, Event firstRequest, boolean multipleBranch){
            boolean isAMockRequest=false;
            String Status="";
            String strForEachTestToReturn="";
            String strForEachVeriyingMock="";
            String strForEachMockToReturn="";
            String FirstPossibleAnswer="";
            Event ev=test.getEventHere();
            ArrayList<String> arrayListOfReturn=new ArrayList<>();
            int nbTimesMockCalled=0;
            String token=ev.getToken();
            if((ev.getTo().equals(firstRequest.getTo()))&&(ev.isReq())){
                
                            strForEachTestToReturn=createStringForEachTestCaseWhenRequestFromUser(ev, strForEachTestToReturn, token);
                            }
                            else if(ev.isReq()){
                                isAMockRequest=true;
                                String URL=ev.getURL();
                                String method=ev.getVerb();
                                strForEachMockToReturn+=createStringForEachMockServerWhenRequest(strForEachMockToReturn,URL,method,token)  ;   
                                nbTimesMockCalled++;
                            }
                            else if((ev.isResp())&&(!ev.getFrom().equals(firstRequest.getTo()))){
                                String str=ev.getBody();
                                String code=Integer.toString(ev.getCode());
                                if(ev.getLabel()!=null){
                                    if(ev.getLabel().equals("wait")){
                                        strForEachMockToReturn+=createStringForEachMockServerWhenResponse(strForEachMockToReturn,str,code,token,60);
                                    }   
                                    else{
                                strForEachMockToReturn+=createStringForEachMockServerWhenResponse(strForEachMockToReturn,str,code,token,1);
                                }
                                }
                                else{
                                strForEachMockToReturn+=createStringForEachMockServerWhenResponse(strForEachMockToReturn,str,code,token,1);
                                }
                            }
                            else if((ev.isResp())&&(ev.getFrom().equals(firstRequest.getTo()))){
                                if(test.getTree().size()==0){
                                    strForEachTestToReturn=createStringForEachTestCaseWhenResponseFromTestedService(ev,strForEachTestToReturn, token,  false);

                                }
                                else{
                                    strForEachTestToReturn=createStringForEachTestCaseWhenResponseFromTestedService(ev,strForEachTestToReturn, token,  false);
                                }
                            }
           
        if(test.getTree().size()>1){
            isMultiple=true;
            strForEachTestToReturn=createStringForEachTestCaseWhenResponseFromTestedService(ev,strForEachTestToReturn, token,  true);
            strForEachTestToReturn+=
"               variable(\"conditionnal\", \"${statusCode}\");\n"+
"               String conditionnal = context.getVariable(\"conditionnal\");\n";
            for(TestCase tc2 : test.getTree()){
                strForEachTestToReturn+=
    "                            if(conditionnal.equals("+tc2.getTree().get(0).getEventHere().getCode()+")){";
                isAMockRequest=true;
                String URL=ev.getURL();
                String method=ev.getVerb();
                strForEachMockToReturn+=createStringForEachMockServerWhenRequest(strForEachMockToReturn,URL,method,token)  ;  
                for(TestCase tc : tc2.getTree()){
                    

                    ArrayList<String> arrayListOfReturnFromRestOfTree=createStr(tc,firstRequest, true);
                    strForEachTestToReturn+="\n"+"               "+arrayListOfReturnFromRestOfTree.get(0);
                    strForEachMockToReturn+="\n"+"               "+arrayListOfReturnFromRestOfTree.get(1);
                     strForEachTestToReturn+="               }\n";
                    if(isAMockRequest){
                        strForEachVeriyingMock+=ev.getURL();
                        strForEachVeriyingMock+=";"+arrayListOfReturnFromRestOfTree.get(2);
                    }
                    else{
                            strForEachVeriyingMock+=arrayListOfReturnFromRestOfTree.get(2);
                        }
                    }
            }
        }
        else{
            if(!test.getTree().isEmpty()){
                TestCase tc = test.getTree().get(0);
                    ArrayList<String> arrayListOfReturnFromRestOfTree=createStr(tc,firstRequest, multipleBranch);
                    
                    strForEachTestToReturn+="\n"+arrayListOfReturnFromRestOfTree.get(0);
                    strForEachMockToReturn+="\n"+arrayListOfReturnFromRestOfTree.get(1);
                    if(isAMockRequest){
                        strForEachVeriyingMock+=ev.getURL();
                        strForEachVeriyingMock+=";"+arrayListOfReturnFromRestOfTree.get(2);
                    }
                    else{
                        strForEachVeriyingMock+=arrayListOfReturnFromRestOfTree.get(2);
                    }
            }
        }
        arrayListOfReturn.add(strForEachTestToReturn);
        arrayListOfReturn.add(strForEachMockToReturn);
        arrayListOfReturn.add(strForEachVeriyingMock);
        return arrayListOfReturn;
    }
}
