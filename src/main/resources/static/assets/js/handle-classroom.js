var ws ;
$("#classSel").change(function(){

	$('#startSession').addClass("disabled");
	if($('#sessionTime').val()!='') {

		$('#startSession').removeClass("disabled");
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
	ws = new WebSocket('ws://localhost:8080/socket');
	ws.onmessage = function(data) {
		let date = new Date();
		let dateString = date.toDateString();
		let timeString = date.toLocaleTimeString();
		let dateTime = dateString.concat(" "+timeString);
		var jsonObject = JSON.parse(data.data);
		var rowAppend = "<tr><th scope='row'>"+jsonObject.roll+"</th>" +
		"<td>"+jsonObject.studName+"</td>" +
		"<td>"+dateTime+"</td></tr>";
		$("tbody").append(rowAppend);
		console.log(jsonObject.studName);

	}
	//popovers
	//endpopovers

	$('#startSession').click(function() {		
		$('iframe').css('pointer-events','auto');
	});
	
	$('#endSession').click(function() {
		$('iframe').css('pointer-events','none');
	});
	
	$('#sessionTime').keyup(function(){
		if($('#sessionTime').val()!='') {

			$('#startSession').removeClass('disabled');
		}
	});
	var token = $("meta[name='_csrf']").attr("content"); 
	var header = $("meta[name='_csrf_header']").attr("content");
	$('#endSession').click(function() {
		var json = {};
		if($('#couseSel').find(':selected').val()==0){
			alert("select a valid course before ending the session!");
			return ;
		}
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
