package com.automation.iss.glues.stepBuilder;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojoHelpers.*;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import com.automation.iss.reporterHelper.logReporter;
import org.junit.Assert;
import stepHelper.commonSteps;
import stepHelper.requestBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class stepDefinitions {
    public static Scenario scenario;
    public Map<String, String> headerSet;
    public requestBuilder requestBuilder = new requestBuilder();
    public commonSteps commonSteps = new commonSteps(requestBuilder);

    @Before
    public void before(Scenario scenario) {

        this.scenario = scenario;
    }

    @When("^Test environment is set$")

    public void setUp() {
        RestAssured.baseURI = "https://api.wheretheiss.at/v1/";
        logReporter.logInfo(scenario, "Base URi " + RestAssured.baseURI, "PASS");

    }

    @And("^check if health ping is successful$")

    public void validateHealthOfApp(Map<String, String> params) {


        commonSteps.setHeaders(params);
        commonSteps.getMethodExecution(params);
        commonSteps.validateResponseCode(params.get("httpCode"));
        //   System.clearProperty("currentBookingCountPostCreation");
        //  System.clearProperty("currentBookingCount");
        System.setProperty("satelliteID", commonSteps.requestBuilder.response.then().extract().jsonPath().get("id[0]").toString());

    }

    @Then("^check if satellites are returned specific to ID supplied$")

    public void getSatelliteByID(Map<String, String> params) {

        commonSteps.setHeaders(params);
        commonSteps.getMethodExecution(params);
        commonSteps.validateResponseCode(params.get("httpCode"));
        satelliteResponse satelliteResponse = commonSteps.requestBuilder.response.getBody().as(satelliteResponse.class);
          validateAllSatelliteData(satelliteResponse);
    }

    public void validateMultipleSatellites(){

        JsonPath jsonPath = commonSteps.requestBuilder.response.then()
                .extract().body().jsonPath();
        List<satelliteResponse> satelliteResponses = jsonPath.getList("", satelliteResponse.class);
        if (satelliteResponses.size() > 0) {
            logReporter.logInfo(scenario, "Total Satellites found " + satelliteResponses.size(), "PASS");
            for(satelliteResponse s:satelliteResponses) {
                validateAllSatelliteData(s);
            }
        }
        else {
            logReporter.logInfo(scenario, "No satellites found ,there may be a problem ", "FAIL");
        }
    }

      public void validateAllSatelliteData(satelliteResponse satelliteResponse) {



        logReporter.logInfo(scenario, "name " + satelliteResponse.getName(), "PASS");
        logReporter.logInfo(scenario, "Id " + satelliteResponse.getId(), "PASS");
        Assert.assertTrue("Satellite Id did not match ",(System.getProperty("satelliteID")).equalsIgnoreCase(String.valueOf(satelliteResponse.getId())));
        logReporter.logInfo(scenario, "latitude " + satelliteResponse.getLatitude(), "PASS");
        Assert.assertTrue("latitude is not correct",satelliteResponse.getLatitude()>=-90 &&satelliteResponse.getLatitude()<=90);
        logReporter.logInfo(scenario, "longitude " + satelliteResponse.getLongitude(), "PASS");
          Assert.assertTrue("latitude is not correct",satelliteResponse.getLatitude()>=-180 &&satelliteResponse.getLatitude()<=180);
        logReporter.logInfo(scenario, "altitude" + satelliteResponse.getAltitude(), "PASS");
        logReporter.logInfo(scenario, "velocity" + satelliteResponse.getVelocity(), "PASS");
          Assert.assertTrue("velocity is not correct",satelliteResponse.getVelocity()>27400);
        logReporter.logInfo(scenario, "visibility " + satelliteResponse.getVisibility(), "PASS");
          Assert.assertTrue("visibility is not correct",
                  Stream.of("visible", "daylight", "eclipsed").anyMatch(satelliteResponse.getVisibility()::equalsIgnoreCase));
          logReporter.logInfo(scenario, "footprint" + satelliteResponse.getFootprint(), "PASS");
          logReporter.logInfo(scenario, "timestamp" + satelliteResponse.getTimestamp(), "PASS");
          Assert.assertTrue("Timestamp is not valid",isMilliesValid((satelliteResponse.getTimestamp())));
          logReporter.logInfo(scenario, "daynum " + satelliteResponse.getDaynum(), "PASS");
          logReporter.logInfo(scenario, "solar_lat" + satelliteResponse.getSolar_lat(), "PASS");
          logReporter.logInfo(scenario, "solar_lon" + satelliteResponse.getSolar_lon(), "PASS");
          logReporter.logInfo(scenario, "units " + satelliteResponse.getUnits(), "PASS");
          Assert.assertTrue("Metrics are not in correct unit",satelliteResponse.getUnits().equalsIgnoreCase("miles")||
                  satelliteResponse.getUnits().equalsIgnoreCase("kilometers"));
    }


    public void validateTLEData() {


        tleResponse tleResponse= commonSteps.requestBuilder.response.getBody().as(tleResponse.class);
        logReporter.logInfo(scenario, "requested_timestamp " + tleResponse.getRequested_timestamp() ,"PASS");
        Assert.assertTrue("Requested Timestamp is not valid",isMilliesValid((tleResponse.getRequested_timestamp())));
        logReporter.logInfo(scenario, "tle_timestamp" + tleResponse.getTle_timestamp(), "PASS");
        Assert.assertTrue("Tle Timestamp is not valid",isMilliesValid((tleResponse.getTle_timestamp())));
        logReporter.logInfo(scenario, "id " + tleResponse.getId() ,"PASS");
        Assert.assertTrue("Satellite Id did not match ",
                (System.getProperty("satelliteID")).equalsIgnoreCase(String.valueOf(tleResponse.getId())));
        logReporter.logInfo(scenario, "name" + tleResponse.getName(), "PASS");
        logReporter.logInfo(scenario, "line1" + tleResponse.getLine1() ,"PASS");
        logReporter.logInfo(scenario, "line2" + tleResponse.getLine2() ,"PASS");
    }
public boolean isMilliesValid(long dateInMillies) {

    Date date = new Date(dateInMillies);
    try {
        System.out.println(DateFormat.getDateInstance().format(date));
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);
        cal.setTime(date);
        cal.getTime();
        return true;
    } catch (Exception e) {
        System.out.println("Invalid date");
        return false;
    }

}

    @Then("^check if satellites are returned based on units and timestamps")

    public void getSatellitesByFilter(Map<String, String> params) {

        Map<String, String> queryParamMap = new HashMap<>();

        if (params.containsKey("timestamps") && ((params.get("timestamps") != null))) {
            queryParamMap = commonSteps.mergeMaps(queryParamMap, commonSteps.asMap("timestamps", params.get("timestamps")));
        }
        if (params.containsKey("units") && ((params.get("units") != null))) {
            queryParamMap = (commonSteps.mergeMaps(queryParamMap, commonSteps.asMap("units", params.get("units"))));
        }
        commonSteps.setHeaders(params);
        commonSteps.queryParamMap(queryParamMap);
        commonSteps.getMethodExecution(params);
        if (Boolean.parseBoolean(params.get("ErrorScenario"))) {
            String error = commonSteps.requestBuilder.response.then().extract().path("error").toString().trim();
            Assert.assertTrue("Error Handling is not valid",
                    error.equalsIgnoreCase(params.get("ErrorMessage")));
            logReporter.logInfo(scenario, "Faliure scenario handled ",
                    "PASS");
        } else {
        commonSteps.validateResponseCode(params.get("httpCode"));
        String[] queryParams=queryParamMap.get("timestamps").split(",");
        for(int i=0;i<queryParams.length;i++){
         Assert.assertTrue("Timestamp check failed",
                 commonSteps.requestBuilder.response.then().extract().path("timestamp["+i+"]").toString()
                    .equalsIgnoreCase(queryParams[i]));
            logReporter.logInfo(scenario, "Timestamp matched from filter and response for : "+queryParams[i],
                    "PASS");}
        validateMultipleSatellites();}
    }

 @Then("^check if tle information is returned for a satellite$")

    public void getSatelliteTLEInformationD(Map<String, String> params) {
        commonSteps.setHeaders(params);
        commonSteps.getMethodExecution(params);
     commonSteps.validateResponseCode(params.get("httpCode"));
     if (Boolean.parseBoolean(params.get("ErrorScenario"))) {
         String error = commonSteps.requestBuilder.response.then().extract().path("error").toString();
         Assert.assertTrue("Error Handling is not valid",
                 error.equalsIgnoreCase(params.get("ErrorMessage")));
         logReporter.logInfo(scenario, "Faliure scenario handled ",
                 "PASS");
     }
     else {
         validateTLEData();
     }
    }

    @Then("^check for not allowed methods satellite$")

    public void checkNotAllowedMethods(Map<String, String> params) {
        commonSteps.setHeaders(params);

        switch(params.get("Method")){

            case "POST":
                commonSteps.postMethodExecution(params);
               break;
            case "PUT":
                commonSteps.putMethodExecution(params);
                break;
            case "DELETE":
                commonSteps.deleteMethodExecution(params);
                break;

            case "GET":
                commonSteps.getMethodExecution(params);
                break;

        }

            commonSteps.validateResponseCode(params.get("httpCode"));
            String error = commonSteps.requestBuilder.response.then().extract().path("error").toString();
            Assert.assertTrue("Error Handling is not valid",
                    error.equalsIgnoreCase(params.get("ErrorMessage")));
            logReporter.logInfo(scenario, "Faliure scenario handled ",
                    "PASS");


    }
}
