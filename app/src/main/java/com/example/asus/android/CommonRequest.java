package com.example.asus.android;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class CommonRequest {

    private HashMap<String, String> requestParam;

    private String requestCode;

    public CommonRequest() {
        requestCode = "";
        requestParam = new HashMap<>();
    }
    public  void addRequestParam(String paramKey, String paramValue){
        requestParam.put(paramKey, paramValue);
    }
    public String getJsonStr(){
        JSONObject object = new JSONObject();
        JSONObject param = new JSONObject(requestParam);

        try {
            object.put("requestCode", requestCode);
            object.put("requestParam", param);
        } catch (JSONException e) {
            Log.d("yicahng",e.getMessage());
            //e.printStackTrace();
        }
        LogUtil.log(object.toString());

        return object.toString();

    }
}
