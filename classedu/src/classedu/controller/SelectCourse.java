package classedu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classedu.sql.Sql;

public class SelectCourse extends HttpServlet {

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

		String selectCourse = request.getParameter("selectCourse");
		String selectClass = request.getParameter("selectClass");
		String targetClass = selectCourse + selectClass;
		String courseName = "";
		String className = "";
		if ("1".equals(selectCourse)) {
			courseName = "高等数学";
		} else {
			courseName = "普通物理";
		}
		if ("1".equals(selectClass)) {
			className = "20001";
		} else {
			className = "20002";
		}
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("courseid", "课程：" + courseName);
		httpSession.setAttribute("classid", "课序：" + className);
		httpSession.setAttribute("targetClass",targetClass);

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/javascript");
		response.sendRedirect("views/signin.jsp?targetClass=" + targetClass);
	}

}
