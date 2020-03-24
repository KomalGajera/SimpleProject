<%@page  isELIgnored="false" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg" id="menu">
 <input type="hidden" id="user" name="user" value="${sessionScope.user}">
 <c:if test="${sessionScope.username==null}">
<c:redirect url="Login.jsp"></c:redirect>
</c:if> 
<a class="navbar-brand" href="#">welcome:${sessionScope.username}</a>

  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>     
    <li class="nav-item" id="profile">
        <a class="nav-link" href="userprofile.jsp?id=${sessionScope.userid}">Your profile</a>
      </li> 
        <li class="nav-item" id="alluser">
        <a class="nav-link" href="user.jsp">user</a>
      </li>
      <li class="nav-item" id="country">
        <a class="nav-link" href="addcountry.jsp">Country</a>
      </li>
       <li class="nav-item" id="state">
        <a class="nav-link" href="addstate.jsp">State</a>
      </li>
       <li class="nav-item" id="updateprofile">
        <a class="nav-link" href="Register.jsp?id=${sessionScope.userid}">updateprofile</a>
      </li>
       <c:if test="${sessionScope.username!=null}">
		 <li class="nav-item">
        <a class="nav-link" href="logout.jsp">Logout</a>
      </li>
     </c:if>
     
    </ul>
   
  </div>
</nav>