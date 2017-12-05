<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="http://localhost:8080/CyberFlix_NRA/CyberFlixLoginServlet" method="post">
    <br><label>E-mail Address</label>
    <input class="text" type="email" name="email_address" required>
    <label>Password</label>
    <input class="text" type="password" name="password" required>
    <button type="submit" value ="Submit">Submit</button>
</form>
</body>
</html>