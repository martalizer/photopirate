<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>martalizer.se</title>
<script type='text/javascript' src='/jquery/jquery-1.8.2.min.js'></script>
<link rel='stylesheet' href='/jquery-colorbox/colorbox.css'
	type='text/css' media='screen' />
<script type='text/javascript'
	src='/jquery-colorbox/jquery.colorbox-min.js'></script>

<link rel="stylesheet" href="/JG/dist/css/justifiedGallery.css" />
<script src="/JG/dist/js/jquery.justifiedGallery.js"></script>

<link rel="stylesheet" href="/css/style.css" type="text/css">
<script type="text/javascript">
	function openFileOption() {
		document.getElementById("file1").click();
	}
</script>
</head>

<body>
	<form id="apa" action="/upload_file" method='post'
		enctype='multipart/form-data'>
		<input onchange="this.form.submit()" type="file" name='file'
			id="file1" style="display: none">
	</form>

	<div class='title'>
		<a href="/"><h1>martalizer.se${pageinfo}</h1></a>
	</div>
	${pageinfo2}
	<div class='menu'>${content}</div>
	<p>
	<div id='gallery'>
	<p>${message}</p>
		<c:forEach items="${bilder}" var="bild">

			<a title='${bild.username}' href=/image?file=${bild.filename}&type=medium rel='bild'> 
				<img alt='${bild.username}' class='preview'
				src='/image?file=${bild.filename}&type=thumb'>
			</a>

		</c:forEach>

	</div>
	<script type="text/javascript">
		$("#gallery").justifiedGallery({
			'extension' : '',
			'sizeRangeSuffixes' : {
				'lt100' : '',
				'lt240' : '',
				'lt320' : '',
				'lt500' : '',
				'lt640' : '',
				'lt1024' : ''
			},
			rowHeight : 200,
			'lastRow' : "justify",
			'margins' : 5
		});
	</script>
</body>
</html>