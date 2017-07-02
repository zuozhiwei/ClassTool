package classedu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import classedu.sql.Sql;

public class Login extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


    	String result="no"; //定义结果字符串
        Map<String, String[]> usertemp = request.getParameterMap();
        Map<String,String[]> user = new HashMap<String,String[]>();
        user.putAll(usertemp);
        String userid = user.get("userid")[0];
        String password = user.get("password")[0];
        System.out.println(user.get("userid")[0]);
        System.out.println(user.get("password")[0]);
        
        request.getSession().setAttribute("userid",userid);       
        Sql sql = new Sql();
        String status = sql.login(userid, password);
        result = status;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/javascript;charset=UTF-8");
        response.getWriter().print(result);
    }
}
