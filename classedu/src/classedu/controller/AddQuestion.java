package classedu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import classedu.sql.Sql;

public class AddQuestion extends HttpServlet{
	
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

        String question = request.getParameter("question");
        String answera = request.getParameter("answera");
        String answerb = request.getParameter("answerb");
        String answerc = request.getParameter("answerc");
        String answerd = request.getParameter("answerd");
        String answer = request.getParameter("answer");
        System.out.println(question+"***"+answer);
        Sql sql = new Sql();
        String result = sql.addQuestion(question,answera,answerb,answerc,answerd,answer);
        System.out.println(result);
        Gson gson = new Gson();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/javascript");
        response.getWriter().print(gson.toJson(result));
    }

}
