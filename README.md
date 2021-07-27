# wheretheissAPITesting


ISS API Testing Report

Objective

To create a automated test suite for testing the ISS API to get the current, past, or future position of the ISS and also get TLE data on the ISS

APIs in Scope:
satellites/[id]/positions
satellites/[id]/tles
Test Automation Setup

Created a simple BDD cucumber based framework on Rest Assured(JAVA) for setting up regression testing. Each of the features are logically created based on the core functionality like GET/POST/PUT/DELETE of satellites. 

Each feature has examples to cover, happy, and negative flows

Framework Details:
Runner Class - To run the tests
Feature files - To mention test case scenarios with examples
POJO Helpers - To set and get json objects
Report helper class - To generate extent reports
Step builder and helper classes - For step definition glues and reusable methods

How to run the cases?

Prerequisite :
Install JAVA JDK- 11.0.6"
Install Intellij/Eclipse- a suitable IDE
Install GIT bash/GIT GUI to pull my repo
If your IDE does not have MAVEN already available you can get it from marketplace
You also need to setup cucumber on your IDE-https://www.jetbrains.com/help/idea/enabling-cucumber-support-in-project.html

Steps to Run the test

Create a folder on your local where you want to download the project
Clone the project from GIT with command git clone https://github.com/madhulikamitra/wheretheissAPITesting.git
Once cloned successfully open your IDE and import the project as a Maven project. Your path should be until where the pom.xml resides
Build with Maven-Install, once the project is imported. Wait for all dependencies to get downloaded
Ensure you see no errors in your project, maybe i missed some prerequisite step, so for any issues let me know :)
Right click and Run the test runner file under -APIAutomation/src/test/java/com/automation/hotel/runner/TestRunner.java
Currently all feature files are marked with “Regression” Tag, please change/comment with hash (#) symbol next to the tag if you wish to run individual features
After the run, the reports are present at APIAutomation/test-output/SparkReport
I have placed my generated report in the same folder named as “Final Report_ISS.html”


Techstack
Java, Maven, Rest Assured, BDD,Extent Reports

GIT Repo:https://github.com/madhulikamitra/wheretheissAPITesting 

Assumptions

Please pardon my silly assumptions, since satellites are a very far away topic for me :), it’s all based on some googling I did. 
The minimum velocity should be 27000 kmph
The latitude should be in range of -90 and 90
The longitude should be in range of -180 and 180
The visibility could be any of 3 values (visible, eclipsed, daylight)


PostMan Collection:

All the requests are created and tested via postman. Collection is added in the GIT repo as well
