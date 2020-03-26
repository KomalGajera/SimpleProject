<%@page  isELIgnored="false" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

if(session.getAttribute("username")==null)
    response.sendRedirect("Login.jsp");
%>
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<nav class="navbar navbar-expand-lg" id="menu">
 <input type="hidden" id="user" name="user" value="${sessionScope.user}">
<input type="hidden" id="user_id" name="user_id" value="${sessionScope.userid}">
<a class="navbar-brand" href="#">welcome:${sessionScope.username}</a>

  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto">
       <li class="nav-item" id="alluser">
        <a class="nav-link" href="user.jsp">User</a>
      </li>
     <!--  <li class="nav-item active">
        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>   -->   
    <li class="nav-item" id="profile">
        <a class="nav-link" href="userprofile.jsp?id=${sessionScope.userid}">Yourprofile</a>
      </li>      
      <li class="nav-item" id="country">
        <a class="nav-link" href="addcountry.jsp">Country</a>
      </li>
       <li class="nav-item" id="state">
        <a class="nav-link" href="addstate.jsp">State</a>
      </li>
       <li class="nav-item" id="updateprofile">
        <a class="nav-link" href="Register.jsp?id=${sessionScope.userid}">Updateprofile</a>
      </li>
       <c:if test="${sessionScope.username!=null}">
		 <li class="nav-item">
        <a class="nav-link" href="logout.jsp">Logout</a>
      </li>
     </c:if>
     
    </ul>
   
  </div>
</nav>