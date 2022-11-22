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
	<h1>Upload with Ajax</h1>
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple="multiple"
			accept="image/*">
	</div>
	<button id="uploadBtn">Upload</button>
	
	
	<div class="uploadResult">
		<ul>
			
		</ul>
	</div>
	
	
	
	<script>
	var regex = new RegExp("(.*?)\.(jpg|png|gif|bmp)$");
	var maxSize = 1024 * 1024 * 5;//5MB
	function checkExtension(fileName, fileSize){//지역변수선언
	
		//용량체크
		if (fileSize >= maxSize) {
			alert("파일 사이즈 초과");
			return false;
		}

		//확장자체크
		if (!regex.test(fileName)) {
			alert("해당 종류의 파일은 업로드할 수 없습니다. ");
			return false;
		}
		return true;
	}
	function showUploadFile(uploadResultArr){
		let str ="";
		$(uploadResultArr).each(function(i,obj){
			str += "<li>" +obj.fileName+ "</li>";
		});
		
		$(".uploadResult ul").append(str);
	}
	$(document).ready(function() {
		
		var cloneObj = $(".uploadDiv").clone();//클론 현재 업로드 Div를 복사
		
		$("#uploadBtn").on("click", function() {
			var formData = new FormData();//Form클래스안에 FORM 객체를 만들겠다.
			var inputfile = $("input[name = uploadFile]");
			var files = inputfile[0].files;
			console.log(files);

			for (var i = 0; i < files.length; i++) {
				if (!checkExtension(files[i].name, files[i].size)) {
					return false;//더이상 반복하지않기 위해 return선언
				}

				formData.append("uploadFile", files[i], files[i].name);

			}

			$.ajax({
				url : "/admin/uploadAjaxAction",
				processData : false,
				contentType : false,
				data : formData,
				type : "POST",
				success : function(result) {
					/* console.log(result); */
					
					$(".uploadDiv").html(cloneObj.html());//파일선택 초기화
					
					//파일목록 출력
					showUploadFile(result);
				}
			});
		});
	});
	</script>
</body>
</html>