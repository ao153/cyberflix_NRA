<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="http://localhost:8080/CyberFlix_NRA/stylesheet.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<script
src="https://code.jquery.com/jquery-3.2.1.min.js"
integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
crossorigin="anonymous"></script>
<title>CyberFlix Shopping Cart</title>
</head>
<body>
<div class="w3-container w3-black w3-right-align padding" style = "padding-top:10px">
<h1>Movies In Your Cart</h1>
<p> Here are your current movies </p>
</div>
<br>
<c:if test="${empty films}">
<div class = "w3-card-4 card w3-padding center" >
<i> We Couldn't Find Anything In Your Cart. </i>
<p> Go Back And Add Some Movies! </p>
</div>
</c:if>
<% int i = 0; %>
<c:forEach var="film" items="${requestScope.films}">
<% String source = "http://localhost:8080/CyberFlix_NRA/images/" + i + ".jpeg"; %>
<div class="w3-card-4 center card" style = "height: 315px; width: 700px">
<div class = "w3-row">
    <div class = "w3-cell">
    <a href="${requestScope.detailServlet}?film_title=${film.getTitle()}&source=<%=source%>">
    <img class="w3-image"
         style = "height: 315px; width: 210px"
         src=<%=source%>
         alt="${film.title}"
    >
    </a>
    </div>
<div class = "w3-cell w3-cell-top w3-padding">
<h3><c:out value="${film.getTitle()}"/></h3><br>
<b>Year: </b><c:out value="${film.getReleaseYear()}"/><br>
<b>Rating: </b><c:out value="${film.getRating()}"/><br>
<b>Running Time: </b><c:out value="${film.getLength()}"/><hr>
<c:out value="${film.getDescription()}"/>
<br>
</div>
</div>

<% i ++;
if(i == 9)
i = 0;
%>
</div>
<br> 
</c:forEach>
</body>
</html>