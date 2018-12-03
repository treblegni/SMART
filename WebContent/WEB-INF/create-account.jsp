<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Create Account</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://bootswatch.com/4/pulse/bootstrap.css">
<script src="main.js"></script>
</head>
<body>
	<div style="margin-top: 10px;text-align:center">
		<h1 class="text text-primary">Create a New Account</h1>
	</div>
	<div class="container" style="margin-top: 10px;text-align:center">
		<c:if test="${error != null}">
			<span id="error" style="color:red">${error}</span>
		</c:if>
		<!--Prompt the user to enter a username and password of their choice as well as their current age. All usernames must be unique.-->
		<form id="create-form" action="CreateAccount" method="post">
			<label for="user">Create Username</label>
			<br>
			<input id="user" type="text" name="username">
			<br>
			<span id="usererror" style="color: red"></span>
			<br>
			<label for="pass">Create Password</label>
			<br>
			<input id="pass" type="password" name="password">
			<br>
			<span id="passerror" style="color: red"></span>
			<br>
			<label for="pass-confirm">Confirm Password</label>
			<br>
			<input id="pass-confirm" type="password">
			<br>
			<span id="passerror2" style="color: red"></span>
			<br>
			<label for="age">Age</label>
			<br>
			<input id="age" type="text" name="age">
			<br>
			<span id="ageerror" style="color: red"></span>
			<br>
			<br>
			<button id="create" class="btn btn-primary" type="button">Create Account</button>
		</form>
		<br>
		<a class="btn btn-primary" href="UserValidation">Return</a>
	</div>
	
	<script src="./js/jquery-3.3.1.js"></script>
	<script>
		$('#user').keyup(function(event) {
			$('#error').html("");
			$('#usererror').html("");
			if(event.keyCode === 13 && $(this).val().length != 0) {
				$('#create').click();
			}
		});
		$('#pass').keyup(function(event) {
			$('#error').html("");
			$('#passerror').html("");
			if(event.keyCode === 13 && $(this).val().length != 0) {
				$('#create').click();
			}
		});
		$('#pass-confirm').keyup(function(event) {
			$('#error').html("");
			$('#passerror2').html("");
			if(event.keyCode === 13 && $(this).val().length != 0) {
				$('#create').click();
			}
		});
		$('#age').keyup(function(event) {
			$('#error').html("");
			$('#ageerror').html("");
			if(event.keyCode === 13 && $(this).val().length != 0) {
				$('#create').click();
			}
		});
		$('#create').click(function() {
			if ($('#user').val().length != 0 && $('#pass').val().length != 0 && $('#age').val().length != 0 && $('#pass-confirm').val().length != 0) {
				if ($('#pass-confirm').val() == $('#pass').val()) {
					$('#create-form').submit();
				}
				else {
					$('#passerror2').html("Passwords do not match");
				}
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
				if ($('#pass-confirm').val().length == 0) {
					$('#passerror2').html("Required");
				}
				else {
					$('#passerror2').html("");
				}
				if ($('#age').val().length == 0) {
					$('#ageerror').html("Required");
				}
				else {
					$('#ageerror').html("");
				}
			}
		});
	</script>
</body>
</html>