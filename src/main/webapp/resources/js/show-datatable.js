$(document).ready(function() {
	
	var table = $("#example tbody");
	 
    $.ajax({
        url: 'displayuser',
        type: "POST",
        success: function (data) {
            table.empty();
            $.each(data, function (key, value) {
            	
            	var user=$( "#user" ).val(); 
            	var user_id=$( "#user_id" ).val(); 
            	if(user=='user')
            	{
            		if(user_id==value.id){
            		 table.append("<tr><td>"+value.id+"</td>" +
                      		"<td><a href='userprofile.jsp?id="+value.id+"'>"+value.fname+"</a></td>"+
                      		"<td>"+value.lname+"</td>"+
                      		"<td><img src='image?name="+value.fname+"' width='100' height='100'/></td>"+
                      		"<td>"+value.gender+"</td>"+
                      		"<td>"+value.email+"</td>"+
                      		"<td>"+value.number+"</td>"+
                      		"<td>"+value.country+"</td>"+
                      		"<td>"+value.state+"</td>"+
                      		"<td>"+value.role+"</td>"+
                      		"<td><a href='Register.jsp?id="+value.id+"'>update</a></td>"+
                      		"<td><a href='#'>delete</a></td></tr>");
            		}
            	}
            	else{
            		 table.append("<tr><td>"+value.id+"</td>" +
                     		"<td><a href='userprofile.jsp?id="+value.id+"'>"+value.fname+"</a></td>"+
                     		"<td>"+value.lname+"</td>"+
                     		"<td><img src='image?name="+value.fname+"' width='100' height='100'/></td>"+
                     		"<td>"+value.gender+"</td>"+
                     		"<td>"+value.email+"</td>"+
                     		"<td>"+value.number+"</td>"+
                     		"<td>"+value.country+"</td>"+
                     		"<td>"+value.state+"</td>"+
                     		"<td>"+value.role+"</td>"+
                     		"<td><a href='Register.jsp?id="+value.id+"'>update</a></td>"+
                     		"<td><a href='#' onClick='$(this).deleteuser("+value.id+","+value.role+")'>delete</a></td></tr>");
                
            	}    	
            	
            });    
            $("#example").DataTable();
        }
    });   
    
    
    $.fn.deleteuser = function(paramater,role) {
    	if(role=='admin')
    		{
    			alert("you can not delete admin");
    		}
    	else{
	    	
	    	$('<div></div>').appendTo('body')
	        .html('<div><h6>Are you sure to delete userId:'+paramater+'??</h6></div>')
	        .dialog({
	          modal: true,
	          title: 'Delete message',
	          zIndex: 10000,
	          autoOpen: true,
	          width: 'auto',
	          resizable: false,
	          buttons: {
	            Yes: function() {            
	              $.ajax({    		
	                  url: 'userdelete',data:"id="+paramater,
	                  type: "GET",
	                  success: function (data) {
	                 	 window.location.reload();
	              }
	              }); 
	              $(this).dialog("close");
	            },
	            No: function() {
	              $(this).dialog("close");
	            }
	          }
	        });
    	
    	}
      }; 
});