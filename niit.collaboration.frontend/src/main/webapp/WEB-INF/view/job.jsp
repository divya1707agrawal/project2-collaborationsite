<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
 
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
  
  <script>
  $(document).ready(function() {
    $("#datepicker").datepicker();
  });
  </script>
</head>
<body>
<div class="generic-container" ng-controller="JobController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead"><h3>----------Post Job Form------------</h3></span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
     
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="title">Job Title:</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.job.title" id="title" class="title form-control input-sm" placeholder="Enter Job Title" required />
                              </div>
                          </div>
                      </div>
                      <br>
                       <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="description">Description</label>
                              <div class="col-md-7">
                                   <textarea rows="8" cols="80" ng-model="ctrl.job.description" id="description" class="form-control input-sm" placeholder="Enter your descritpion. [This field is validation free]">
                              </textarea>
                              </div>
                          </div>
                      </div>
                      <br>
                       <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="datepicker">Date</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.job.dateTime" id="datepicker" class="form-control input-sm" placeholder="select date. [This field is validation free]"/>
                              </div>
                          </div>
                      </div>
                      <br>
                      
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.job.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div></div>
          <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script> 
      <script src="<c:url value='/static/app.js' />"></script>
      <script src="<c:url value='/static/job_controller.js' />"></script>
      <script src="<c:url value='/static/job_service.js' />"></script>
               </body>
</html>