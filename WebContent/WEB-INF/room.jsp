<!-- this will be the create room page -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		
		<link rel="stylesheet" href="https://bootswatch.com/4/pulse/bootstrap.css">
		
		<title>Room</title>
	</head>
	<body>
		<div class="container" style="margin-top: 10px;text-align:center">
			<h1 class="text text-primary">This is the Room</h1>
		<div>
		<div class="container" style="margin-top: 10px;text-align:center">
			<span></span>		
			<select id="tracks">
			<c:forEach items="${playlist.keySet()}" var="key" varStatus="loop">
				<c:choose>
					<c:when test="${loop.index == 0}">
						<option selected="selected" value="${playlist.get(key)}">${key}</option>
					</c:when>
					<c:otherwise>
						<option value="${playlist.get(key)}">${key}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			</select>
			<br>
			<div>
				<div id="image"></div>
				
				<audio id="player" controls></audio>
			</div>
		</div>
		<div class="container" style="margin-top: 10px;text-align:center">
			<br><br>
			<a class="btn btn-primary" href="./index.html">Back to Home Page</a>
		</div>
		
		<!-- scripts -->
		<script src="./js/jquery-3.3.1.js"></script>
		<script>
			window.onload = function() {
				const API_KEY = 'ZGRmNTkxNTYtY2NmZC00OTVjLWE2YTMtYmMwYWQzODI5YmY1';
				
				var trackId = document.getElementById("tracks").value;
				
				$.getJSON('https://api.napster.com/v2.2/tracks/' + trackId + '?apikey=' + API_KEY, function(query) {
					var trackInfo = query.tracks;
					var albumId = trackInfo[0].albumId;
					var trackUrl = trackInfo[0].previewURL;
					
					$('#image').html('<img src="http://direct.rhapsody.com/imageserver/v2/albums/' + albumId + '/images/300x300.jpg">');
					$('#player').html('<source src="' + trackUrl + '"></source>');
				});
			
				$('#tracks').change(function() {
					trackId = document.getElementById("tracks").value;
					
					$.getJSON('https://api.napster.com/v2.2/tracks/' + trackId + '?apikey=' + API_KEY, function(query) {
						var trackInfo = query.tracks;
						var albumId = trackInfo[0].albumId;
						var trackUrl = trackInfo[0].previewURL;
						
						$('#image').html('<img src="http://direct.rhapsody.com/imageserver/v2/albums/' + albumId + '/images/300x300.jpg">');
						$('#player').html('<source src="' + trackUrl + '"></source>');
					});
				});
			}
		</script>
	</body>
</html>