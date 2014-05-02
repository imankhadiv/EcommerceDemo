$(document).ready(function() {
		$('.nav-tabs li').on('click', function(event) {
			$('.nav-tabs li').removeClass('active'); // remove active class from tabs
			$(this).addClass('active'); // add active class to clicked tab
		//alert("ReaderController?action="+$(this).val()+"&"+$('input').val());
		});
			$('#search').on('click', function(event){
			//var v = $('.nav-tabs li').attr('id');
			var v = $('.active').attr('id');
			alert(v); 
			 $("#search").attr("href", "ReaderController?action="+v+"&value="+$('#input').val());
 
			});
			

		});