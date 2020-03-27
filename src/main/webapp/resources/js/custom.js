(function($) {
	'use strict';
	var table = $("#example");
	
	
	$("#email").blur(function(){			
		 	var email=$( "#email" ).val();    	
 			$.ajax({url: "checkemail",type:'POST',data:'email='+email, 			
 	        success: function(list){      
 	        	if(list==1){
 	        		$(".error").html("email already exits"); 	        		
 	        	}else{
 	        		$(".error").html("");
 	        	}	 	         
 	        },
 	        error: function(data) {
 	            alert('woops!');
 	        } 
 		});
	});	
	  $("#country").change(function(){
	    	 var country_name=$( "#country option:selected" ).text();    	 
	    		$.ajax({url: "displaystate",type:'POST',data:'country='+country_name,
	    			
	    	        success: function(list){      	        	
	    	            var select = $('#state');
	    	             select.find('option').remove();
	    	              $.each(list, function(index, value) {
	    	              $('<option>').val(value['state_name']).text(value['state_name']).appendTo(select);
	    	          });
	    	        },
	    	        error: function(data) {
	    	            alert('woops!');
	    	        } 
	    		});
	    	
	    });
	/*
	 * ================================================================== [
	 * Daterangepicker ]
	 */
	$("#profile").change(function() {
		readURL(this);
	});
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#profileimg').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}

	try {
		$('.js-datepicker').daterangepicker({
			"singleDatePicker" : true,
			"showDropdowns" : true,
			"autoUpdateInput" : false,
			locale : {
				format : 'DD/MM/YYYY'
			},
		});

		var myCalendar = $('.js-datepicker');
		var isClick = 0;

		$(window).on('click', function() {
			isClick = 0;
		});

		$(myCalendar).on('apply.daterangepicker', function(ev, picker) {
			isClick = 0;
			$(this).val(picker.startDate.format('DD/MM/YYYY'));

		});

		$('.js-btn-calendar').on('click', function(e) {
			e.stopPropagation();

			if (isClick === 1)
				isClick = 0;
			else if (isClick === 0)
				isClick = 1;

			if (isClick === 1) {
				myCalendar.focus();
			}
		});

		$(myCalendar).on('click', function(e) {
			e.stopPropagation();
			isClick = 1;
		});

		$('.daterangepicker').on('click', function(e) {
			e.stopPropagation();
		});

	} catch (er) {
		console.log(er);
	}
	/*
	 * [ Select 2 Config ]
	 * ===========================================================
	 */

	try {
		var selectSimple = $('.js-select-simple');

		selectSimple.each(function() {
			var that = $(this);
			var selectBox = that.find('select');
			var selectDropdown = that.find('.select-dropdown');
			selectBox.select2({
				dropdownParent : selectDropdown
			});
		});

	} catch (err) {
		console.log(err);
	}

})(jQuery);

window.onload = function() {
	
	$.ajax({url: "displaycountry",type:'POST',
        success: function(list){      	        	
            var select = $('#country');           
              $.each(list, function(index, value) {
              $('<option>').val(value['country_name']).text(value['country_name']).appendTo(select);
          });
        },
        error: function(data) {
            alert('woops!');
        } 
	});
	
	var id = getUrlVars()["id"];	
	if(id!=undefined)
		{	
		$.ajax({url: "userbyid",type:'POST',data:"id="+id,
	        success: function(data){   
	        	var select = $('#state'); 
	        	var abc=JSON.stringify(data);
            	var value = JSON.parse(abc);
            	$('#user_id').val(id);
            	$('#fname').val(value.fname);
            	$('#lname').val(value.lname);
            	$('#email').val(value.email);
            	$('h2').text("Updation Form");
            	$('#contact_no').val(value.number);
            	$('#psw').val(value.password);
            	$('#psw_confirm').val(value.password);
            	$('#birthdate').val(value.dob);
            	$('#country').val(value.country)
//            	$('#state').text(value.state);
            	$('<option selected="selected">').val(value.state).text(value.state).appendTo(select);
            	$('#profileimg').attr('src',"image?name="+value.fname+"");
            	if(value.gender=='female')
            	{
            		$("#female").attr('checked', 'checked');
            	}
            	if(value.gender=='male')
            	{
            		$("#male").attr('checked', 'checked');
            	}
            	var element=value.hobby.split(' ');
            	$.each(element,function(index,hobby){
            		$('input[type=checkbox]').filter(function(){
            			   return this.value === hobby;
            			}).prop('checked', true);
            	});
            	$(this).address(value.id); 
            	
	        },
	        error: function(data) {
	            alert('woops!');
	        } 
		});
	}
	 $.fn.address = function(paramater) {
		 var add1=[];
		 var i=0;
		 $.ajax({
 	        url: 'useraddress',
 	        type: "POST",
 	        data:"id="+paramater,
 	        success: function (address) { 
            	$.each(address, function (key, value) {            		
            		var a="{'address["+i+"][name]':'"+value.add+"'}";
            		add1.push(a);
            		i++;                	
                }); 
            	$('#oldadd').val("["+add1+"]");
            	var abc=$('#oldadd').val();
            	console.log(abc);
 	        },
	        error: function(data) {
	            alert('woops!');
	        } 
 	    });
	 };   
	
	
	
};

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