package com.bungii.api.utilityFunctions;

import com.bungii.common.utilities.ApiHelper;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.UrlBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.collections.map.HashedMap;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GoogleMaps {
    //Move this hard coded value in the Data properties(during AWS environment)
    private static String DISTANCE_MATRIX_API = "https://maps.googleapis.com/maps/api/distancematrix/json";
    //private static String DISTANCE_MATRIX_API = PropertyUtility.getDataProperties("GOOGLE_DISTANCE_BASE_URL");
    private static LogUtility logger = new LogUtility(AuthServices.class);

    public long[] getDurationInTraffic(String[] driverCoordinate, String[] dropCoordinate, String[] stackPickupCoordinate) {
       //old method (refer new one)
        Date date= new Date();
        long epoch = date.getTime();
        String strOrigins = driverCoordinate[0]+","+driverCoordinate[1]+"|"+dropCoordinate[0]+","+dropCoordinate[1];
        String strDestinations = dropCoordinate[0]+","+dropCoordinate[1]+"|"+stackPickupCoordinate[0]+","+stackPickupCoordinate[1];
        Map<String, String> data = new HashedMap();
        String RequestText="API REQUEST : Get duration in Traffic : "+ DISTANCE_MATRIX_API;

        Response response =given()//.log().all()
                .header("User-Agent", "okhttp/3.4.1")
                .header("Content-Type", "x-www-form-urlencoded")
                .header("Accept-Encoding", "gzip")
                .urlEncodingEnabled(true)
                .contentType("x-www-form-urlencoded")
                .param("units", "imperial")
                .param("origins", strOrigins)
                .param("destinations", strDestinations)

                .param("key", "AIzaSyD5z-ZpO46vNQIXTGeNYJSIy9vvlR-ViQI")
                .param("departure_time", String.valueOf(epoch))
                .param("traffic_model","best_guess")
                .param("mode","driving")
                .when()
                .get(DISTANCE_MATRIX_API);

        //response.then().log().all();
        ApiHelper.genericResponseValidation(response,RequestText);
        return  getStackDuration(response);
    }

    public long[] getDurationInTraffic(String[] dropCoordinate, String[] stackPickupCoordinate) {
        //new method
        Date date= new Date();
        long epoch = date.getTime();
        String DropPointA = dropCoordinate[0]+","+dropCoordinate[1];
        String PickupPointB = stackPickupCoordinate[0]+","+stackPickupCoordinate[1];
        Map<String, String> data = new HashedMap();
        String RequestText="API REQUEST : Get duration in Traffic : "+ DISTANCE_MATRIX_API;

        Response response =given()//.log().all()
                .header("User-Agent", "okhttp/3.4.1")
                .header("Content-Type", "x-www-form-urlencoded")
                .header("Accept-Encoding", "gzip")
                .urlEncodingEnabled(true)
                .contentType("x-www-form-urlencoded")
                .param("units", "imperial")
                .param("origins", DropPointA)
                .param("destinations", PickupPointB)

                .param("key", "AIzaSyD5z-ZpO46vNQIXTGeNYJSIy9vvlR-ViQI")
                .param("departure_time", String.valueOf(epoch))
                .param("traffic_model","best_guess")
                .param("mode","driving")
                .when()
                .get(DISTANCE_MATRIX_API);

        //response.then().log().all();
        ApiHelper.genericResponseValidation(response,RequestText);
        //return  getStackDuration(response);
        return getNewStackDuration(response);
    }
    public String getMiles(String pickupAddress, String dropAddress) {
        Date date= new Date();
        long epoch = date.getTime();
        String strOrigins = pickupAddress;
        String strDestinations = dropAddress;
        Map<String, String> data = new HashedMap();
        String RequestText="API REQUEST : Get Distance : "+ DISTANCE_MATRIX_API;

        Response response =given()//.log().all()
                .header("User-Agent", "okhttp/3.4.1")
                .header("Content-Type", "x-www-form-urlencoded")
                .header("Accept-Encoding", "gzip")
                .urlEncodingEnabled(true)
                .contentType("x-www-form-urlencoded")
                .param("units", "imperial")
                .param("origins", strOrigins)
                .param("destinations", strDestinations)

                .param("key", "AIzaSyD5z-ZpO46vNQIXTGeNYJSIy9vvlR-ViQI")
                .param("departure_time", String.valueOf(epoch))
                .param("traffic_model","best_guess")
                .param("mode","driving")
                .when()
                .get(DISTANCE_MATRIX_API);

        ApiHelper.genericResponseValidation(response,RequestText);
        return  getMilesData(response);
    }
    public String getMilesWithLatLong(String[] dropCoordinate, String[] stackPickupCoordinate) {
        Date date= new Date();
        long epoch = date.getTime();
        String strOrigins = dropCoordinate[0]+","+dropCoordinate[1];
        String strDestinations = stackPickupCoordinate[0]+","+stackPickupCoordinate[1];
        Map<String, String> data = new HashedMap();
        String RequestText="API REQUEST : Get Distance : "+ DISTANCE_MATRIX_API;

        Response response =given()//.log().all()
                .header("User-Agent", "okhttp/3.4.1")
                .header("Content-Type", "x-www-form-urlencoded")
                .header("Accept-Encoding", "gzip")
                .urlEncodingEnabled(true)
                .contentType("x-www-form-urlencoded")
                .param("units", "imperial")
                .param("origins", strOrigins)
                .param("destinations", strDestinations)

                .param("key", "AIzaSyD5z-ZpO46vNQIXTGeNYJSIy9vvlR-ViQI")
                .param("departure_time", String.valueOf(epoch))
                .param("traffic_model","best_guess")
                .param("mode","driving")
                .when()
                .get(DISTANCE_MATRIX_API);

        ApiHelper.genericResponseValidation(response,RequestText);
        return  getMilesData(response);
    }
    public String getMilesData(Response response){
        String distance= "";
        JsonPath jsonPathEvaluator = response.jsonPath();
        ArrayList jsonArray = jsonPathEvaluator.getJsonObject("rows");

        HashMap hashMapjsonEleFour = (HashMap) jsonArray.get(0);
        JSONObject jsonEleFour=new JSONObject(hashMapjsonEleFour);
        JSONArray elementsjsonEleFour = jsonEleFour.getJSONArray("elements");
        JSONObject elementelementsjsonEleFourZero = elementsjsonEleFour.getJSONObject(0);
        JSONObject distanceInTraffic =elementelementsjsonEleFourZero.getJSONObject("distance");

        String distanceToDropUp =distanceInTraffic.get("text").toString();
        distance=distanceToDropUp.replace(" km","").replace(" mi","");
        return distance;
    }
    public long[] getStackDuration(Response response){
        long [] timingInformation= new long[2];
        JsonPath jsonPathEvaluator = response.jsonPath();
        ArrayList jsonArray = jsonPathEvaluator.getJsonObject("rows");

        HashMap hashMapjson = (HashMap) jsonArray.get(0);
        JSONObject json=new JSONObject(hashMapjson);
        JSONArray elements = json.getJSONArray("elements");
        JSONObject elementZero = elements.getJSONObject(0);
        JSONObject distanceInTraffic =elementZero.getJSONObject("duration_in_traffic");
        String timeToDropUp =distanceInTraffic.get("value").toString();


        HashMap hashMapjsonEleFour = (HashMap) jsonArray.get(1);
        JSONObject jsonEleFour=new JSONObject(hashMapjsonEleFour);
        JSONArray elementsjsonEleFour = jsonEleFour.getJSONArray("elements");
        JSONObject elementelementsjsonEleFourZero = elementsjsonEleFour.getJSONObject(1);
        JSONObject distanceInTraffic2 =elementelementsjsonEleFourZero.getJSONObject("duration_in_traffic");

        String timeFromDropToNewPickup =distanceInTraffic2.get("value").toString();
        timingInformation[0]=Long.valueOf(timeToDropUp.replace(" mins","").replace(" min",""));
        timingInformation[1]=Long.valueOf(timeFromDropToNewPickup.replace(" mins","").replace(" min",""));
    return timingInformation;
    }

    public long[] getNewStackDuration(Response response){
        long [] timingInformation= new long[2];
        JsonPath jsonPathEvaluator = response.jsonPath();
        ArrayList jsonArray = jsonPathEvaluator.getJsonObject("rows");

        HashMap hashMapjson = (HashMap) jsonArray.get(0);
        JSONObject json=new JSONObject(hashMapjson);
        JSONArray elements = json.getJSONArray("elements");
        JSONObject elementZero = elements.getJSONObject(0);
        JSONObject distanceInTraffic =elementZero.getJSONObject("duration_in_traffic");
        String timeToDropUpAToPickupB =distanceInTraffic.get("value").toString();

        timingInformation[0]=Long.valueOf(timeToDropUpAToPickupB.replace(" mins","").replace(" min",""));
        //timingInformation[1]=Long.valueOf(timeFromDropToNewPickup.replace(" mins","").replace(" min",""));
        return timingInformation;
    }
}
