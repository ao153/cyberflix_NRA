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
crossorigin="anonymous"></script>
<title>CyberFlix Shopping Cart</title>
</head>
<body>
<div class="w3-container w3-black w3-right-align padding" style = "padding-top:10px">
<h1>Checkout</h1>
</div>
<div class="w3-card w3-white invoiceBox">
  <c:if test="${requestScope.first_name.equals('Guest')}">
    <h3>Please Login or Create an account to enable checkout.</h3>
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
	})
</script>
	 
  </c:if>
</div>
</body>
</html>