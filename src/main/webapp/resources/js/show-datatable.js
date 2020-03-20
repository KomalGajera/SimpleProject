$(document).ready(function() {
	
	var table = $("#example tbody");
	 
    $.ajax({
        url: 'displayuser',
        type: "POST",
        success: function (data) {
            table.empty();
            $.each(data, function (key, value) {
                table.append("<tr><td>"+value.id+"</td>" +
                		"<td><a href='userprofile.jsp?id="+value.id+"'>"+value.fname+"</a></td>"+
                		"<td>"+value.lname+"</td>"+
                		"<td><img src='image?name="+value.fname+"' width='100' height='100'/></td>"+
                		"<td>"+value.gender+"</td>"+
                		"<td>"+value.email+"</td>"+
                		"<td>"+value.number+"</td>"+
                		"<td>"+value.country+"</td>"+
                		"<td>"+value.state+"</td>"+
                		"<td>"+value.hobby+"</td>"+
                		"<td>"+value.role+"</td>"+"<td><a href='userdelete?id="+value.id+"'>delete</a></td></tr>");
            }); 
            $("#example").DataTable();
        }
    });    
});