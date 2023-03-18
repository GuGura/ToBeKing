<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	public void init() throws ServletException{
		System.out.println("login.jsp 초기화");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post">
	<input type="text" name="userid"><br>
	<input type="password" name="userpw"><br>
	<input type="submit" value="로그인">
</form>
</body>
</html>