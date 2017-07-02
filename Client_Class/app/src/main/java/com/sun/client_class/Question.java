package com.sun.client_class;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zuozhiwei on 2017/4/3.
 */

public class Question extends AppCompatActivity {

    private static RequestQueue requestQueue;

    RadioGroup radioGroup;
    RadioButton radioButton, radio_a, radio_b, radio_c, radio_d;
    Button button_submit, button_flushquestion, button_nav_signin, button_nav_message;
    TextView textView, textview_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        //调用刷新题目的方法
        flushQuetion();
        //创建对象
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        button_submit = (Button) findViewById(R.id.button_submit);
        button_flushquestion = (Button) findViewById(R.id.button_flushquestion);
        button_nav_signin = (Button) findViewById(R.id.button_nav_signin);
        button_nav_message = (Button) findViewById(R.id.button_nav_massege);
        textView = (TextView) findViewById(R.id.textview_showquestion);
        textview_status = (TextView) findViewById(R.id.textview_status);
        radio_a = (RadioButton) findViewById(R.id.radio_a);
        radio_b = (RadioButton) findViewById(R.id.radio_b);
        radio_c = (RadioButton) findViewById(R.id.radio_c);
        radio_d = (RadioButton) findViewById(R.id.radio_d);

        button_nav_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Question.this, SignIn.class);
                Question.this.startActivity(intent);
                finish();
            }
        });

        button_nav_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Question.this, Message.class);
                Question.this.startActivity(intent);
                finish();
            }
        });


        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitQuestion();
            }
        });

        button_flushquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flushQuetion();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectRadioBtn();
            }
        });
    }

    private void selectRadioBtn() {
        radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
    }

    private void flushQuetion() {
        //1、创建请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        /**2、实例化StringRequest请求
         *  第一个参数选择Request.Method.GET即get请求方式
         *  第二个参数的url地址根据文档所给
         *  第三个参数Response.Listener 请求成功的回调
         *  第四个参数Response.ErrorListener 请求失败的回调
         */
        String serverip = PublicVariable.serverip;
        final String userid = PublicVariable.userid;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "http://" + serverip + ":8080/classedu/GetQuestion",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("cylog", response.toString());
                        try {
                            String question = response.getString("question");
                            String answera = response.getString("answera");
                            String answerb = response.getString("answerb");
                            String answerc = response.getString("answerc");
                            String answerd = response.getString("answerd");
                            String answer = response.getString("answer");
                            PublicVariable.setAnswer(answer);
                            radioGroup.setVisibility(View.VISIBLE);
                            textView.setText(question);
                            radio_a.setText(answera);
                            radio_b.setText(answerb);
                            radio_c.setText(answerc);
                            radio_d.setText(answerd);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("cylog", volleyError.getMessage(), volleyError);
            }
        });
        //3、将请求添加进请求队列
        requestQueue.add(jsonObjectRequest);
    }

    private void submitQuestion() {
        String checkedAnswer = "";
        try {
            checkedAnswer = radioButton.getText().toString();
        } catch (Exception e) {
            textview_status.setText("请选择！");
        }
        String answer = PublicVariable.getAnswer();
        String status1 = "";

        if (checkedAnswer.equals(answer)) {
            status1 = "yes";
            textview_status.setText("答对了！");
        } else {
            status1 = "error";
            textview_status.setText("答错了！");
        }
        final String status = status1;
        //1、创建请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        final String serverip = PublicVariable.getServerip();
        final String userid = PublicVariable.getUserid();
        /**2、实例化StringRequest请求
         *  第一个参数选择Request.Method.GET即get请求方式
         *  第二个参数的url地址根据文档所给
         *  第三个参数Response.Listener 请求成功的回调
         *  第四个参数Response.ErrorListener 请求失败的回调
         */
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + serverip + ":8080/classedu/AnswerStatus",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //String s即为服务器返回的数据
                        Log.d("cylog", s);
                        if ("success".equals(s)) {
                            openToast("数据同步成功");
                        } else {
                            openToast("数据同步失败");
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
                user.put("status", status);
                return user;
            }
        };
        //3、将请求添加进请求队列
        requestQueue.add(stringRequest);
    }

    private void openToast(String strMsg) {
        Toast.makeText(this, strMsg, Toast.LENGTH_LONG).show();
    }
}