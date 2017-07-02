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

public class TeacherLogin extends HttpServlet {

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

		String result = "no"; // 定义结果字符串
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		Sql sql = new Sql();
		String status = sql.teacherLogin(userid, password);
		System.out.println(status);
		if (status == "passworderror") {
			result = "密码错误";
			request.getSession().setAttribute("teacher", result);
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("text/javascript;charset=UTF-8");
			response.sendRedirect("index.jsp");
		}
		if (status == "useriderror") {
			result = "工号错误";
			request.getSession().setAttribute("teacher", result);
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("text/javascript;charset=UTF-8");
			response.sendRedirect("index.jsp");
		}
		if (status == "success") {
			result = "登录成功";
			request.getSession().setAttribute("teacher", result);
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setContentType("text/javascript;charset=UTF-8");
			response.sendRedirect("views/selectcourse.jsp");
		}
	}
}
