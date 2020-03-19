$(document).ready(function() {
	
	var table = $("#example tbody");
	 
    $.ajax({
        url: 'displaycountry',
        type: "POST",
        success: function (data) {
            table.empty();
            $.each(data, function (key, value) {
                table.append("<tr><td>"+value.country_id+"</td>" +
                    "<td>"+value.country_name+"</td>"+
                    "<td><a href='countryupdate?id="+value.country_id+"'>update</a></td>"+
                    "<td><a href='countrydelete?id="+value.country_id+"'>delete</a></td></tr>");
            }); 
            $("#example").DataTable();
        }
    });
} );