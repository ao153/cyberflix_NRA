<%@ page import="com.mysql.*" %>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import = "edu.txstate.internet.cyberflix.data.db.FilmDAO" %>
<%@ page import = "edu.txstate.internet.cyberflix.data.film.Film" %>
<%@ page import = "edu.txstate.internet.cyberflix.data.DataSource" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html> 
<head><title>CyberFlix</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="http://localhost:8080/CyberFlix_NRA/stylesheet.css">
</head> 
<body> 

<div class="container w3-black">      
      <img class = "logo"
      	   src="http://localhost:8080/CyberFlix_NRA/images/logo.jpg" 
      	   alt="Logo" 
		   style="width:auto;height:200px"
	  >
	  <div class = "topleft">Welcome to...</div>
</div>
<br>
<div class="w3-card-4 w3-section card center">
	<header class="w3-container color1">
  	<h1 class = "w3-text-white" style = "text-shadow: 1px 0px 0 #444">Search</h1>
	</header>
	<form action="http://localhost:8080/CyberFlix_NRA/CyberFlixServlet" method="get">
	<div class="w3-container">
	<br><label>Film Title</label>
	<input class = "w3-input" type="text" name="film_title">
	<label>Film Description</label>
	<input class = "w3-input" type = "text" name = "film_description">
	<label>Film Rating</label> 
	<input class = "w3-input" type = "text" name = "film_rating">
	<label>Running Time (in minutes)</label><br>
  	<input class = "w3-check" type="checkbox"> 
  	<label>30</label>
  	<input class = "w3-check" type="checkbox">
  	<label>60</label>
  	<input class = "w3-check" type="checkbox">
  	<label>90</label>
  	<input class = "w3-check" type="checkbox">
  	<label>120</label>
  	<input class = "w3-check" type="checkbox">
  	<label>150</label>
  	<input class = "w3-check" type="checkbox">
  	<label>200+</label>
  	<input class = "w3-check" type="checkbox">
  	<label>Any</label> <br><br>
  	</div>
  	<button class="w3-button w3-block w3-light-grey w3-right-align" type = "submit" value = "Submit">Submit</button>
	</form>
	
	<table>
	<jsp:include page="/index.jsp" />
	<c:forEach var = "newFilms" items = "${newFilms}" varStatus = "loop">	
	</c:forEach>
	
	</table>

	
</div>


</body>
</html>