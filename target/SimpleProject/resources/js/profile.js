$(document).ready(function() {
	
	var table = $("#example");
	var id = getUrlVars()["id"];	
	 $.ajax({
	        url: 'userbyid',
	        type: "POST",
	        data:"id="+id,
	        success: function (data) {
	        	var abc=JSON.stringify(data);
            	var value = JSON.parse(abc);
            	table.append("<tr><td>ID</td><td>"+value.id+"</td></tr>" );
            	table.append("<tr><td>First name</td><td>"+value.fname+"</td></tr>" ); 
            	table.append("<tr><td>last name</td><td>"+value.lname+"</td></tr>" ); 
            	table.append("<tr><td>profile</td><td><img src='image?name="+value.fname+"' width='100' height='100'/></td></tr>" ); 
            	 $.ajax({
            	        url: 'useraddress',
            	        type: "POST",
            	        success: function (address) {
                        	var avalue = JSON.parse(JSON.stringify(address));	
            	        }
            	    });                	
            	table.append("<tr><td>Gender</td><td>"+value.gender+"</td></tr>" ); 
            	table.append("<tr><td>email</td><td>"+value.email+"</td></tr>" ); 
            	table.append("<tr><td>phone_no</td><td>"+value.number+"</td></tr>" ); 
            	table.append("<tr><td>country</td><td>"+value.country+"</td></tr>" ); 
            	table.append("<tr><td>state</td><td>"+value.state+"</td></tr>" ); 
            	table.append("<tr><td>hobby</td><td>"+value.hobby+"</td></tr>" ); 
            	table.append("<tr><td>role</td><td>"+value.role+"</td></tr>" ); 
	    }
	    });

});


function getUrlVars()
{
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}

