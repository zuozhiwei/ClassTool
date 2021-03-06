package classedu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import classedu.sql.Sql;

public class AnswerStatus extends HttpServlet{

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

    	String result="no";
        Map<String, String[]> usertemp = request.getParameterMap();
        Map<String,String[]> user = new HashMap<String,String[]>();
        user.putAll(usertemp);
        String userid = user.get("userid")[0];
        String status = user.get("status")[0];
        
        Sql sql = new Sql();
        result = sql.setAnswerStatus(userid, status);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/javascript");
        response.getWriter().print(result);
}

}
