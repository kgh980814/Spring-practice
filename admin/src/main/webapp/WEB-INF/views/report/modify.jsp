<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../includes/header.jsp"%>

<div class="wrap">
	<div class="row">
		<div class="col-md-12">
			<div class="widget">
				<header class="widget-header">
					<h4 class="widget-title">Report Modify</h4>
				</header>
				<!-- .widget-header -->
				<hr class="widget-separator">
				<div class="widget-body">
					<div class="m-b-lg">
					</div>
					<form method="post" class="form-horizontal" action="">
						<input type="hidden" name="bno" value="${report.bno }"/>
						
						<div class="form-group">
							<label for="textarea1" class="col-sm-3 control-label">업무내용:</label>
							<div class="col-sm-9">
								<textarea class="form-control input-sm" name="content" id="content"
									placeholder="Your content..." required="required">${report.content }</textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="textarea1" class="col-sm-3 control-label">비고:</label>
							<div class="col-sm-9">
								<textarea class="form-control input-sm" name="note" id="note"
									placeholder="Your content..." required="required">${report.note }</textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="textarea1" class="col-sm-3 control-label">특이사항/건의사항:</label>
							<div class="col-sm-9">
								<textarea class="form-control input-sm" name="suggestion" id="suggestion"
									placeholder="Your content..." required="required">${report.suggestion }</textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="exampleTextInput1" class="col-sm-3 control-label">작성자:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control input-sm" name="writer" id="writer"
									placeholder="Writer" readonly="readonly" value="${report.writer }">
							</div>
						</div>
						
						<div class="row">
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

<%@ include file="../includes/footer.jsp"%>