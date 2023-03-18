package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AddService;

public class AddHandler implements commandHandler{

	private AddService addService = new AddService();
	
	@Override
	public String handlerAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int n1 = Integer.parseInt(request.getParameter("n1"));
		int n2 = Integer.parseInt(request.getParameter("n2"));
		
		int result = addService.add(n1,n2);
		
		request.setAttribute("result", result);
		request.setAttribute("calc", "덧셈");
		return "/WEB-INF/calc.jsp";
	}
}
