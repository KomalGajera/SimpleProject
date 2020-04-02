<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="resources/css/dataTables.bootstrap4.min.css">
   <link rel="stylesheet" type="text/css" href="resources/css/header.css">
   <link rel="stylesheet" type="text/css" href="resources/css/userprofile.css">
	<link rel="stylesheet"  type="text/css" href="resources/css/bootstrap.min.css">
</head>
<body>

<%@include file="header.jsp" %>

<div class="container">
	<table id="example">
  <tr>
    <th>Field</th>
    <th>value</th>
  </tr>
  
</table>

</div>


 <%@include file="footer.jsp" %>
        <script src='resources/js/jquery-3.3.1.js'></script>
        <script src="resources/js/jquery-3.4.1.min.js"></script>
        <script src="resources/js/popper.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script src='resources/js/jquery.dataTables.min.js'></script>
        <script src='resources/js/dataTables.bootstrap4.min.js'></script>
        <script src='resources/js/profile.js'></script>
</body>
</html>




