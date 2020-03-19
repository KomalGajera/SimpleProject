
$(document).ready(function() {
	
	
	var table = $("#example tbody");
	 
    $.ajax({
        url: 'displaystate',
        type: "POST",
        success: function (data) {
            table.empty();
            $.each(data, function (key, value) {
                table.append("<tr><td>"+value.state_id+"</td>" +
                		"<td>"+value.country_name+"</td>"+
                		"<td>"+value.state_name+"</td>"+
                		"<td><a href='stateupdate?"+value.state_id+"'>update</a></td>"+
                    "<td><a href='statedelete?"+value.state_id+"'>delete</a></td></tr>");
            }); 
            $("#example").DataTable();
        }
    });
	
	$.ajax({url: "displaycountry",type:'POST',
        success: function(list){      	        	
            var select = $('#selectcountry');           
              $.each(list, function(index, value) {
              $('<option>').val(value['country_name']).text(value['country_name']).appendTo(select);
          });
        },
        error: function(data) {
            alert('woops!');
        } 
	});     
});