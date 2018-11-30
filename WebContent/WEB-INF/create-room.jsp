<!-- this will be the create room page -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<!-- meta settings go here -->
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<!-- links to files this html will use go here -->
		<link rel="stylesheet" href="https://bootswatch.com/4/pulse/bootstrap.css">

		<!-- title for the page -->
		<title>Create Room</title>
	</head>
	<body>
		<div style="margin-top:10px;text-align:center">
			<h1 class="text text-primary">Track Search</h1>
		</div>
		<div class="container" style="margin-top: 10px;text-align:center">
			<span>Search By Artist, Album, Song:</span>
			<div>
				<input id="request"></input>
				<button id="search" type="button" style="height:28px">
					<img src="./img/search-icon.svg" style="margin-bottom:8px"height="20" width="20">
				</button>
			</div>
			
			<form action="CreateRoom" method="GET">
				<select name="selected" style="width:500px">
					<c:forEach items="${userTrackCache}" var="track">
						<option value="${track.getName()},${track.getArtist()},${track.getId()}">${track.getName()} by ${track.getArtist()}</option>
					</c:forEach>
				</select>
				<button id="addSong" style="height:28px">Add</button>
			</form>
			<br>
			<form id="playlist-form" action="Room" method="GET">
				<ul id="playlist">
				<c:forEach items="${userTracks.keySet()}" var="key">
					<li id="${userTracks.get(key).getId()}">${userTracks.get(key).getName()} by ${userTracks.get(key).getArtist()}</li>
				</c:forEach>
				</ul>
				<button id="start-room" type="button">Start Room</button>
			</form>
		</div>
		
		<div class="container" style="margin-top: 10px;text-align:center">
			<br><br>
			<a class="btn btn-primary" href="./index.html">Back to Home Page</a>
		</div>
		<div id="test"></div>
		
		<!-- scripts -->
		<script src="./js/jquery-3.3.1.js"></script>
		<script>
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
					var trackNames = [];
					var trackArtists = [];
					var trackIds = [];

					for (var i = 0 ; i < tracks.length ; i++) {
						trackNames.push(tracks[i].name);
						trackArtists.push(tracks[i].artistName);
						trackIds.push(tracks[i].id);
					}
					
					$.ajax({
					    url:"CreateRoom",
					    type:"GET",
					    dataType: 'json',
				        data: {
				        	trackNames: trackNames,
				        	trackArtists: trackArtists,
				        	trackIds: trackIds
				        }
					});
					window.location.reload();
				});
			});
			
			$('#start-room').click(function() {
				var trackNames = [];
				var trackIds = [];
				
				$('#playlist li').each(function() {
					var name = $(this).text();
					var id = $(this).attr('id');
					trackNames.push(name);
					trackIds.push(id);
				});
				
				$.ajax({
				    url:"Room",
				    type:"GET",
				    dataType: 'json',
			        data: {
			        	trackNames: trackNames,
			        	trackIds: trackIds
			        }
				});
				$('#playlist-form').submit();
			});
		</script>
	</body>
</html>