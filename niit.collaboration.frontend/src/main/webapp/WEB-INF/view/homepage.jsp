<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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

<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

<nav class="navbar navbar-invers navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Collaboration</a>
    </div>
   <ul class="nav navbar-nav">
   
      <li class="active"><a href="#/">Home</a></li>
      
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">Blogs<span class="caret"></span></a>
    <ul class="dropdown-menu">
      <li><a href="#create_blog">Add New Blog</a></li>
      <li><a href="#list_blog">Show List of Blogs</a></li>
    </ul></li>

  <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">Friends<span class="caret"></span></a>
    <ul class="dropdown-menu">
      <li><a href="#search_friend">Search Friends</a></li>
      <li><a href="#/view_firend">View Friends</a></li>
    </ul></li>

<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">Jobs<span class="caret"></span></a>
    <ul class="dropdown-menu">
      <li><a href="#search_job">Search Job</a></li>
      <li><a href="#/view_applied_jobs">View Applied Jobs</a></li>
      <li><a href="#/post_job">Post New Jobs</a></li>
    </ul></li>
    
    <li><a href="#create_blog">Events</a></li>
    
    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown">Chat Forums<span class="caret"></span></a>
    <ul class="dropdown-menu">
      <li><a href="#create_chat_forum">Create Chat Forum</a></li>
      <li><a href="#list_chat_forum">View Existing Forums</a></li>
    </ul></li>
    
    

</ul></div></nav>


</body></html>