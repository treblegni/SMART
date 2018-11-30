window.onload = function() {
	const API_KEY = 'ZGRmNTkxNTYtY2NmZC00OTVjLWE2YTMtYmMwYWQzODI5YmY1';
	
	$('#request').keyup(function(event) {
		if(event.keyCode === 13) {
			$('#search').click();
		}
	});

	$('#search').click(function() {
		var track = $('#request').val();

		$.getJSON('https://api.napster.com/v2.2/search?apikey=' + API_KEY + '&query=' + track + '&type=track&per_type_limit=10', function(query) {
			var tracks = query.search.data.tracks;
//			var audioString = '';
//
//			for (var i = 0 ; i < tracks.length ; i++) {
//				audioString += '<option>' +
//				tracks[i].name + ' by ' + tracks[i].artistName +
//				'</option>';
//			}
//			
//			$('#audio-dropdown').html(audioString);
			
			$.ajax({
			    url:"./WEB-INF/myUrl",
			    type:"POST",
			    dataType:'json',
			    data: {json:json},
			    success:function(data){
			        // codes....
			    },
			});
		});
	});
}