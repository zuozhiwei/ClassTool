package com.sun.client_class;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import部分
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import java.util.Date;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    private Button button_login;
    private EditText editText_ip,editText_userid,editText_password;

    private static RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_login=(Button)findViewById(R.id.button_login);
        editText_ip = (EditText) findViewById(R.id.editText_ip);
        editText_userid = (EditText) findViewById(R.id.editText_userid);
        editText_password = (EditText) findViewById(R.id.editText_password);


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1、创建请求队列
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                String serverip = editText_ip.getText().toString();
                final String userid = editText_userid.getText().toString();
                final String password = editText_password.getText().toString();
                PublicVariable.setServerip(serverip);
                PublicVariable.setUserid(userid);
                /**2、实例化StringRequest请求
                 *  第一个参数选择Request.Method.GET即get请求方式
                 *  第二个参数的url地址根据文档所给
                 *  第三个参数Response.Listener 请求成功的回调
                 *  第四个参数Response.ErrorListener 请求失败的回调
                 */
                StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://" + serverip + ":8080/classedu/Login",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                //String s即为服务器返回的数据
                                Log.d("cylog",s);
                                if("success".equals(s)){
                                    openToast("登录成功");
                                    Intent intent = new Intent();
                                    intent.setClass(MainActivity.this,SignIn.class);
                                    MainActivity.this.startActivity(intent);
                                    finish();
                                }else if("useriderror".equals(s)){
                                    openToast("用户名错误");
                                }else if("passworderror".equals(s)){
                                    openToast("密码错误");
                                }else{
                                    openToast("登录失败");
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
                        user.put("password",password);
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
