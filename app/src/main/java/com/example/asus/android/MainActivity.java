package com.example.asus.android;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;


public class MainActivity extends BaseActivity  {

    private String URL_Login = "http://192.168.56.1:8080/AndroidJson/loginServlet";
    private String URL_Regist = "http://192.168.56.1:8080/AndroidJson/registServlet";
    private String TitleText;
    private String MsgText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_urlcon);
        JPushInterface.setDebugMode(true);
        //开启极光推送的服务代码
        JPushInterface.init(this);

           final EditText editUser = findViewById(R.id.et_account);
           final  EditText editPassword = findViewById(R.id.et_password);

        Button btnLogin = findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(editUser.getText().toString(),editPassword.getText().toString());
            }
        });
        Button btnRegist =  findViewById(R.id.regist);
        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regist(editUser.getText().toString(),editPassword.getText().toString());
            }
        });

    }

//    private  void register(String user,String password){
//        String registerUrlStr = Constant.URL_Register + "?user=" + user + "&password=" + password;
//       // new MyAsyncTask(tvContext).execute(registerUrlStr);
//    }
    private  void login(String name , String password){
        final TextView tvRequest =(TextView) findViewById(R.id.tv_response);
        final TextView tvResponse =(TextView) findViewById(R.id.text_result);

        final CommonRequest request = new CommonRequest();
        request.addRequestParam("name", name);
        request.addRequestParam("password", password);
        sendHttpPostRequest(URL_Login, request, new ResponseHandler(){
            @Override
            public void success(CommonResponse response) {

          //      LoadingDialogUtil.cancelLoading();
                tvRequest.setText(request.getJsonStr());
                tvResponse.setText(response.getResCode() + "\n" + response.getResMsg());
               showDialogUtil_Success();
            }

            @Override
            public void fail(String failCode, String failMsg) {

                tvResponse.setText(request.getJsonStr() + "\n" );
                tvResponse.setText(failCode+"\n"+failMsg);
                Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDialogUtil_Success() {{
        AlertDialog.Builder normalDialog = new AlertDialog.Builder(MainActivity.this);
        normalDialog.setTitle("登录结果");
        normalDialog.setMessage("登录成功啦！！！");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                }
                });
        normalDialog.show();
    }
    }


    private void regist(String name ,String password){
        final TextView tvRequest =(TextView) findViewById(R.id.tv_response);
        final TextView tvResponse =(TextView) findViewById(R.id.text_result);
        final CommonRequest request = new CommonRequest();
        request.addRequestParam("name", name);
        request.addRequestParam("password", password);

        sendHttpPostRequest(URL_Regist, request, new ResponseHandler(){
            @Override
            public void success(CommonResponse response) {

                //      LoadingDialogUtil.cancelLoading();
                tvRequest.setText(request.getJsonStr());
                tvResponse.setText(response.getResCode() + "\n" + response.getResMsg());
                showDialogUtil();
            }

            private void showDialogUtil() {
                AlertDialog.Builder normalDialog = new AlertDialog.Builder(MainActivity.this);
                normalDialog.setTitle("注册结果");
                normalDialog.setMessage("注册成功啦！！！");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }
                        });
//                normalDialog.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
//                    }
//                });
                normalDialog.show();
            }

            @Override
            public void fail(String failCode, String failMsg) {

            }
        });
    }

}
