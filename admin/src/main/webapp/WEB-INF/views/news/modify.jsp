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
						<small> Use Bootstrap's predefined grid classes to align
							labels and groups of form controls in a horizontal layout by
							adding <code>.form-horizontal</code> to the form (which doesn't
							have to be a <code>&lt;form&gt;</code>). Doing so changes <code>.form-groups</code>
							to behave as grid rows, so no need for <code>.row</code>.
						</small>
					</div>
					<form method="post" class="form-horizontal" action="">
						<input type="hidden" name="bno" value="${news.bno }"/>
						
						<div class="form-group">
							<label for="exampleTextInput1" class="col-sm-3 control-label">Title:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control input-sm" name="title" id="title"
									placeholder="Title" required="required" value="${news.title }">
							</div>
						</div>
						<div class="form-group">
							<label for="textarea1" class="col-sm-3 control-label">Content:</label>
							<div class="col-sm-9">
								<textarea class="form-control input-sm" name="content" id="content"
									placeholder="Your content..." required="required">${news.content }</textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="exampleTextInput1" class="col-sm-3 control-label">Writer:</label>
							<div class="col-sm-9">
								<input type="text" class="form-control input-sm" name="writer" id="writer"
									placeholder="Writer" readonly="readonly" value="${news.writer }">
							</div>
						</div>
						
						<div class="row">
							<div class="col-sm-9 col-sm-offset-3">
								<button type="submit" class="btn btn-success btn-sm">Modify Button</button>
								<a href="javascript:history.go(-2);" class="btn btn-success btn-sm">List Button</a>
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