package com.example.asus.android;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class CommonResponse {

    private String resCode = "";
    private String resMsg = "";
    public   HashMap<String ,String> propertyMap;
    public  ArrayList<HashMap<String, String>> mapList;
    public CommonResponse(String result) {

        propertyMap = new HashMap<>();
        mapList = new ArrayList<>();

        try {
            JSONObject resultJson = new JSONObject(result);
            resCode = resultJson.getString("resCode");
            resMsg = resultJson.getString("resMsg");

                JSONObject property  = resultJson.optJSONObject("property");
            if (property!=null){
                parseProperty(property,propertyMap);
            }
            JSONArray list = resultJson.optJSONArray("list");
            if (list!= null){
                parseList(list);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void parseProperty(JSONObject property, HashMap<String, String> targetMap){

        Iterator<?> it = property.keys();
        while (it.hasNext()){
            String key = it.next().toString();
            Object value = property.opt(key);
            targetMap.put(key,value.toString());
        }
    }
    private  void parseList(JSONArray list){
        int i = 0;
        while (i<list.length()){

            HashMap<String, String> map = new HashMap<>();
            try {
                parseProperty(list.getJSONObject(i++), map);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mapList.add(map);
        }
    }
    public  String getResCode(){
        return resCode;
    }
    public  String  getResMsg(){
        return resMsg;
    }
}
