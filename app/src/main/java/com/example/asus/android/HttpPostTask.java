package com.example.asus.android;

import android.os.AsyncTask;

import android.os.Handler;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpPostTask extends AsyncTask<String ,String ,String> {

    private Handler mHandler;
    private CommonRequest request;
    private ResponseHandler rHandler;

    public HttpPostTask(CommonRequest request, Handler mHandler, ResponseHandler rHandler) {
        this.request = request;
        this.mHandler = mHandler;
        this.rHandler = rHandler;

    }


    @Override
    protected String doInBackground(String... params) {
        Log.w("gu", "task doInBackground()");

        StringBuilder resultBuf = new StringBuilder();
        // JSONObject object = new JSONObject();

        //     JSONObject param = new JSONObject(requestParam);
        try {
            URL url = new URL(params[0]);
            Log.w("gu", url.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(80000);
            connection.setReadTimeout(80000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            Log.w("gu", "connection well");
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());

            out.writeBytes(request.getJsonStr());
            out.flush();
            Log.w("gu", "writeParam");
            int respondeCode = connection.getResponseCode();
            if (respondeCode == HttpURLConnection.HTTP_OK) {
                InputStream in = connection.getInputStream();
                BufferedReader  reader = new BufferedReader (new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    resultBuf.append(line);
                }
                return resultBuf.toString();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            //     mHandler.obtainMessage(Constant.HANDLER_HTTP_SEND_FAIL,e.getClass().getName() +":"+e.getMessage()).sendToTarget();
            e.printStackTrace();
        }
        return resultBuf.toString();
    }

    protected void onPostExecute(String result) {
        Log.w("gu", "task onPreExecute()");
        CommonResponse response = new CommonResponse(result);
        Log.w("gu....result",result);
      //  if ("0".equals(response.getResCode()))
        Log.w("gu....response", response.getResCode());
        if ("0".equals(response.getResCode())){
            rHandler.success(response);

        } else
        {
            rHandler.fail(response.getResCode(),response.getResMsg());
            Log.w("gu", "no 0))))))))))");
        }
        Log.w("gu", "***********");

//        if (rHandler != null) {
//
//            if (!"".equals(result)) {
//                Log.w("gu", result);
//
//                CommonResponse response = new CommonResponse(result);
//                if ("0".equals(response.getClass())) {
//                    rHandler.success(response);
//                    Log.w("gu", "***********");
//                } else {
//                    rHandler.fail(response.getResCode(), response.getResMsg());
//                }
//                Log.w("gu", "task onPreExecute()");
//                Log.w("gu", "----------");
//
//            }
//        }
    }
}
