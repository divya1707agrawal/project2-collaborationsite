<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
  <body ng-app="myApp" class="ng-cloak">
 <div class="generic-container" ng-controller="LoginController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">User Login Form </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
     
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="uname">User Id:</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.user.id" id="id" class="name form-control input-sm" placeholder="Enter your id" required />
                              </div>
                          </div>
                      </div>
                         
                       
                       <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="password">Password</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.user.password" id="password" class="form-control input-sm" placeholder="Enter your password. [This field is validation free]"/>
                              </div>
                          </div>
                      </div>
                      
                      
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="submit" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                          </div>
                      </div>
                  </form>
              </div>
          </div></div>
      <script src="<c:url value='/static/app.js' />"></script>
      <script src="<c:url value='/static/login_controller.js' />"></script>
      <script src="<c:url value='/static/login_service.js' />"></script>
  </body>
</html>