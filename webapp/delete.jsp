<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
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

<div class='title'><a href="/"><h1>martalizer.se</h1></a></div>
<div class='menu'>${content}</div>
<p>
	
	<tr><td>
	<div id='gallery'>
	
		<c:forEach items="${bilder}" var="bild">

			<p><a href=/image?file=${bild.filename}&type=medium rel='bild'><img src='/image?file=${bild.filename}&type=thumb'></a> 
						<a class= 'deletebutton' href='deletethisimage?id=${bild.id}'>Delete Image</a></p>

		</c:forEach>
		
		
	</div>
	</td></tr>
  </body>
</html>