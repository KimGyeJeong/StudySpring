<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>uploadForm</title>
</head>
<body>
	<h1>uploadForm</h1>
	
	<form action="/upload/uploadPro" method="post" enctype="multipart/form-data">
		message : <input type="text" name="msg"/> <br>
		file : <input type="file" name="img"/> <br>
		<input type="submit" value="전송"/> <br>
	</form>

</body>
</html>