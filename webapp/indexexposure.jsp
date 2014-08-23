<!DOCTYPE html>
<head>
	<title>martalizer.se / photopirate</title>	
	<link rel="stylesheet" href="css/style.css" type="text/css">
</head>

<body>
<div class='title'><a href="/"><h1>martalizer</h1></a>
</div>
<div class='menu'>${content}</div>
<p>
	<div id="login">   

	
<FIELDSET>
<LEGEND>Exposure conversion</LEGEND>
<TABLE border="0" cellspacing="3" cellpadding="2">
<FORM METHOD="GET" ACTION="">
<TR>
	<TD>Aperture</TD>
	<TD>ISO</TD>
	<TD>Shutterspeed</TD>
</TR>
<TR>
	<TD>${aperture}</TD>
	<TD>${iso}</TD>
	<TD>${shutter}</td>
</tr>
<TR>
	<TD>${aperture2}</TD>
	<TD>${iso2}</TD>
	<TD>${shutter2}</td>
</tr>

<tr>
	<TD colspan="3">
	<INPUT class="submitfield" TYPE="SUBMIT" VALUE="Calculate!" />
	</td>
</TR>	
</FORM>
</TABLE>

</LEGEND>
</FIELDSET>	
	<p>${result}</p>
	</div>
  </body>
</html>