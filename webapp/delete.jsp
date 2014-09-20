<!DOCTYPE html>
<head>
	<title>martalizer.se</title>	
	<link rel="stylesheet" href="/css/style.css" type="text/css">
</head>

<body>

<script type="text/javascript">
function openFileOption()
{
	document.getElementById("file1").click();
}
</script>

<form id="apa" action="/upload_file" method='post' enctype='multipart/form-data'>
	<input onchange="this.form.submit()" type="file" name='file' id="file1" style="display:none">
</form>

<div class='title'><a href="/"><h1>photopoop</h1></a></div>
<div class='menu'>${content}</div>
<p>
	
	<tr><td>
	<div id='gallery'>
		${bilder}
	</div>
	</td></tr>
  </body>
</html>