<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Login Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://bootswatch.com/4/pulse/bootstrap.css">
<script src="main.js"></script>

</head>
<body>
	<div style="margin-top: 10px;text-align:center">
		<h1 class="text text-primary">Login</h1>
	</div>
	<div class="container" style="margin-top: 20px;text-align:center">
		<c:if test="${error != null}">
			<span id="error" style="color:red">${error}</span>
		</c:if>
		<!-- The user must enter the username and password of an already created account.-->
		<form id="login-form" action="Login" method="post">
			<label for="username">Username</label><br>
			<input id="user" type="text" name="username">
			<br>
			<span id="usererror" style="color: red"></span>
			<br>
			<label for="password">Password</label><br>
			<input id="pass" type="password" name="password">
			<br>
			<span id="passerror" style="color: red"></span>
			<br>
			<br>
			<button id="login" class="btn btn-primary" type="button">Login</button>
		</form>
		<br>
		<a class="btn btn-primary" href="UserValidation">Return</a>
	</div>
	
	<script src="./js/jquery-3.3.1.js"></script>
	<script>
		$('#user').keyup(function(event) {
			$('#error').html("");
			if(event.keyCode === 13 && $(this).val().length != 0) {
				$('#login').click();
			}
		});
		$('#pass').keyup(function(event) {
			if(event.keyCode === 13 && $(this).val().length != 0) {
				$('#login').click();
			}
		});
		$('#login').click(function() {
			if ($('#user').val().length != 0 && $('#pass').val().length != 0) {
				$('#login-form').submit();
			}
			else {
				if ($('#user').val().length == 0) {
					$('#usererror').html("Required");
				}
				else {
					$('#usererror').html("");
				}
				if ($('#pass').val().length == 0) {
					$('#passerror').html("Required");
				}
				else {
					$('#passerror').html("");
				}
			}
		});
	</script>
</body>
</html>