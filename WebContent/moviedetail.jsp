<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="http://localhost:8080/CyberFlix_NRA/stylesheet.css">
<title>CyberFlix: Movie Details</title>
</head>
<body>
  <div class="w3-container w3-black w3-left-align padding">
  <h1>  Movie Details</h1>
  <p> Here is the movie you selected </p>
  </div>
  <hr>
<div class="w3-container">
<div class="w3-card-4 card">
	<div class = "w3-row">
		<div class = "w3-cell">
		<img class="img"
			 src="<c:url value="${image}"/>"
			 alt="${film.title}"
		>
		</div>
	<div class = "w3-cell w3-cell-top padding">
	<h3><c:out value="${film.getTitle()}"/></h3>
	<p><b>Category: </b><c:out value="${film.getCategory()}"/></p>
	<p><b>Year: </b><c:out value="${film.getReleaseYear()}"/></p>
	<p><b>Rating: </b><c:out value="${film.getRating()}"/></p>
	<p><b>Running Time: </b><c:out value="${film.getLength()}"/></p>
	<p><c:out value= "${film.getDescription()}"/></p>
	<hr>
	<b>Actors: </b>
	<c:if test="${empty film.getActors()}">
    <i> No Actors Found For This Film. </i>
	</c:if>
	<c:forEach var = "film" items = "${film.getActors()}" varStatus = "loop">
	<c:out value = "${film.getFirstName()} ${film.getLastName()}"/>
	<c:if test="${!loop.last}">,</c:if>
	</c:forEach>
	<p> </p>
	<p> </p>
	</div>
	</div>
</div>
</div>
</body>
</html>