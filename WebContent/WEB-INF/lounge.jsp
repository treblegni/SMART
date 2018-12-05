<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	  	
		<link rel="stylesheet" href="https://bootswatch.com/4/pulse/bootstrap.css">
		
		<title>The Lounge!</title>
	</head>
	<body>
		<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
			<a class="navbar-brand" href="#">Navbar</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			<div class="collapse navbar-collapse" id="navbarColor01">
				<ul class="navbar-nav mr-auto">
			    	<li class="nav-item">
			      		<a class="nav-link" href="index.html">Home <span class="sr-only">(current)</span></a>
			    	</li>
			    	<li class="nav-item">
			      		<a class="nav-link" href="about-us.html">About Us</a>
			    	</li>
			    	<li class="nav-item active">
		    			<a href="UserValidation" class="nav-link">Lounge</a>
		    		</li>
			    	<li class="nav-item">
			    		<a class="nav-link" href="Logout">Logout</a>
			    	</li>
			  	</ul>
			</div>
		</nav>
		<div style="margin-top: 20px;text-align:center">
			<h1 class="text text-primary">The Lounge</h1>
		</div>
		<div class="container" style="margin-top: 10px;text-align:center">
			<br>
			<div>
				<p>Create a room.</p>
				<a href="CreateRoom">
					<button type="button" class = "btn btn-primary">Create Room</button>
				</a>
			</div>
			<br>
			<div>
				<p>Join a room.</p>
				<a href="Join">
					<button type="button" class = "btn btn-primary">Join Room</button>
				</a>
			</div>
		</div>
	</body>
</html>