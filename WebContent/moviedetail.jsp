<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
<title>CyberFlix: Movie Details</title>
</head>
<body>
<div class="w3-container w3-black w3-left-align padding" style = "padding-top:10px;padding-left:30px;">
<h1><b>  Movie Details </b></h1>
<h4> Here is the movie you selected </h4>
<!-- shopping cart icon -->
<% String sessionID = request.getSession().getId(); %>
<c:set var="sessionID" value="<%=sessionID%>" />
<c:if test="${DataSource.getCart(sessionID).getSize() != 0}">
<p class = "w3-left-align detailCartStuff"> <a id="cartLink" href="CyberFlixCartServlet" class ="dCS2">Shopping Cart
<i class="material-icons" style="font-size:30px;vertical-align:middle;color:#B82601;">add_shopping_cart</i>
</a>
</p>
</c:if>
<!-- end shopping cart icon -->
</div>
<br>
<div class="w3-container">
<div class="w3-card-4 card center"style = "height: 410px; width: 900px;">
<div class = "w3-row">
    <div class = "w3-cell">
    <img class="img"
         src="<c:url value="${image}"/>"
         alt="${film.title}"
         style = "height: 410px; width: 273"
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
<br><br><br><br>
<button id="${film.getTitle()}" class = "w3-button w3-large w3-round-large srCart" value = "cart">Add to Cart 
<i class="material-icons" style="font-size:30px;vertical-align:middle;">add_shopping_cart</i>
</button>
<script>
$("button.srCart").click(function() {
  myUrl = "addCartServlet?addFilm=" + $(this).attr('id'); 
  $.ajax({
        type : "POST",
        url : myUrl,
          datatype: "text",
        success : function(response){},
        error : function(jqXHR, exception){}
   });
  $(this).css("backgroundColor", "gray");
});
</script>
</div>
</div>
</div>
</div>
<br><br>
</body>
</html>