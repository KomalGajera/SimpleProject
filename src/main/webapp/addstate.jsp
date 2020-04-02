<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="resources/css/dataTables.bootstrap4.min.css">
   <link rel="stylesheet" type="text/css" href="resources/css/header.css">
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
</head>
<body>

<%@include file="header.jsp" %>
<div class='table-container'>
   <table id="example" class="table table-striped table-bordered" style="width:100%">
        <thead>
            <tr>
            	<th>ID</th>
            	<th>country name</th>
                <th>State Name</th> 
                <th>Update</th>
                <th>Delete</th>                        
            </tr>
        </thead>
        <tbody>
        </tbody>
        <tfoot>
            <tr>
               	<th>ID</th>
            	<th>country name</th>
                <th>state Name</th>
                <th>Update</th>
                <th>Delete</th> 
            </tr>
        </tfoot>
    </table>
   </div>   
 <div class="container" id="country12">
  <form action="stateadd" method="post">  
    <div class="form-group">
       <label class="label">Country</label>
       <select name="selectcountry" id="selectcountry" class="form-control">
	   <option disabled="disabled" selected="selected" value="0">Choose Country</option>		  
		</select>
        <div class="select-dropdown"></div>
  </div>  
    <div class="form-group" id="main_stateid">
      <label for="stateid">county id:</label>
      <input type="text" class="form-control" id="state_id" name="state_id" value="0">
    </div> 
    <div class="form-group">
      <label for="country">Add State:</label>
      <input type="text" class="form-control" id="state" placeholder="Enter state" name="state">
    </div>    
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
</div>

<%@include file="footer.jsp" %>
        <script src='resources/js/jquery-3.3.1.js'></script>
        <script src="resources/js/jquery-3.4.1.min.js"></script>
        <script src="resources/js/popper.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script src='resources/js/jquery.dataTables.min.js'></script>
        <script src='resources/js/dataTables.bootstrap4.min.js'></script>
        <script src='resources/js/show-state.js'></script>
</body>
</html>