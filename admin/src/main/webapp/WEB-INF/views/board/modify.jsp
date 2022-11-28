<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../includes/header.jsp"%>

<div class="wrap">
	<div class="row">
		<div class="col-md-12">
			<div class="widget">
				<header class="widget-header">
					<h4 class="widget-title">Board Modify</h4>
				</header>
				<!-- .widget-header -->
				<hr class="widget-separator">
				<div class="widget-body">
					<div class="m-b-lg">
						
					</div>
					<form id="frm" method="post" class="form-horizontal" action="">
						<input type="hidden" name="bno" value="${board.bno }"/>
						
						<div class="form-group">
							<label for="exampleTextInput1" class="col-sm-3 control-label">Title:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control input-sm" name="title" id="title"
									placeholder="Title" required="required" value="${board.title }">
							</div>
						</div>
						<div class="form-group">
							<label for="textarea1" class="col-sm-3 control-label">Content:</label>
							<div class="col-sm-9">
								<textarea class="form-control input-sm" name="content" id="content"
									placeholder="Your content..." required="required">${board.content }</textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="exampleTextInput1" class="col-sm-3 control-label">Writer:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control input-sm" name="writer" id="writer"
									placeholder="Writer" readonly="readonly" value="${board.writer }">
							</div>
						</div>
						<div class="form-group">
							<label for="uploadFile" class="col-sm-3 control-label">File:</label>
							<div class="col-sm-9 uploadDiv">
								<input type="file" class="form-control input-sm" style="height:35px " name="uploadFile" id="uploadFile"
									multiple="multiple" >
							</div>
						</div>
						<div class="form-group">
							<label for="uploadFile" class="col-sm-3 control-label"></label>
							<div class="col-sm-9 uploadResult">
							<ul style="display:flex">
							
							</ul>								
							</div>
						</div><!-- 첨부파일 END-->
						<div class="form-group">
							<div class="col-sm-9 col-sm-offset-3">
								<button type="submit" class="btn btn-danger btn-sm">Modify Button</button>
								<a href="javascript:history.go(-2);" class="btn btn-danger btn-sm">List Button</a>
							</div>
						</div>
					</form>
				</div>
			
				<!-- .widget-body -->
			</div>
			<!-- .widget -->
		</div>
		<!-- END column -->
	</div>
</div>
<!-- class="wrap" -->
<script>
var regex = new RegExp("(.*?)\.(jpg|png|gif|bmp|zip|hwp)$");
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
	
	if(!uploadResultArr || uploadResultArr.length == 0){
		return;
	}
	let str ="";
	
	$(uploadResultArr).each(function(i,obj){
		if(obj.image){ //이미지일경우에 처리
		var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
		var fileRealPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
		
		str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'><a href='../download?fileName=" + fileRealPath + "'>";
		str += "<img src='../display?fileName=" + fileCallPath + "'></a>";
		str += "<span data-realFile='"+fileRealPath+"' data-file='"+fileCallPath+"' data-type='image'>X</span></li>";
		}else{
			var fileRealPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
			
			str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'><a href='../download?fileName=" + fileRealPath + "'>";
			str += '<i class="fa fa-save" aria-hidden="true"></i></a>';
			str += "<span data-realFile='"+fileRealPath+"' data-file='"+fileCallPath+"' data-type='file'>X</span></li>";
			
		}
		
		
	});//원본 파일
	
	$(".uploadResult ul").append(str);
}
$(document).ready(function(){
	$("button[type=submit]").on("click",function(e){
		e.preventDefault();
		let str = "";
		$(".uploadResult ul li").each(function(i,obj){
			console.log(obj);
			str += '<input type="hidden" name="attachList['+i+'].fileName" value="'+$(obj).data('filename')+'">';//attchList:BoardVO
			str += '<input type="hidden" name="attachList['+i+'].uuid" value="'+$(obj).data('uuid')+'">';
			str += '<input type="hidden" name="attachList['+i+'].uploadPath" value="'+$(obj).data('path')+'">';
			str += '<input type="hidden" name="attachList['+i+'].fileType" value="'+$(obj).data('type')+'">';
		});
		$("#frm").append(str).submit();
		//console.log("submit");클릭 버튼 체크
	});
	
	var cloneObj = $(".uploadDiv").clone();//클론 현재 업로드 Div를 복사
	$(".uploadResult").on("click", "span", function(){
		$(this).parent().remove();
	});
	
	
	$("input[type=file]").on("change",function(){
		var formData = new FormData();//Form클래스안에 FORM 객체를 만들겠다.
		var inputfile = $("input[name = uploadFile]");
		var files = inputfile[0].files;
		//console.log(files);

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
				$('#uploadFile').val('');
				console.log(result);
				//파일목록 출력
				showUploadFile(result);
			}
		});
	});
	 
	 var bno = '${board.bno }';
		$.getJSON("./getAttachList", {bno:bno}, function(arr){
			console.log(arr);
			
			let str = "";
			$(arr).each(function(i, attach){
				var fileRealPath 
				= encodeURIComponent(attach.uploadPath + "/"+attach.uuid + "_" + attach.fileName);
				
				//그림파일
				if(attach.fileType){				
					var fileCallPath 
					= encodeURIComponent(attach.uploadPath + "/s_"+attach.uuid + "_" + attach.fileName);
					
					str += "<li style='padding:5px;' data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'><a href='../download?fileName=" + fileRealPath + "'>";
					str += '<a href="../download?fileName='+fileRealPath+'">';
					str += '<img src="../display?fileName='+fileCallPath+'">';
					str += '</a>';
					str += "<span data-realFile='"+fileRealPath+"' data-file='"+fileCallPath+"' data-type='image'>X</span></li>";
					str += '</li>';
					
				} else {
					str += "<li style='padding:5px;' data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'><a href='../download?fileName=" + fileRealPath + "'>";
					str += '<a href="../download?fileName='+fileRealPath+'">';
					str += '<i class="fa fa-save" aria-hidden="true"></i>';
					str += '</a>';
					str += "<span data-realFile='"+fileRealPath+"' data-file='"+fileCallPath+"' data-type='file'>X</span></li>";
					str += '</li>';
				}
			});
			$(".uploadResult ul").html(str);//html형식으로 추가함  html.()
		});
	
});

</script>
<%@ include file="../includes/footer.jsp"%>