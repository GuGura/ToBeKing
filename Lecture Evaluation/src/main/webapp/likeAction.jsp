<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="util.IsWrite"%>
<%@ page import="user.UserDAO"%>
<%@ page import="evaluation.EvaluationDAO"%>
<%@ page import="likey.LikeyDAO"%>
<%@ page import="java.io.PrintWriter" %>
<%!
	public static String getCilentIP(HttpServletRequest request){
		String ip = request.getHeader("X-FORWARDED-FOR");
		if(IsWrite.isWrite(ip)){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(IsWrite.isWrite(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(IsWrite.isWrite(ip)){
			ip = request.getRemoteAddr();
		}
		return ip;
	}
%>
<%
	UserDAO dao = new UserDAO();
	String userID = null;
	if(session.getAttribute("userID") != null){
		userID = (String) session.getAttribute("userID");
	}
	if(userID == null || userID.trim().length() == 0){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 해주세요')");
		script.println("location.href='login.jsp'");
		script.println("</script>");
		script.close();
		return;
	}
	
	request.setCharacterEncoding("utf-8");
	String evaluationID = null;
	if(request.getParameter("evaluationID") != null){
		evaluationID = request.getParameter("evaluationID");
	}
	EvaluationDAO evaluationDAO = new EvaluationDAO();
	LikeyDAO likeyDAO = new LikeyDAO();
	int result =likeyDAO.like(userID, evaluationID, getCilentIP(request));
	if(result == 1){
		result = evaluationDAO.like(evaluationID);
		if(result == 1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('추천이 완료되었습니다.')");
			script.println("location.href = 'index.jsp'");
			script.println("</script>");
			script.close();
			return;
		}else{
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생했습니다.')");
			script.println("history.back()");
			script.println("</script>");
			script.close();
			return;
		}
	}else{
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 추천을 누룬 글입니다.')");
		script.println("history.back()");
		script.println("</script>");
		script.close();
		
	}
%>
