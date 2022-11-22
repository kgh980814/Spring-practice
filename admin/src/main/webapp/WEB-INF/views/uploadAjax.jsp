<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload with Ajax</title>
<script src="/admin/resources/libs/bower/jquery/dist/jquery.js"></script>
</head>
<body>
<div class="uploadDiv">
	<input type ="file" name="uploadFile" multiple="multiple">
</div>
<button id ="uploadBtn">Upload</button>
<script>
$(document).ready(function(){
	$("#uploadBtn").on("click",function(){
		var formData = new FormData();//Form클래스안에 FORM 객체를 만들겠다.
		var inputfile = $("input[name = uploadFile]");
		var files = inputfile[0].files;
		console.log(files);
	});
});
</script>
</body>
</html>