<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
</head>
<body>
<h1>Welcome, <%= session.getAttribute("userId") %></h1>
<p><a href="logout.jsp">Logout</a></p>
</body>
</html>
