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

import classedu.bean.Config;
import classedu.sql.Sql;

public class SetQuestionNo extends HttpServlet {

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

		String questionNo = request.getParameter("questionNo");
		System.out.println(questionNo);
		Config.questionNo=questionNo;
		Gson gson = new Gson();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/javascript;charset=UTF-8");
        response.getWriter().print(gson.toJson("success"));
	}

}
