var ws ;
$("#classSel").change(function(){
	$('iframe').attr('src',"http://"+$(this).val());
	ws.send("browser");
});

$(function(){
	ws = new WebSocket('ws://localhost:8080/socket');
	ws.onmessage = function(data) {
		console.log(data);
	}
	
});