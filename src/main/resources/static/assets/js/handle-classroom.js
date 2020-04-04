var ws ;
$("#classSel").change(function(){
	$('iframe').attr('src',"http://"+$(this).val());
	arduinoMessage = {id:-1,className:$("#classSel option:selected").html(),arduinoMessage:false,newConnection:true};
	ws.send(JSON.stringify(arduinoMessage));
});

$(function(){
	ws = new WebSocket('ws://localhost:8080/socket');
	ws.onmessage = function(data) {
		var jsonObject = JSON.parse(data.data);
		console.log(jsonObject.studName);
		
	}
	
});