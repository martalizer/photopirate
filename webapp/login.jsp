<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<div id="login">
	<p>Login</p>
	<form action='login' method='post'>
		<label for='username'>Username:</label> 
		<input type='text' name='username' id='username'><br>

		<label for='password'>Password:</label> 
		<input type='password' name='password' id='password'><br>

		<input type='submit' name='submit' value='Submit'>
	</form>
	<div><a href='/register.jsp'>Register User</a></div>
</div>	
	
	<!--<p>Create User</p>
	<form action='register' method='post'>
		<label for='username'>Username:</label> 
			<input type='text' name='username' id='username'><br>
		<label for='password'>Password:</label> 
			<input type='password' name='password' id='password'><br>
		<input type='submit' name='submit' value='Submit'>
	</form>-->

</body>
</html>