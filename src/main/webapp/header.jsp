<%@page  isELIgnored="false" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg" id="menu">

<%--  <c:if test="${sessionScope.adminuser==null}">

<c:redirect url="Login.jsp"></c:redirect>
</c:if> --%>

<c:if test="${sessionScope.adminuser!=null}">
<%--  <a class="navbar-brand" href="#">welcome:${sessionScope.adminuser}</a> --%>

</c:if>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>     
     <li class="nav-item">
        <a class="nav-link" href="#">Profile</a>
      </li>
        <li class="nav-item">
        <a class="nav-link" href="user.jsp">User</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="addcountry.jsp">Country</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="addstate.jsp">State</a>
      </li>
      <c:if test="${sessionScope.adminuser!=null || sessionScope.normaluser!=null}">
		 <li class="nav-item">
        <a class="nav-link" href="logout.jsp">Logout</a>
      </li>
	</c:if>
     
     
    </ul>
   
  </div>
</nav>