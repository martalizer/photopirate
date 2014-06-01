<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>
    <center><div id="fb-root"></div>
    <script>
      window.fbAsyncInit = function() {
        FB.init({
          appId      : '748812261795853',
          status     : true,
          xfbml      : true
        });
      };

      (function(d, s, id){
         var js, fjs = d.getElementsByTagName(s)[0];
         if (d.getElementById(id)) {return;}
         js = d.createElement(s); js.id = id;
         js.src = "//connect.facebook.net/en_US/all.js";
         fjs.parentNode.insertBefore(js, fjs);
       }(document, 'script', 'facebook-jssdk'));
    </script>
<!--    
<div id="login">
	<p>Register User</p>
	<form action='register' method='post'>
		<label for='username'>Username:</label> 
			<input type='text' name='username' id='username'><br>
		<label for='password'>Password:</label> 
			<input type='password' name='password' id='password'><br>
		<input type='submit' name='submit' value='Submit'>
	</form>
	<a href='https://www.facebook.com/dialog/oauth?client_id=748812261795853&redirect_uri=http%3A%2F%2Fmartalizer.se%2Fregister'>FB</a>
</div>	

	-->
<fb:registration redirect_uri="http://martalizer.se/facebookreg" fields="name,email"/>
</body>
</html>