<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">   
<link href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css" rel="stylesheet" />
   <link rel="stylesheet" type="text/css" href="resources/css/header.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

<%@include file="header.jsp" %>

<div class='table-container'>
   <table id="example" class="table table-striped table-bordered" style="width:100%">
        <thead>
            <tr>
            	<th>ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>profile</th>
                <th>Gender</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Country</th>
                <th>State</th>
                <th>role</th>
                 <th>update</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>           
        </tbody>
        <tfoot>
            <tr>
               <th>ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>profile</th>
                <th>Gender</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Country</th>
                <th>State</th>
                <th>role</th>
                <th>update</th>
                <th>Delete</th>
            </tr>
        </tfoot>
    </table>
   </div>

<%@include file="footer.jsp" %>
 <script src='https://code.jquery.com/jquery-3.3.1.js'></script>
 <script
  src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script> 
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src='https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js'></script> 
   <script src='https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js'></script>
   <script src='resources/js/show-datatable.js'></script>
   
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
</body>
</html>