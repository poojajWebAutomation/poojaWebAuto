package com.bungii.api.utilityFunctions;

import com.bungii.android.utilityfunctions.DbUtility;
import com.bungii.common.utilities.ApiHelper;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.UrlBuilder;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DriverServices {
    private static String DRIVER_PROFILE = "/api/driver/profile";
    private static LogUtility logger = new LogUtility(DriverServices.class);


    public Response driverProfile(String authToken) {
        String RequestText="API REQUEST : Get Driver Profile by authToken " + authToken;
        String loginURL = null;
        loginURL = UrlBuilder.createApiUrl("driver", DRIVER_PROFILE);
        Header header = new Header("AuthorizationToken", authToken);
        Response response = ApiHelper.getRequestForDriver(loginURL, header);
        ApiHelper.genericResponseValidation(response,RequestText);
        return response;
    }

    public String getDriverRef(String authToken) {
        String RequestText="API REQUEST : Get Driver Reference by authToken " + authToken;
        Response response = driverProfile(authToken);
        ApiHelper.genericResponseValidation(response,RequestText);
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get("DriverProfile.DriverRef");
    }

}
