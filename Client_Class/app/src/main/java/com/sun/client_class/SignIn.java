package com.sun.client_class;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zuozhiwei on 2017/4/2.
 */

public class SignIn extends AppCompatActivity {

    Button signin,button_nav_question,button_nav_message;
    TextView showsignin;
    private static RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        signin = (Button) findViewById(R.id.button_signin);
        showsignin = (TextView) findViewById(R.id.textview_showsignin);
        button_nav_question = (Button) findViewById(R.id.button_nav_question);
        button_nav_message = (Button) findViewById(R.id.button_nav_massege);
        if(PublicVariable.getSigninstatus().equals("no")){
            showsignin.setText("未签到");
        }else{
            showsignin.setText("已签到");
        }

        button_nav_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SignIn.this,Message.class);
                SignIn.this.startActivity(intent);
                finish();
            }
        });

        button_nav_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SignIn.this,Question.class);
                SignIn.this.startActivity(intent);
                finish();
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、创建请求队列
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                /**2、实例化StringRequest请求
                 *  第一个参数选择Request.Method.GET即get请求方式
                 *  第二个参数的url地址根据文档所给
                 *  第三个参数Response.Listener 请求成功的回调
                 *  第四个参数Response.ErrorListener 请求失败的回调
                 */
                String serverip = PublicVariable.serverip;
                final String userid = PublicVariable.getUserid();
                StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://" + serverip + ":8080/classedu/Signin",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                //String s即为服务器返回的数据
                                Log.d("cylog",s);
                                if("success".equals(s)&&PublicVariable.getSigninstatus().equals("no")){
                                    openToast("签到成功");
                                    showsignin.setText("已签到");
                                    PublicVariable.setSigninstatus("ok");
                                }
                                else if("success".equals(s)&&PublicVariable.getSigninstatus().equals("ok")){
                                    openToast("已签到！");
                                }else{
                                    openToast("签到失败，请重试");
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("cylog", volleyError.getMessage(),volleyError);
                    }
                })
                {
                    protected Map<String,String> getParams(){
                        Map<String,String> user = new HashMap<String, String>();
                        user.put("userid",userid);
                        return user;
                    }
                };
                //3、将请求添加进请求队列
                requestQueue.add(stringRequest);
                signin.setEnabled(false);
            }
        });

    }
    private void openToast(String strMsg){
        Toast.makeText(this, strMsg, Toast.LENGTH_LONG).show();
    }
}
