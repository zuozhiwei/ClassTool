package com.sun.client_class;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by zuozhiwei on 2017/4/4.
 */

public class Message extends AppCompatActivity {
    Button button_msubmit, button_nav_signin, button_nav_question;
    EditText editText_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        button_msubmit = (Button) findViewById(R.id.button_msubmit);
        button_nav_question = (Button) findViewById(R.id.button_nav_question);
        button_nav_signin = (Button) findViewById(R.id.button_nav_signin);
        editText_message = (EditText) findViewById(R.id.editText_message);

        button_nav_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Message.this,SignIn.class);
                Message.this.startActivity(intent);
                finish();
            }
        });

        button_nav_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Message.this,Question.class);
                Message.this.startActivity(intent);
                finish();
            }
        });

        button_msubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String message = editText_message.getText().toString();
                final String serverip = PublicVariable.getServerip();
                final String userid = PublicVariable.getUserid();
                //1、创建请求队列
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                /**2、实例化StringRequest请求
                 *  第一个参数选择Request.Method.GET即get请求方式
                 *  第二个参数的url地址根据文档所给
                 *  第三个参数Response.Listener 请求成功的回调
                 *  第四个参数Response.ErrorListener 请求失败的回调
                 */
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + serverip + ":8080/classedu/SetMessage",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                //String s即为服务器返回的数据
                                Log.d("cylog", s);
                                if ("success".equals(s)) {
                                    openToast("发布成功");
                                } else {
                                    openToast("发布失败");
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("cylog", volleyError.getMessage(), volleyError);
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> user = new HashMap<String, String>();
                        user.put("userid", userid);
                        user.put("message", message);
                        return user;
                    }
                };
                //3、将请求添加进请求队列
                requestQueue.add(stringRequest);
            }
        });
    }
        private void openToast(String strMsg){
            Toast.makeText(this, strMsg, Toast.LENGTH_LONG).show();
        }
}
