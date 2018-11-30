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
		<!--Prompt the user to enter a username and password of their choice as well as their current age. All usernames must be unique.-->
		<form action="Users" method="post">
			<label for="createusername">Create Username</label><br> <input
				type="text" name="createusername"><br> <br> <label
				for="createpassword">Create Password</label><br> <input
				type="text" name="createpassword"><br> <br> <label
				for="age">Age</label><br> <input type="text" name="age"><br>
			<br> <input class="btn btn-primary" type="submit"
				value="Create Account">
		</form>
		<br>
		<br>
		<a class="btn btn-primary" href="./index.html">Back to Home Page</a>
	</div>
</body>
</html>