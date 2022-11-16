<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<%@ include file="../includes/header.jsp" %>

  <div class="wrap">
	  <div class="row">
	  	<div class="col-md-12">
			<div class="widget p-lg">
				<h4 class="m-b-lg">뉴스</h4>
				
				<div style="text-align:right;" class="form-group"><a href="register" class="btn btn-warning btn-sm" role="btn">New Register</a></div>
				
				<div class="table-responsive">
					<table class="table">
						<colgroup>
							<col style="width:80px"/>
							<col style=""/>
							<col style="width:100px"/>
							<col style="width:150px"/>
							<col style="width:150px"/>
						</colgroup>
						<tr>
							<th>#번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
						<c:set value="0" var="rowCnt"/>
						<c:forEach items="${list }" var="news">
						<tr>
							<td><c:out value="${news.bno }"></c:out></td>
							<td><a href="get?bno=<c:out value="${news.bno }"></c:out>">
							<c:out value="${news.title }"></c:out></a></td>
							<td><c:out value="${news.writer }"></c:out></td>
							<td><fmt:formatDate value="${news.regdate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td><fmt:formatDate value="${news.updateDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
						<c:set value="${rowCnt + 1 }" var="rowCnt"/>
						</c:forEach>
						<c:if test="${rowCnt eq 0}">
						<tr>
							<td colspan="5">등록된 글이 없습니다.</td>
						</tr>
						</c:if>
					</table>
				</div>
			</div><!-- .widget -->
		</div><!-- END column -->
	</div>
  </div><!-- class="wrap" -->
  
<%@ include file="../includes/footer.jsp" %>