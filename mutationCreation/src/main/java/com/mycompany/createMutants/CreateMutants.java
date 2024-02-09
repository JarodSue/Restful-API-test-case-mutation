/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.createMutants;

import com.google.gson.Gson;
import com.mycompany.models.TestCase;
import com.mycompany.mutations.createNewTestCaseMutated;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 *
 * @author blackbow
 */
public class CreateMutants {

    public static void main(String[] args) throws IOException, InterruptedException {
        String json=Files.readString(Path.of("D:/boulot/tests/results/TC0ForPCO[8081].json"));
        TestCase tc=new Gson().fromJson(json, TestCase.class);
        ArrayList<TestCase> newMutatedTestCases=createNewTestCaseMutated.mainTestMutation(tc,Boolean.parseBoolean(args[1]),Boolean.parseBoolean(args[2]),Integer.parseInt(args[3]));
        String PCO=    newMutatedTestCases.get(0).getEventHere().getTo();
        CreatingTestCaseFile.createFile(newMutatedTestCases,PCO,args[0],0);
        
    }
}
