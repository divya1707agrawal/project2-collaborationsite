<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
</head>
<body ng-app="myApp">



<div  ng-controller="editcontroller as edt">
<div ng-controller="BlogController as ctrl">
   <form action="ctrl.submit()">
   <input type="hidden"  value="{{ blog.id }}"/>
   <input type="text" value="{{ blog.title }}"/>
   
   <textarea>
   {{ blog.description }}
   </textarea>
   <input type='submit' value="update"/>
   </form>
</div> 
</div>
  

 <script src="<c:url value='/static/app.js' />"></script>
      <script src="<c:url value='/static/blog_controller.js' />"></script>
      <script src="<c:url value='/static/blog_service.js' />"></script> 
      <script src="<c:url value='/static/editblogcontroller.js' />"/></script>
</body>
</html>