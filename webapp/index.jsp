<!DOCTYPE html>
<head>
	<title>martalizer.se / photopirate</title>	
	<script type='text/javascript' src='/jquery/jquery-1.8.2.min.js'></script>
	<link rel='stylesheet' href='/jquery-colorbox/colorbox.css' type='text/css' media='screen' />
	<script type='text/javascript' src='/jquery-colorbox/jquery.colorbox-min.js'></script>
	<link rel='stylesheet' href='/../css/jquery.justifiedgallery.css' type='text/css' media='all' />
	<script type='text/javascript' src='/../js/jquery.justifiedgallery.js'></script>
	<link rel="stylesheet" href="/style.css" type="text/css">
</head>

<body>
<div class='title'><a href="/"><h1>martalizer</h1></a>
</div>
<div class='menu'>${content}</div>
<p>	
	<div id='gallery'>
		${bilder}
	</div>
    <script type="text/javascript">
	    $("#gallery").justifiedGallery({
	    'extension':'',
	    'sizeRangeSuffixes' : {'lt100':'','lt240':'', 'lt320':'', 'lt500':'', 'lt640':'', 'lt1024':''},
	   			
	    'justifyLastRow' : false,
	    'margins' : 5
	    });
    </script>
  </body>
</html>