package stepHelper;

import com.automation.iss.glues.stepBuilder.stepDefinitions;
import com.automation.iss.reporterHelper.logReporter;
import io.restassured.RestAssured;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class commonSteps {

    public requestBuilder requestBuilder;

    public commonSteps(requestBuilder requestBuilder){
        this.requestBuilder=requestBuilder;
    }

    public  void getMethodExecution(Map<String,String> params){

        String uriPath=params.get("path");
        logReporter.logInfo(stepDefinitions.scenario,"EndPoint "+uriPath,"PASS");
        if(uriPath.contains("{")) {
            requestBuilder.response = requestBuilder.request.pathParam(params.get("pathParam"),
                    params.get(params.get("pathParam"))).get(uriPath);
        }
            else
    requestBuilder.response=requestBuilder.request.get(uriPath);
        logReporter.logInfo(stepDefinitions.scenario,"Response "+requestBuilder.response.body().asString(),"PASS");
}
    public  void deleteMethodExecution(Map<String,String> params){

        String uriPath=params.get("path");
        logReporter.logInfo(stepDefinitions.scenario,"EndPoint "+uriPath,"PASS");
        if(uriPath.contains("{")) {
            requestBuilder.response = requestBuilder.request.pathParam(params.get("pathParam"),
                    params.get(params.get("pathParam"))).delete(uriPath);
        }
        else
            requestBuilder.response=requestBuilder.request.delete(uriPath);
        logReporter.logInfo(stepDefinitions.scenario,"Response "+requestBuilder.response.body().asString(),"PASS");
    }
    public  void postMethodExecution(Map<String,String> params){

        String uriPath=params.get("path");
        logReporter.logInfo(stepDefinitions.scenario,"EndPoint "+uriPath,"PASS");
        requestBuilder.request.header("Content-Type","application/json");
        requestBuilder.request.header("Accept","application/json");
         requestBuilder.response=requestBuilder.request.post(uriPath);
         logReporter.logInfo(stepDefinitions.scenario,"Response "+requestBuilder.response.body().asString(),"PASS");
    }
    public  void putMethodExecution(Map<String,String> params){

        String uriPath=params.get("path");
        logReporter.logInfo(stepDefinitions.scenario,"EndPoint "+uriPath,"PASS");
        requestBuilder.request.header("Content-Type","application/json");
        requestBuilder.request.header("Accept","application/json");
        if(uriPath.contains("{")) {
            requestBuilder.response = requestBuilder.request.pathParam(params.get("pathParam"),
                    params.get(params.get("pathParam"))).put(uriPath);
        }
        else{
        requestBuilder.response=requestBuilder.request.put(uriPath);}
        logReporter.logInfo(stepDefinitions.scenario,"Response "+requestBuilder.response.body().asString(),"PASS");
    }

public  void setHeaders(Map<String, String> params){

    Map<String, String>  headerSet = commonSteps.extractParams(params, "Header");
    if(params.containsKey("AuthorisationHeader")){

        if(params.get("AuthorisationHeader").equalsIgnoreCase("Authorization")){
            headerSet.put("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=");
            logReporter.logInfo(stepDefinitions.scenario,"Added Authorisation method to headers "
                    +"Authorization  "+"Basic YWRtaW46cGFzc3dvcmQxMjM=","PASS");}
        else if(params.get("AuthorisationHeader").equalsIgnoreCase("Cookie")){
            String cookieValue=genCookie();
            headerSet.put("Cookie","token="+cookieValue);
            logReporter.logInfo(stepDefinitions.scenario,"Authorisation cookie method specified"+
                    "Cookie  "+cookieValue,"PASS");}

        else
            logReporter.logInfo(stepDefinitions.scenario,"No Authorisation specified","PASS");
    }

    requestBuilder.request= RestAssured.given().headers(headerSet);

}
    public  void validateResponseCode(String httpCode){

    requestBuilder.response.then().statusCode(Integer.parseInt(httpCode));
    }

    public  void setRequestBody(Object O){
        requestBuilder.request.body(O);
        logReporter.logInfo(stepDefinitions.scenario,"Setting request Body ","PASS");
    }



    public  void queryParamMap(Map<String,String> queryParams){

        queryParams.forEach((k,v)->requestBuilder.request.queryParams(k,v));
        logReporter.logInfo(stepDefinitions.scenario,"Setting query paramerters "+queryParams.toString(),"PASS");
    }
    public String genCookie(){
        HashMap<String, String> authenticator=new HashMap<String, String>();
        authenticator.put("username","admin");
        authenticator.put("password","password123");
      return(  RestAssured.given()
              .header("Content-Type","application/json")
              .header("Accept","application/json")
              .body(authenticator)
                .post("https://restful-booker.herokuapp.com/auth").then().extract().path("token"));
    }

    public  <K,V>Map<K,V> mergeMaps(Map... maps){
        Map<K,V> mergedMap=new HashMap<>();
        Arrays.stream(maps).forEach(m->mergedMap.putAll(m));
        return mergedMap;
    }

    public  <T> Map<String,T> asMap(String key,T value){
       HashMap<String, T> hashMap=new HashMap<>();
       hashMap.put(key,value);
       return  hashMap;
    }

    public static Map<String, String> extractParams(Map<String,String> requestParams,String type){

        return requestParams.entrySet()
                .stream()
                .filter(k->k.getKey().contains(type))
                .collect(Collectors.toMap(map->map.getKey().substring(type.length()),Map.Entry::getValue));
    }
}
