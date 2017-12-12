<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login to CyberFlix</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="http://localhost:8080/CyberFlix_NRA/stylesheet.css">
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
    <h1 style="margin-right:60px">Login</h1>
</div>
<form 
    action="http://localhost:8080/CyberFlix_NRA/CyberFlixLoginServlet" 
    method="post" 
    class="w3-container, w3-display-middle" 
    style="max-width:500px; margin-top: 120px;"
    >
    
    <!-- START USER-SELECT -->
          <input class="w3-radio" type="radio" name="user_type" value="new"> 
            <label class="w3-text-white"><b>Create an Account</b></label><br>
          <input class="w3-radio" type="radio" name="user_type" value="existing" checked> 
            <label class="w3-text-white"><b>Current Customer</b></label><br>
       <!-- END USER-SELECT -->
        <br><h3 class="w3-text-white"><b>E-mail Address</b></h3>
        <input class="w3-input w3-border" 
            type="email" 
            name="email_address" required>
        
        <h3 class="w3-text-white"><b>Password</b></h3>
        <input class="w3-input w3-border" 
            type="password" 
            name="password" required>
        <button class="w3-btn w3-red w3-round-large" type="submit" 
            value ="Submit" 
            style="margin-top:20px; margin-left:65px">Login</button>
</form>
</body>
</html>