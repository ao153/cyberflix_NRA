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
<%@ page import = "edu.txstate.internet.cyberflix.data.DataSource" %>
<script
    src="https://code.jquery.com/jquery-3.2.1.min.js"
    integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
    crossorigin="anonymous">
</script>
<title>CyberFlix Shopping Cart</title>
</head>
<body>
<div class="container w3-black">      
      <img class="logo"
           src="http://localhost:8080/CyberFlix_NRA/images/logo.jpg" 
           alt="Logo" 
           style="width:auto;height:200px"
      >
</div>
<div class="w3-container w3-black w3-right-align padding" style="padding-top:10px">
    <h1 style="margin-right:60px">Checkout</h1>
</div>

<div class="w3-text-white w3-card w3-round-large invoiceBox" style="background-color: rgba(255, 255, 255, 0.3); margin-top: 5%;">
  <c:if test="${requestScope.first_name.equals('Guest')}">
  
    <h3 class="w3-text-white"
        style="padding-top: 20px;">Please Login or Create an account to enable checkout.</h3>
    
    <a class="w3-btn w3-red w3-round-large" 
        href="login.jsp" 
        target="_blank"
        style="margin-top: 10px;">LOGIN</a>
  </c:if>
  
  <c:if test="${!requestScope.first_name.equals('Guest')}">
        <h3><c:out value="${requestScope.customer_name}"/></h3>
        <h5><c:out value="${requestScope.customer_email}"/></h5>
        <h4>Thanks for using CyberFlix!</h4>
        <br>
        <p>You have selected <c:out value="${requestScope.film_count}" /> films to rent.</p>
        <p>At a rental rate of $1 per day per movie, your total per day will be $
         <c:out value="${requestScope.film_count}" /> per day.</p>
         <button class="confirmBtn w3-button w3-round-large w3-green" 
            data-cost="${requestScope.film_count}">
            Confirm
        </button>
         <script>
            $("button.confirmBtn").click(function() {
           	  alert("You have been charged $" + $(this).data("cost") + ". Thanks!"); 
              
              myUrl = "CyberFlixCheckoutServlet";
              $.ajax({
                  type : "POST",
                  url : myUrl,
                  datatype: "text",
                  success : function(response){},
                  error : function(jqXHR, exception){}
             });
            })
        </script>  
  </c:if>
</div>
<!-- BEGIN CURRENT CHECKED OUT -->
<div class="w3-card w3-round-large invoiceBox" style="background-color: rgba(255, 255, 255, 0.3); padding-top: 25px; width: 40%;">
<% int i = 0; %>
<h3 style="color: white; margin-bottom: 25px;">Currently Checked Out:</h3>
<c:forEach var="film" items="${requestScope.films}">
<% String source = "http://localhost:8080/CyberFlix_NRA/images/" + i + ".jpeg"; %>
<div class="w3-card-4 center card" style = "height: 150px; width: 70%">
<div class = "w3-row">
    <div class = "w3-cell">
    <a href="${requestScope.detailServlet}?film_title=${film.getTitle()}&source=<%=source%>">
    <img class="w3-image"
         style = "height: 150px;"
         src=<%=source%>
         alt="${film.title}"
    >
    </a>
    </div>
<div class = "w3-cell w3-cell-top w3-padding">
<h3><c:out value="${film.getTitle()}"/></h3><br>
<b>Rental Date: </b><c:out value="${requestScope.dateMap.get(film.getTitle())}"/><br>
</div>
</div>
<% i ++;
if(i == 9)
i = 0;
%>
</div>
<br> 
</c:forEach>
</div>
<!-- END CURRENT CHECKED OUT -->

</body>
</html>