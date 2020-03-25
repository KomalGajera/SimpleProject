$(document).ready(function() {

$('#example1').repeater({
	    btnAddClass: 'r-btnAdd',
	    btnRemoveClass: 'r-btnRemove',
	    groupClass: 'r-group',
	    minItems: 1,
	    maxItems: 0,
	    startingIndex: 0,
	    showMinItemsOnLoad: true,
	    reindexOnDelete: true,
	    repeatMode: 'append',
	    animation: 'fade',
	    animationSpeed: 400,
	    animationEasing: 'swing',
	    clearValues: true
	},[$('oldadd').val()]);

});