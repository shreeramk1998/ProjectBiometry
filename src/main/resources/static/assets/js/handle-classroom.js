var ws ;
$("#classSel").change(function(){
	$('#startSession').data('clicked',false);
	$('iframe').attr('src',"http://"+$(this).val());
	arduinoMessage = {id:-1,className:$("#classSel option:selected").html(),arduinoMessage:false,newConnection:true};
	ws.send(JSON.stringify(arduinoMessage));
});

$(function(){
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
	

	$('#startSession').click(function() {
		$('iframe').css('pointer-events','auto');
	});
	$('#endSession').click(function() {
		$('iframe').css('pointer-events','none');
	});
//	$("iframe").load(function(){
//       
//    });
//	$('iframe').on('click', function(event) {alert("rrrr") });
});
function loadIframe() {
	
	
	
	if(!$('#startSession').data('clicked')) {
		$('iframe').css('pointer-events','none');
	}else {
		
	} 
}