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
		<title>Join A Room</title>
	</head>
	<body>
		<div style="margin-top:20px;text-align:center">
			<h1 class="text text-primary">Join A Room</h1>
		</div>
		<div class="container" style="margin-top: 10px;text-align:center">
			<span>Rooms:</span>
			<br>
			<form id="join-form" action="RoomGuest" method="GET">
				<select id="room-selected" name="selected" style="width:500px">
					<c:forEach items="${rooms}" var="room">
						<c:choose>
							<c:when test="${loop.index == 0}">
								<option selected="selected" value="${room.getRoomHost()}">${room.getRoomName()} Hosted by ${room.getRoomHost()}</option>
							</c:when>
							<c:otherwise>
								<option value="${room.getRoomHost()}">${room.getRoomName()} hosted by ${room.getRoomHost()}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				<button id="join-room" style="height:28px" type="button">Join</button>
			</form>
			<br>
		</div>
		
		<div class="container" style="margin-top: 10px;text-align:center">
			<br><br>
			<a class="btn btn-primary" href="Lounge">Return</a>
		</div>
		
		<!-- scripts -->
		<script src="./js/jquery-3.3.1.js"></script>
		<script>
			$('#join-room').click(function() {
				var room = $('#room-selected option:selected').text();
				console.log(room)
			
				if (room != null && room.length != 0) {
					$('#join-form').submit();
				}
			});
		</script>
	</body>
</html>