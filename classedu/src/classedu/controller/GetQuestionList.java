package classedu.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import classedu.sql.Sql;

public class GetQuestionList extends HttpServlet {

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

        Gson gson = new Gson();
        Sql sql = new Sql();
        List<Map<String, String>> list = sql.getQuestionList();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/javascript;charset=UTF-8");
        response.getWriter().print(gson.toJson(list));
	}

}
