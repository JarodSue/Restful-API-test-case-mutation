# Restful-API-test-case-mutation

The application is located in the directory mutationCreation The logs used are in the directory AllLogs

In order to use the application, you need to have test cases in json format as can be created by https://github.com/JarodSue/AutomatedTestGeneration.

Then, you need to build the app with maven using maven install

Finally, you need to launch the app in command line, you can found an example of a script that can be used to this usage in mutation creation named
launch mut.

the command line is :

java -jar <jar localisation and name> <result directory> <false or true for using strategy 1> <false or true for using strategy 2> <number maximum of test cases from a single test case for strategy 2>

mkdir -p tests

java -jar target/mutationCreation-1.0-SNAPSHOT-jar-with-dependencies.jar tests false false 0