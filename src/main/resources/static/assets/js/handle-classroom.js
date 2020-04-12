var ws ;
$("#classSel").change(function(){


	if($('#sessionTime').val()!='') {
	}

	$('#startSession').data('clicked',false);
	$('iframe').attr('src',"http://"+$(this).val());
	arduinoMessage = {id:-1,className:$("#classSel option:selected").html(),arduinoMessage:false,newConnection:true};
	ws.send(JSON.stringify(arduinoMessage));
});

$(function(){

	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function (e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});

	console.log(new Date().toJSON());
	ws = new WebSocket('ws://192.168.2.5:8080/socket');
	ws.onmessage = function(data) {
		let date = new Date();
		let dateString = date.toDateString();
		let timeString = date.toLocaleTimeString();
		let dateTime = dateString.concat(" "+timeString);
		var jsonObject = JSON.parse(data.data);

		if(jsonObject.newConnection){			
			return null;
		}
		var rowAppend = "<tr><th scope='row'>"+jsonObject.roll+"</th>" +
		"<td>"+jsonObject.studName+"</td>" +
		"<td>"+dateTime+"</td></tr>";
		$("tbody").append(rowAppend);

	}

	$('#startSession').click(function() {		
		$('iframe').css('pointer-events','auto');
	});

	$('#endSession').click(function() {
		$('iframe').css('pointer-events','none');
	});



	$('#endSession').click(function() {
		if($('#couseSel').find(':selected').val()==0 ){
			$('.modal-title').text("Message!");
			$('.modal-body').text("Select a valid COURSE before ending the session!");
			$('#ModalCenter').modal('show');
			return ;
		}else if( $("#attTable tr").length==0){
			$('.modal-title').text("Message!");
			$('.modal-body').text("No attendance recorded!");
			$('#ModalCenter').modal('show');
			return;
		}else if($('#sessionTime').val()===''){
			$('.modal-title').text("Message!");
			$('.modal-body').text("Please specify session TIME before ending the session!");
			$('#ModalCenter').modal('show');
			return;
		}
		else if($('#classSel').find(':selected').val()==0){
			$('.modal-title').text("Message!");
			$('.modal-body').text("Select a valid CLASS before ending the session!");
			$('#ModalCenter').modal('show');
			return ;
		}
		var json = {};

		json.cmID = $('#couseSel').find(':selected').val();
		json.date = new Date().toJSON().slice(0,10).split('-').reverse().join('-');
		json.time = $('#sessionTime').val();
		json.className = $('#classSel').find(':selected').text();
		var jsonString = JSON.stringify(json);
		console.log(jsonString);
		$.ajax({
			type:"post",
			url: "/teacher/attendance",
			data: jsonString,
			async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
			cache: false,    //This will force requested pages not to be cached by the browser          
			processData:false, //To avoid making query String instead of JSON
			contentType: 'application/json; charset=utf-8',
			success:function(response) {
				if (response==="true") {
					$('.modal-title').text("Message!");
					$('.modal-body').text("Successfully saved attendance");
					$('#ModalCenter').modal('show');
				}
			} 
		});	
	});

	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#attTable tr").filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	})
});
function loadIframe() {

	if(!$('#startSession').data('clicked')) {
		$('iframe').css('pointer-events','none');
	}else {

	} 
}
