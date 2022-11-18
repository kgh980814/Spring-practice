<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ include file="../includes/header.jsp"%>

<div class="wrap">
	<div class="row">
		<div class="col-md-12">
			<div class="widget">
				<header class="widget-header">
					<h4 class="widget-title">Board View</h4>
				</header>
				<!-- .widget-header -->
				<hr class="widget-separator">
				<div class="widget-body">
					<div class="m-b-lg"></div>
					<form method="post" class="form-horizontal" action="">
						<input type="hidden" name="bno" value="${board.bno }" />

						<div class="form-group">
							<label for="exampleTextInput1" class="col-sm-3 control-label">Title:</label>
							<div class="col-sm-9">
								<c:out value="${board.title }"></c:out>
							</div>
						</div>
						<div class="form-group">
							<label for="textarea1" class="col-sm-3 control-label">Content:</label>
							<div class="col-sm-9">
								<%
								pageContext.setAttribute("newLineChar", "\n");
								%>
								${fn:replace(board.content, newLineChar,"<br/>")}
							</div>
						</div>
						<div class="form-group">
							<label for="exampleTextInput1" class="col-sm-3 control-label">Writer:</label>
							<div class="col-sm-9">
								<c:out value="${board.writer }"></c:out>
							</div>
						</div>
						<div class="form-group">
							<label for="exampleTextInput1" class="col-sm-3 control-label">Regdate:</label>
							<div class="col-sm-9">
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
									value="${board.regdate }"></fmt:formatDate>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-9 col-sm-offset-3">
								<a href="modify?bno=${board.bno }" class="btn btn-danger btn-sm">Modify
									Button</a>
								<button type="button" id="btn_remove"
									class="btn btn-danger btn-sm">Remove Button</button>
								<a href="javascript:history.go(-1);"
									class="btn btn-danger btn-sm">List Button</a>
							</div>
						</div>
					</form>
				</div>
				<!-- .widget-body -->
			</div>
			<!-- .widget -->
			<!-- reply Register Modal  -->
			<div class="widget">
				<div class="mail-item">
					<div style="display: inline-block; height: 32px; padding-top: 9px;">Reply</div>
					<div style="display: inline-block; float: right;">
						<button data-toggle="modal" data-target="#composeModal"
							class="btn btn-danger btn-sm" onclick="btn_new()">New Reply</button>
					</div>
				</div>
			</div>
			<!-- reply Register ModalEND  -->
			<!-- reply list -->
				<div id="chat">
					
				</div>
				<!-- END reply list -->
				


		</div>
		<!-- END column -->
	</div>
</div>
<!-- class="wrap" -->
<!-- Compose modal -->
<div class="modal fade" id="composeModal" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">New Reply</h4>
			</div>
			<div class="modal-body">
				<form action="#">
					<input type="hidden" name="rno" id="rno">
					<textarea name="reply" id="reply" cols="30" rows="5"
						class="form-control" placeholder="content"></textarea>

					<div class="form-group">
						<input name="replyer" id="replyer" type="text"
							class="form-control" placeholder="writer">
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" id="btn_modify" data-dismiss=""class="btn btn-success"><i class="fa fa-pencil"></i></button>
				<button type="button" id="btn_del" data-dismiss="" class="btn btn-danger"><i class="fa fa-trash"></i></button>
				<button type="button" id="btn_reply" data-dismiss=""class="btn btn-primary">Send <i class="fa fa-send"></i></button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<script>
function btn_new(){
	$("#rno").val('');
	$("#reply").val('');
	$("#replyer").val('');
	console.log("click");
	$(".modal-footer").empty();
	let btn_footer = "";
	btn_footer += '<button type="button" id="btn_reply" data-dismiss=""class="btn btn-primary">Send <i class="fa fa-send"></i></button';
	$(".modal-footer").html(btn_footer);
	
}
function btn_modal(t){
	let rno = $(t).data("rno");
	let reply = $(t).data("reply");
	let replyer =$(t).text();
	//console.log("수정삭제"+rno);
	
	$("#reply").val(reply);
	$("#replyer").val(replyer);
	$("#rno").val(rno);
	
	$(".modal-footer").empty();
	let btn_footer = "";
	btn_footer += '<button type="button" id="btn_modify" data-dismiss=""class="btn btn-success"><i class="fa fa-pencil"></i></button>';
	btn_footer += '<button type="button" id="btn_del" data-dismiss="" class="btn btn-danger"><i class="fa fa-trash"></i></button>';

	$(".modal-footer").html(btn_footer);
}
function getList() {
		$.ajax({
			type : "GET", // HTTP method type(GET, POST,,,,,) 형식이다.
			url : "/admin/replies/pages/${board.bno }/1.json", // 컨트롤러에서 대기중인 URL 주소이다.
			//contentType: 'application/json',
			//data : JSON.stringify(data),            // Json 형식의 데이터이다.
			//dataType:"json",
			success : function(res) { // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
				//let jsonArr = JSON.parse(res);
			let html = "";
			
				for (let i = 0; i < res.length; i++) {
			html += '<div class="widget">';
			html += '<div class="mail-item">';
			html += '<table class="mail-container">';
			html += '<tr>';
			html += '<td class="mail-center">';
			html += '<div class="mail-item-header">';
			html += '<h4 class="mail-item-title">';
			html += '<span data-toggle="modal" data-target="#composeModal" class="title-color" onclick="btn_modal(this)" data-reply="'+res[i].reply+'" data-rno="'+res[i].rno+'">'+res[i].replyer+'</span></h4>';
			html += '</div>';
			html += '<p class="mail-item-excerpt">'+res[i].reply+'</p>';
			html += '</td>';
			html += '<td class="mail-right" style="width: 160px">';
			html += '<p class="mail-item-date">'+(new Date(res[i].replyDate).toLocaleString())+'</p>';
			html += '</td>';
			html += '</tr>';
			html += '</table>';
			html += '</div>';
			html += '</div>';
					console.log("댓글번호" + res[i].rno);
					console.log("댓글내용" + res[i].reply);
					console.log("댓글작성자" + res[i].replyer);
				}
				$("#chat").html(html);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
				console.log("통신 실패.");
			}
		});
	}

	$(document).ready(function() {
		//ajax
		getList();

		$("#btn_remove").on("click", function() {
			if (confirm("정말로 삭제하시겠습니까?")) {
				$("form").attr("action", "remove");
				$("form").submit();
			}
			//console.log("삭제버튼 눌림");
		});

		$("#btn_reply").on("click", function() {
			let bno = ${board.bno};
			let reply = $("#reply").val();
			let replyer = $("#replyer").val();

			let data = {
				bno : bno,
				reply : reply,
				replyer : replyer
			};

			if (reply == '') {
				alert("댓글내용을 입력해주세요");
			} else if (replyer == '') {
				alert("작성자를 입력해주세요");
			} else {

				//ajax
				$.ajax({
					type : "POST", // HTTP method type(GET, POST,,,,,) 형식이다.
					url : "/admin/replies/new", // 컨트롤러에서 대기중인 URL 주소이다.
					contentType : 'application/json',
					data : JSON.stringify(data), // Json 형식의 데이터이다.
					success : function(res) { // 비동기통신의 성공일경우 success콜백으로 들어옵니다. 'res'는 응답받은 데이터이다.
						console.log("댓글등록성공"+res);
						 if(res =='Success'){
							 getList();
						 }
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) { // 비동기 통신이 실패할경우 error 콜백으로 들어옵니다.
						console.log("통신 실패.");
					}
				});

				$("#composeModal").modal('hide');
			}
			console.log("send버튼 눌림" + reply + replyer);
		});
	});
</script>

<%@ include file="../includes/footer.jsp"%>