package com.bungii.common.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.config.DecoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;

import java.io.File;
import java.util.*;

import static com.bungii.common.manager.ResultManager.error;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AnyOf.anyOf;


public class ApiHelper {
    private static Gson gson;
    private static LogUtility logger = new LogUtility(ApiHelper.class);


    /**
     * Given config for customer app
     *
     * @return
     */
    public static RequestSpecification givenCustConfig() {
        return given().config(RestAssuredConfig.config().decoderConfig(DecoderConfig.decoderConfig().defaultContentCharset("UTF-8")).and().sslConfig(new SSLConfig().relaxedHTTPSValidation()))//.log().body()
                .header("MobileAppVersion", PropertyUtility.getDataProperties("CUST_MOBILE_APP_VERSION"))
                .header("AppVersion", PropertyUtility.getDataProperties("CUST_APP_VERSION"))
                .header("User-Agent", "okhttp/3.4.1")
                .header("Content-Type", "application/json")
                .header("Accept-Encoding", "gzip")
                .header("DeviceID", UUID.randomUUID())//PropertyUtility.getDataProperties("DEVICE_ID"))
                .auth().preemptive().basic(PropertyUtility.getDataProperties("auth.username"), PropertyUtility.getDataProperties("auth.password"));
    }

    /**
     * Given config for Partner Config
     *
     * @return
     */
    public static RequestSpecification givenPartnerConfig(){

        return given()//.log().all()
                //.header("authority", PropertyUtility.getDataProperties("AUTH_URL"))
                .header("accept","application/json, text/plain, */*")
                .header("accept-encoding", "gzip, deflate, br")
                .header("accept-language", "en-US,en;q=0.9")
                .header("content-type", "application/json")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("sec-fetch-dest","empty")
                .header("sec-fetch-mode","cors")
                .header("sec-fetch-site","same-site")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36")//PropertyUtility.getDataProperties("DEVICE_ID"))
                .auth().preemptive().basic(PropertyUtility.getDataProperties("partner.auth.username"), PropertyUtility.getDataProperties("partner.auth.password"));
    }

    /**
     * Given config for Partner Access with AccessToken
     *
     * @return
     */
    public static RequestSpecification givenPartnerAccess(String AccessToken){

        return given()//.log().all()
                //.header("authority", PropertyUtility.getDataProperties("AUTH_URL"))
                .header("accept","application/json, text/plain, */*")
                .header("accept-encoding", "gzip, deflate, br")
                .header("accept-language", "en-US,en;q=0.9")
                .header("appversion",4)
                .header("authorizationtoken",AccessToken)
                .header("content-type", "application/json")
                .header("sec-fetch-dest","empty")
                .header("sec-fetch-mode","cors")
                .header("sec-fetch-site","same-site")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36")//PropertyUtility.getDataProperties("DEVICE_ID"))
                .auth().preemptive().basic(PropertyUtility.getDataProperties("partner.auth.username"), PropertyUtility.getDataProperties("partner.auth.password"));
    }

    /**
     * Given config for Partner Braintree with Authorization
     *
     * @return
     */
    public static RequestSpecification givenPartnerBraintree(String Auth){

        return given()//.log().all()
                //.header("authority", PropertyUtility.getDataProperties("AUTH_URL"))
                .header("accept","*/*")
                .header("accept-encoding", "gzip, deflate, br")
                .header("accept-language", "en-US,en;q=0.9")
                .header("Braintree-Version","2018-05-10")
                .header("Authorization",Auth)
                .header("Connection","keep-alive")
                .header("content-type", "application/json")
                .header("accept-Encoding", "gzip, deflate, br")
                .header("sec-fetch-dest","empty")
                .header("sec-fetch-mode","cors")
                .header("sec-fetch-site","same-site")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:88.0) Gecko/20100101 Firefox/88.0");//PropertyUtility.getDataProperties("DEVICE_ID"))

    }

    /**
     * Given config for driver app
     *
     * @return
     */
    public static RequestSpecification givenDriverConfig() {
        return given().config(RestAssuredConfig.config().decoderConfig(DecoderConfig.decoderConfig().defaultContentCharset("UTF-8")).and().sslConfig(new SSLConfig().relaxedHTTPSValidation()))//.log().body()
                .header("MobileAppVersion", PropertyUtility.getDataProperties("DRIVER_MOBILE_APP_VERSION"))
                .header("AppVersion", PropertyUtility.getDataProperties("DRIVER_APP_VERSION"))
                .header("User-Agent", "okhttp/3.4.1")
                .header("Content-Type", "application/json")
                .header("Accept-Encoding", "gzip")
                .header("DeviceID",  UUID.randomUUID()) //PropertyUtility.getDataProperties("DEVICE_ID"))
                .auth().preemptive().basic(PropertyUtility.getDataProperties("auth.username"), PropertyUtility.getDataProperties("auth.password"));
    }


    /**
     * Post
     *
     * @param Path
     * @param data
     * @return
     */
    public static Response postDetailsForCustomer(String Path, Map<String, String> data) {
        Response response = givenCustConfig().
                body(gson().toJson(data, Map.class)).
                when().
                post(Path);
        return response;
    }


    /**
     * Post
     *
     * @param Path
     * @param data
     * @return
     */
    public static Response postDetailsForDriver(String Path, Map<String, String> data) {
        Response response = givenDriverConfig().
                body(gson().toJson(data, Map.class)).
                when().
                post(Path);
        return response;
    }

    /**
     * Post
     *
     * @param Path
     * @param data
     * @return
     */
    public static Response postRequestForCustomer(String Path, Map<String, String> data) {
        Response response = givenCustConfig().
                body(gson().toJson(data, Map.class)).
                when().
                post(Path);
        return response;
    }

    /**
     * Post
     *
     * @param Path
     * @param data
     * @return
     */
    public static Response postRequestForDriver(String Path, Map<String, String> data) {
        Response response = givenDriverConfig().
                body(gson().toJson(data, Map.class)).
                when().
                post(Path);
        return response;
    }

    /**
     * @param path
     * @param extraHeaders
     * @return
     */
    public static Response getRequestForCustomer(String path, Header... extraHeaders) {
        List<Header> headerList = new LinkedList<Header>();
        if (extraHeaders.length > 0) {
            for (int i = 0; i < extraHeaders.length; i++) {
                headerList.add(extraHeaders[i]);
            }
            Headers header = new Headers(headerList);
            Response response = givenCustConfig().headers(header).
                    when().
                    get(path);
           // response.then().log().body();
            return response;
        } else {
            Response response = givenCustConfig().
                    when().
                    get(path);

           // response.then().log().body();
            return response;
        }
    }

    /**
     * @param path
     * @param extraHeaders
     * @return
     */
    public static Response getRequestForDriver(String path, Header... extraHeaders) {
        List<Header> headerList = new LinkedList<Header>();
        if (extraHeaders.length > 0) {
            for (int i = 0; i < extraHeaders.length; i++) {
                headerList.add(extraHeaders[i]);
            }
            Headers header = new Headers(headerList);
            Response response = givenDriverConfig().headers(header).
                    when().
                    get(path);
          //  response.then().log().body();
            return response;
        } else {
            Response response = givenDriverConfig().
                    when().
                    get(path);
          //  response.then().log().body();
            return response;
        }
    }

    public static Response postDetailsForCustomer(String Path, JSONObject data, Header... extraHeaders) {
        if (extraHeaders.length > 0) {
            List<Header> headerList = new LinkedList<Header>();

            for (int i = 0; i < extraHeaders.length; i++) {
                headerList.add(extraHeaders[i]);
            }

            Headers header = new Headers(headerList);
            Response response = givenCustConfig().headers(header).
                    body(data.toString()).
                    when().
                    post(Path);
         //   response.then().log().body();
            return response;

        } else {
            Response response = givenCustConfig().
                    body(data.toString()).
                    when().
                    post(Path);
          //  response.then().log().body();

            return response;
        }
    }

    public static Response postDetailsForDriver(String Path, JSONObject data, Header... extraHeaders) {
        if (extraHeaders.length > 0) {
            List<Header> headerList = new LinkedList<Header>();

            for (int i = 0; i < extraHeaders.length; i++) {
                headerList.add(extraHeaders[i]);
            }

            Headers header = new Headers(headerList);
            Response response = givenDriverConfig().headers(header).
                    body(data.toString()).
                    when().
                    post(Path);
          //  response.then().log().body();
            return response;

        } else {
            Response response = givenDriverConfig().
                    body(data.toString()).
                    when().
                    post(Path);
        //    response.then().log().body();

            return response;
        }
    }

    public static Response uploadImageOndemand(String Path, JSONObject data, Header authToken) {
        String pickupImage = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("PICKUP_ITEM_IMAGE"));

        Response response = givenCustConfig().header(authToken).param("WalletRef", data.get("WalletRef")).param("EstLoadUnloadTimeInMilliseconds", data.get("EstLoadUnloadTimeInMilliseconds")).param("PickupRequestID", data.get("PickupRequestID")).param("Description", data.get("Description")).param("PaymentMethodID", data.get("PaymentMethodID")).param("IsScheduledPickup", data.get("IsScheduledPickup"))
                .contentType("multipart/form-data")

                .multiPart("ItemImage1", new File(pickupImage))
                .multiPart("Content-Type", "image/jpeg")
                .multiPart("filename", "ItemImage1")
                .multiPart("WalletRef", data.get("WalletRef"))
                .multiPart("EstLoadUnloadTimeInMilliseconds", data.get("EstLoadUnloadTimeInMilliseconds"))
                .multiPart("PickupRequestID", data.get("PickupRequestID"))
                .multiPart("Description", data.get("Description"))
                .multiPart("PaymentMethodID", data.get("PaymentMethodID"))
                .multiPart("IsScheduledPickup", data.get("IsScheduledPickup"))
                .when().post(Path);
        return response;
    }

    //Specify all one time default Gson config
    public static Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gson(gsonBuilder);
        return gson;
    }

    public static Response uploadImage(String Path, JSONObject data, Header authToken) {
        Response response;
        String pickupImage = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("image.folder"),PropertyUtility.getImageLocations("PICKUP_ITEM_IMAGE"));

        if ((Boolean) data.get("IsScheduledPickup")) {
            response = givenCustConfig().header(authToken).param("WalletRef", data.get("WalletRef")).param("EstLoadUnloadTimeInMilliseconds", data.get("EstLoadUnloadTimeInMilliseconds")).param("PickupRequestID", data.get("PickupRequestID")).param("Description", data.get("Description")).param("PaymentMethodID", data.get("PaymentMethodID")).param("IsScheduledPickup", data.get("IsScheduledPickup"))
                    .contentType("multipart/form-data")
                    .multiPart("ItemImage1",new File(pickupImage),"image/jpeg") //new File(pickupImage)
                    .multiPart("Content-Type", "image/jpeg" )
                    .multiPart("filename", "ItemImage1","multipart/form-data")
                    .multiPart("WalletRef", data.get("WalletRef"))
                    .multiPart("EstLoadUnloadTimeInMilliseconds", data.get("EstLoadUnloadTimeInMilliseconds"))
                    .multiPart("PickupRequestID", data.get("PickupRequestID"))
                    .multiPart("Description", data.get("Description"))
                    .multiPart("PaymentMethodID", data.get("PaymentMethodID"))
                    .multiPart("IsScheduledPickup", data.get("IsScheduledPickup"))
                    .multiPart("ScheduledDateTime", data.get("ScheduledDateTime"))
                    .multiPart("PickupNote",data.get("PickupNote"))
                    .when().post(Path);
        } else {
            response = givenCustConfig().header(authToken).param("WalletRef", data.get("WalletRef")).param("EstLoadUnloadTimeInMilliseconds", data.get("EstLoadUnloadTimeInMilliseconds")).param("PickupRequestID", data.get("PickupRequestID")).param("Description", data.get("Description")).param("PaymentMethodID", data.get("PaymentMethodID")).param("IsScheduledPickup", data.get("IsScheduledPickup"))
                    .contentType("multipart/form-data")
                    .multiPart("ItemImage1", new File(pickupImage),"image/jpeg")
                    .multiPart("Content-Type", "image/jpeg")
                    .multiPart("filename", "ItemImage1","multipart/form-data")
                    .multiPart("WalletRef", data.get("WalletRef"))
                    .multiPart("EstLoadUnloadTimeInMilliseconds", data.get("EstLoadUnloadTimeInMilliseconds"))
                    .multiPart("PickupRequestID", data.get("PickupRequestID"))
                    .multiPart("Description", data.get("Description"))
                    .multiPart("PaymentMethodID", data.get("PaymentMethodID"))
                    .multiPart("IsScheduledPickup", data.get("IsScheduledPickup"))
                    .multiPart("PickupNote",data.get("PickupNote"))
                    .when().post(Path);
        }
        return response;
    }

    //Custom Gson config to override Default Gson  configuration
    public static Gson gson(GsonBuilder gsonBuilder) {
        gson = gsonBuilder.create();
        return gson;
    }

    public static void genericResponseValidation(Response response, String RequestText) {
        JsonPath jsonPathEvaluator;
        try {
            jsonPathEvaluator = response.jsonPath();
            HashMap error = jsonPathEvaluator.get("Error");

        if (error == null) {
          //  logger.detail(response.then().log().body()); //temporary checkin
            logger.detail(RequestText + " | Response - Success ");
        }
        else
        {   logger.detail(RequestText + " | Response - Failure ");
            logger.detail(response.then().log().body());
        }
            response.then().statusCode(anyOf(is(200),is(201))); // 201 added due to auth service changes
        }
        catch (JsonPathException ex) {
            logger.error("Lexical error in JSON response " + response.then().log().body() +" for request "+RequestText);
            error("Step should be successful", "Failed due to :  Lexical error in JSON response " +response.then().log().body()+" for request "+RequestText,
                    false);

        }
        catch (AssertionError ex)
        {
            jsonPathEvaluator = response.jsonPath();
            HashMap error = jsonPathEvaluator.get("Error");
            if (error.size()!=0) {
                logger.error("API Call failed : ", " Failed due to : " + error.get("Message").toString());
                error("Step should be successful", "Failed due to :  " + error.get("Message").toString(),
                        false);
            }
            else
            {
                logger.error("API Call failed : ", " API Response is empty. It seems to be queue error. Please reset the queue and try again.");
                error("Step should be successful", "API Response is empty. It see seems to be queue error. Please reset the queue and try again.",
                        false);
            }
        }

    }
}