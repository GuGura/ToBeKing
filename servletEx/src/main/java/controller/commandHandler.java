package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface commandHandler {
	
	public String handlerAction(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException; 
		
}
