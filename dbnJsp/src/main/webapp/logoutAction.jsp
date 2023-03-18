<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="Content-Type" content="text/html">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		session.invalidate(); //세션을 빼앗는다.
	%>
	<script>
		location.href = 'main.jsp';
	</script>
</body>
</html>






