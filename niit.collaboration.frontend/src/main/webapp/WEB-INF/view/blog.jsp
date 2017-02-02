<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body ng-app="myApp" class="ng-cloak">
      <div class="generic-container" ng-controller="BlogController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead"><h3>-----Add Blog page-----</h3></span></div><br><br>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.blog.id" />
                    
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="title">Blog Title:</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.blog.title" id="title" class="title form-control input-sm" placeholder="Enter your blog title" required/>
                                </div>
                           </div>
                       </div><br>
                                
                    <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="description">Description:</label>
                              <div class="col-md-7">
                                  <textarea rows="8" cols="80" ng-model="ctrl.blog.description" id="description" class="form-control input-sm" placeholder="Enter your blog description. [This field is validation free]">
                                  </textarea>
                              </div>
                          </div>
                      </div> <br>
                      
                       <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.blog.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Blog</button>
                          </div>
                      </div>
                      </form>
                      </div>
                      </div>
                      </div> 
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/app.js' />"></script>
      <script src="<c:url value='/static/blog_controller.js' />"></script>
      <script src="<c:url value='/static/blog_service.js' />"></script>            
</body>
</html>