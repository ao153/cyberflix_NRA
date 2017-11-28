<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="http://localhost:8080/CyberFlix_NRA/stylesheet.css">
<title>CyberFlix Search Results</title>
</head>
<body>
  <div class="w3-container w3-black w3-right-align padding">
  <h1>Movies Matching Your Search</h1>
  <p> Here are the movies we found </p>
  </div>
  <hr>

<c:if test="${empty films}">
<div class = "w3-card-4 card w3-padding w3-center" >
<i> We Couldn't Find Anything That Matched Your Search. </i>
<p> Go Back And Try Again! </p>
</div>
</c:if>

<% int i = 0; %>
<c:forEach var="film" items="${requestScope.films}">
<% String source = "http://localhost:8080/CyberFlix_NRA/images/" + i + ".jpeg"; %>
<div class="w3-card-4 card">
	<div class = "w3-row">
		<div class = "w3-cell">
		<a href="${requestScope.detailServlet}?film_title=${film.getTitle()}&source=<%=source%>">
		<img class="w3-image w3-padding"
			 src=<%=source%>
			 alt="${film.title}"
		>
		</a>
		</div>
	<div class = "w3-cell w3-cell-top w3-padding">
	<h3><c:out value="${film.getTitle()}"/></h3>
	<p><b>Year: </b><c:out value="${film.getReleaseYear()}"/></p>
	<p><b>Rating: </b><c:out value="${film.getRating()}"/></p>
	<p><b>Running Time: </b><c:out value="${film.getLength()}"/></p>
	</div>
	</div>
	<br>
	<footer class="w3-container film-desc"><c:out value="${film.getDescription()}"/></footer> <br>
<% i ++;
   if(i == 9)
   	i = 0;
%>
</div>
</c:forEach>
</div>
</body>
</html>