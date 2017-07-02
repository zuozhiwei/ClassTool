package classedu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import classedu.bean.Config;
import classedu.sql.Sql;

public class GetQuestion extends HttpServlet{

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

    	Map<String,String> question = new HashMap<String,String>();
    	
    	Sql sql = new Sql();
    	if(null==Config.questionNo){
    		Config.questionNo="1";
    	}
    	question = sql.getQuestion(Config.questionNo);
    	Gson gson = new Gson();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/javascript;charset=UTF-8");
        response.getWriter().print(gson.toJson(question));
    }

}