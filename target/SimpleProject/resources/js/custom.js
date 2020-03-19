(function($) {
	'use strict';
	
	
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