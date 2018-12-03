<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  	
	<link rel="stylesheet" href="https://bootswatch.com/4/pulse/bootstrap.css">
	
	<title>Login/Sign Up</title>
</head>
<body>
	<div class="container" style="margin-top: 10px;text-align:center">
		<!--If user is registered, they can choose to login.-->
		<div>
			<p>If you are already registered, please login.</p>
			<a href="Login">
				<button type="button" class="btn btn-primary">Login</button>
			</a>
			
		</div>
		<br>
		<!--If user is not registered, they may create a new account-->
		<div>
			<p>If you do not have an account, please sign up for one.</p>
			<a href="CreateAccount">
				<button type="button" class = "btn btn-primary">Sign Up</button>
			</a>
		</div>
		<br>
		<br>
		<a class="btn btn-primary" href="./index.html">Back to Home Page</a>
	</div>
</body>
</html>