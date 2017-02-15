<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
  .carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
      width: 70%;
      margin: auto;
  }
  </style>
  
  
   <style>
         .Search {
     position: absolute;
     top: 355px;
     left: 575px;
}
.Search input {
     height: 50px;
     width: 180pt;
}
        </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Collaboration</a>
    </div>
   <ul class="nav navbar-nav">
        <%
   String user=(String)session.getAttribute("loggedInUserID"); 
   if(user==null)
   {
	   out.println("<li class='active'><a href='login'>Login</a></li>");
	   out.println("<li class='active'><a href='user'>Registration</a></li>");
   }
   else
   {
	   out.println("<li class='active'><a href='logout'>Logout</a></li>");
   }
   
   %>
  
      
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">Blogs<span class="caret"></span></a>
    <ul class="dropdown-menu">
      <li><a href="blog">Add New Blog</a></li>
      <li><a href="viewblog">Show List of Blogs</a></li>
    </ul></li>

  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">Friends<span class="caret"></span></a>
    <ul class="dropdown-menu">
      <li><a href="unfriend">View Friends/Unfriend</a></li>
      <li ><a href="viewusers">Send Friend Request</a></li>
    <li ><a href="acceptrejectrequest">Accept/Reject Request</a></li>
    </ul></li>

<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">Jobs<span class="caret"></span></a>
    <ul class="dropdown-menu">
      <li><a href="applyforjob">Search Job</a></li>
      <li><a href="job">Post New Jobs</a></li>
    </ul></li>
    
    
    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">Chat Forums<span class="caret"></span></a>
    <ul class="dropdown-menu">
      <li><a href="chatforum">Create Chat Forum</a></li>
      <li><a href="viewchatforums">View Existing Forums</a></li>
    </ul></li>
    <li class="active"><a href="personalchatting">Personal Chat</a></li>
 
    <li class="active"><a href="updateprofile">Update Profile</a></li>
    
</ul></div></nav><br><br><br><br>



    <div>
         <br> 
         <div class="panel panel-primary">
         <div class="panel-heading">
         Message Board
         </div>
         <table>
         <tr>
       <td>
       
   
     From:</td><div>
     <td> <input type='text' id="user"  value='<%=session.getAttribute("loggedInUserID") %>' readonly='true'/></br>
   </td>   </div>
      </tr>
      <tr>
      <td>
     To:</td><td><input type='text' id='to'/>
     </td>
     </tr>
     <tr>
     <td>
     Enter message
     </td>
     <td>
     <textarea id="userinput" rows='5' cols='60'></textarea></br>
    </td>
    </tr>
    </table>
    </div>
    <input type="submit"
            value="Send Message" onclick="start()" />
    <div id="messages"></div>
    </div>
    <script type="text/javascript">
    var app = angular.module('myApp', []);
    app.controller('ctrl', function($scope, $http) {
      $http.get("currentuser")
      .then(function(response) {
          $scope.user = response.data;
      });
    });
        var webSocket = new WebSocket(
                'ws://localhost:8080/niit.collaboration.frontend/websocket');

        webSocket.onerror = function(event) {
            onError(event)
        };

        webSocket.onopen = function(event) {
        	
            onOpen(event)
            
        };

        webSocket.onmessage = function(event) {
            onMessage(event)
        };

        function onMessage(event) {
        var res=event.data;  
        	
        	document.getElementById('messages').innerHTML += '<div class="panel panel-primary"><div class="panel-heading">message on <%=new java.util.Date()%> </div>'
                    + event.data+'</div>';
        	document.getElementById('user').innerHTML=event.data;
        
        }
        function onOpen(event) {
            document.getElementById('messages').innerHTML = 'Now Connection established';
        }

        function onError(event) {
            alert("error"+event.data);
        }

        function start() {
        	
            var text = document.getElementById("userinput").value;
                var user=document.getElementById("user").value;
                var to=document.getElementById("to").value;
            webSocket.send(user+","+to+","+text);
            return false;
        }
        
    </script>
</body>
</html>