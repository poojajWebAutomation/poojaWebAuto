package com.bungii.api.utilityFunctions;

import com.bungii.common.utilities.ApiHelper;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.UrlBuilder;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaymentServices {

    private static String GET_PAYMENT_METHOD = "/api/payment/getpaymentmethods";
    private static String PARTNER_TOKEN ="/api/payment/gettokenforpartner";
    private static LogUtility logger = new LogUtility(AuthServices.class);



    public Response getGetPaymentMethod(String authToken) {
        String apiURL = null;
        String RequestText ="API REQUEST : Get Payment Method by Authtoken : " + authToken;
        apiURL = UrlBuilder.createApiUrl("payment", GET_PAYMENT_METHOD);
        Header header = new Header("AuthorizationToken", authToken);
        Response response = ApiHelper.getRequestForCustomer(apiURL, header);
        ApiHelper.genericResponseValidation(response,RequestText);
        return response;
    }

    public String getPaymentMethodRef(String authToken) {
        String RequestText ="API REQUEST : Get Default Payment Method Reference by Authtoken : " + authToken;

        Response response = getGetPaymentMethod(authToken);
        ApiHelper.genericResponseValidation(response,RequestText);
        JsonPath jsonPathEvaluator = response.jsonPath();
       // response.then().log().body();
        String paymentRef = "";
        ArrayList<HashMap<String,?>> paymentMethods = jsonPathEvaluator.get("PaymentMethods");
        int i =0;
        while (i<paymentMethods.size()){
            HashMap<String,?> keyValue = paymentMethods.get(i);
            Boolean value = (Boolean) keyValue.get("IsDefault");
            if(value==true)
            {
                paymentRef = keyValue.get("PaymentMethodRef").toString();
                break;
            }

            i++;
        }
        if (paymentRef.equalsIgnoreCase("") && paymentMethods.size()>=1){

            paymentRef = paymentMethods.get(0).get("PaymentMethodRef").toString();
            logger.detail("First Payment Method Reference by Authtoken : " + authToken +" is "+paymentRef +" NOTE: CARD IS NOT DAFAULT");

        }
        logger.detail("Default Payment Method Reference by Authtoken : " + authToken +" is "+paymentRef);

        return paymentRef;
    }

    public String GetTokenForPartner(String AccessToken){
        String RequestText ="API REQUEST : GetTokenFor Partner(Get)";
        String apiURL = null;
        apiURL = UrlBuilder.createApiUrl("payment",PARTNER_TOKEN);
       // String AccessToken = (String) cucumberContextManager.getScenarioContext("Partner_Access_Token");

        Response response = ApiHelper.givenPartnerAccess(AccessToken).when().get(apiURL);
        //response.then().log().all();

        JsonPath jsonPathEvaluator = response.jsonPath();
        ApiHelper.genericResponseValidation(response, RequestText);
        return jsonPathEvaluator.get("ClientToken");
        //return response;

    }

}
