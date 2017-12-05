<%@ page import="com.mysql.*" %>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import = "edu.txstate.internet.cyberflix.data.db.FilmDAO" %>
<%@ page import = "edu.txstate.internet.cyberflix.data.film.Film" %>
<%@ page import = "edu.txstate.internet.cyberflix.data.DataSource" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<% List<Film> newFilms = DataSource.findNewestFilms(8);%>
<% request.getSession().setAttribute("newFilms", newFilms);%>
<% String sessionID = request.getSession().getId(); %>
<% DataSource.createCartAt(sessionID); %>
<html> 
<head><title>CyberFlix</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="http://localhost:8080/CyberFlix_NRA/stylesheet.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
src="https://code.jquery.com/jquery-3.2.1.min.js"
integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
crossorigin="anonymous"></script>
</head> 
<body> 
<div class="w3-container w3-black w3-left-align padding"  style = "height: 190px">

  <div class = "topleft">Welcome to...</div>
  <img class = "logo"
       src = "http://localhost:8080/CyberFlix_NRA/images/logo.jpg"
       alt = "logo"
       >
  </div>
<br>
<c:set var="alphabet" value="A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z" />
<div class = "w3-bar w3-black" style = "height: 40px">
    <table class = "center">
    <tr>  
        <th class = "w3-bar-item alpha-font">Browse Alphabetically:</th>
            <c:forTokens var="letter" items="${alphabet}" delims=",">
            <th class = "letter-size"><a href = "CyberFlixServlet?alpha=${letter}">${letter}</a></th>
            </c:forTokens>
    </tr>
    </table>
</div>
  <!--  LOGIN CODE -->
  <c:if test = "${DataSource.getUser().getFirstName().equals('Guest')}">
    <a style="color:red;" href="login.jsp" target="_blank">LOGIN</a>
  </c:if>
  <c:if test = "${!DataSource.getUser().getFirstName().equals('Guest')}">
    <h4 style="color:red;">${DataSource.getUser().getFirstName()}</h4>
  </c:if>
  <!-- END LOGIN CODE -->
<div class="center card w3-card-4 w3-section">
    <header class="w3-container color1">
    <h1 class = "w3-text-white" style = "text-shadow: 1px 0px 0 #444">Search</h1>
    </header>
    <form action="http://localhost:8080/CyberFlix_NRA/CyberFlixServlet" method="get">
    <div class="w3-container">
    <label class = "label-pad3">Film Title</label>
    <input type="text" name="film_title">
    <label class = "label-pad3">Film Description</label>
    <input type = "text" name = "film_description">
    <label class = "label-pad">Max Film Rating</label> 
    <input class = "pointer w3-check" type = "radio" name = "film_rating" value = "G">
    <label>G</label>
    <input class = "pointer w3-check" type = "radio" name = "film_rating" value = "PG">
    <label>PG</label>
    <input class = "pointer w3-check" type = "radio" name = "film_rating" value = "PG_13">
    <label>PG-13</label>
    <input class = "pointer w3-check" type = "radio" name = "film_rating" value = "R">
    <label>R</label>
    <input class = "pointer w3-check" type = "radio" name = "film_rating" value = "NC_17">
    <label>NC-17</label>
    <input class = "pointer w3-check" type = "radio" name = "film_rating" value = "X">
    <label>X</label>
    <input class = "pointer w3-check" type = "radio" name = "film_rating" value = "UR" checked>
    <label>Unrated</label><br>
    <label class= "label-pad2">Max Running Time</label>
    <input class = "pointer w3-check" type="radio" name = "length" value = "30"> 
    <label>30</label>
    <input class = "pointer w3-check" type="radio" name = "length" value = "60">
    <label>60</label>
    <input class = "pointer w3-check" type="radio" name = "length" value = "90">
    <label>90</label>
    <input class = "pointer w3-check" type="radio" name = "length" value = "120">
    <label>120</label>
    <input class = "pointer w3-check" type="radio" name = "length" value = "150">
    <label>150</label>
    <input class = "pointer w3-check" type="radio" name = "length" value = "200">
    <label>200</label>
    <input class = "pointer w3-check" type="radio" name = "length" value = "Any" checked>
    <label>Any</label> <br><br>
    </div>
    <button class="w3-button w3-block w3-light-grey w3-right-align" type = "submit" value = "Submit">Submit</button>
    </form>
</div>
<div class = "w3-bar w3-black" style = "height: 40px"> <p class = "w3-bar-item alpha-font" style = "padding-left: 8%"> RECENTLY ADDED</p> </div>
<table class = "center">
<tr>
    <% int i = 0; %>
    <c:forEach var="film" items="${newFilms}" varStatus = "loop">
    <c:if test = "${loop.index == 4}">
        <tr>
    </c:if>
    <td>
    <div class = "gallery container1">
    <%String source ="http://localhost:8080/CyberFlix_NRA/images/" + i + ".jpeg";  %>
<%--     <a href="http://localhost:8080/CyberFlix_NRA/CyberFlixMovieDetailServlet?film_title=${film.getTitle()}&source=<%=source%>"> --%>
    <img class="cover" src=<%=source %> alt="${film.getTitle()}" width="180px" height="270px">
    <div class ="middle">
    <a href="CyberFlixMovieDetailServlet?film_title=${film.getTitle()}&source=<%=source %>">
    	<button class = "w3-button w3-middle-align details" value = "details">More Info</button><br>
    </a>
    <button id="${film.getTitle()}" class = "w3-button w3-middle-align cart" value = "cart">Add to Cart </button>
    </div>
    <div class="desc">${film.getTitle()} (${film.getReleaseYear()})</div>
    </div>
    
    </td>
    <% i++;%>
    </c:forEach>
</tr>
</table>
<script>
$("button.cart").click(function() {
  myUrl = "addCartServlet?addFilm=" + $(this).attr('id'); 
  $.ajax({
        type : "POST",
        url : myUrl,
          datatype: "text",
        success : function(response){},
        error : function(jqXHR, exception){}
   });
})
</script>
</body>
</html>