$("#classSel").change(function(){
	$('iframe').attr('src',"http://"+$(this).val());
});