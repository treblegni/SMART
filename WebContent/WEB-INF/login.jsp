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
	<div class="container" style="margin-top: 10px;text-align:center">
		<!-- The user must enter the username and password of an already created account.-->
		<form action="Login" method="post">
			<label for="username">Username</label><br>
			<input type="text" name="username"><br><br>
			<label for="password">Password</label><br>
			<input type="text" name="password"><br><br>
			<button class="btn btn-primary" type="submit">Login</button>
		</form>
		<br><br>
		<a class="btn btn-primary" href="./index.html">Back to Home Page</a>
	</div>
</body>
</html>