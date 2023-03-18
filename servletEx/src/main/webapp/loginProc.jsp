<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userId = request.getParameter("userid");
	String userPw = request.getParameter("userpw");
	
	if(userId.equals(userPw)){
		response.sendRedirect("/main.jsp");	
	}else{
		response.sendRedirect("/login.jsp");	
	}
%>