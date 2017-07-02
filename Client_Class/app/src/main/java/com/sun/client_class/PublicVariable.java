package com.sun.client_class;

/**
 * Created by zuozhiwei on 2017/4/2.
 */

public class PublicVariable {
    public static String userid;
    public static String serverip;
    public static String answer;
    public static String signinstatus = "no";

    public static String getSigninstatus() {
        return signinstatus;
    }

    public static void setSigninstatus(String signinstatus) {
        PublicVariable.signinstatus = signinstatus;
    }

    public static String getAnswer() {
        return answer;
    }

    public static void setAnswer(String answer) {
        PublicVariable.answer = answer;
    }



    public static String getServerip() {
        return serverip;
    }

    public static void setServerip(String serverip) {
        PublicVariable.serverip = serverip;
    }



    public static String getUserid() {
        return userid;
    }

    public static void setUserid(String userid) {
        PublicVariable.userid = userid;
    }
}
