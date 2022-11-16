<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    
<%@ include file="../includes/header.jsp" %>

  <div class="wrap">
	  <div class="row">
	  	<div class="col-md-12">
			<div class="widget p-lg">
				<h4 class="m-b-lg">게시판</h4>
				
					<div style="text-align:right;" class="form-group">

					<a href="register" class="btn btn-warning btn-sm" role="btn">New Register</a>
					</div>
				
				
				<div class="table-responsive">
					<table class="table">
					
						<colgroup>
							<col style="width:80px"/>
							<col style=""/>
							<col style="width:100px"/>
							<col style="width:150px"/>
							<col style="width:150px"/>
							<col style="width:80px"/>
						</colgroup>
						<tr>
							<th>#번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
							<th>조회수</th>
						</tr>
						<c:set value="0" var="rowCnt"/>
						<c:forEach items="${list }" var="board">
						<tr>
							<td><c:out value="${board.bno }"></c:out></td>
							<td><a href="get?bno=<c:out value="${board.bno }"></c:out>">
							<c:out value="${board.title }"></c:out></a></td>
							<td><c:out value="${board.writer }"></c:out></td>
							<td><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td><fmt:formatDate value="${board.updateDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td><c:out value="${board.hit }"></c:out></td>
						</tr>
						<c:set value="${rowCnt + 1 }" var="rowCnt"/>
						</c:forEach>
						<c:if test="${rowCnt eq 0}">
						<tr>
							<td colspan="6">등록된 글이 없습니다.</td>
						</tr>
						</c:if>
					</table>
				</div>
	
				
				<!-- search -->


				<!-- search end -->
					<!-- page -->
				<nav style="text-align:right;">
<div>			
				  <ul class="pagination">
				  	<c:if test="${pageMaker.prev}">
				    <li>
				      <a href="?pageNum=${pageMaker.startPage - 1 }&amount=${pageMaker.cri.amount }" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				    </li>
				    </c:if>
				    <c:forEach begin="${pageMaker.startPage }" 
				    			end="${pageMaker.endPage }" var="num">
				    <c:if test="${pageMaker.cri.pageNum eq num }">				    
					    <li class="active">
					    	<a>${num }</a>
					    </li>
				    </c:if>
				    <c:if test="${pageMaker.cri.pageNum ne num }">				    
					    <li>
					    	<a href="?pageNum=${num }&amount=${pageMaker.cri.amount }">${num }</a>
					    </li>
				    </c:if>
				    </c:forEach>
				    <c:if test="${pageMaker.next}">
				    <li>
				      <a href="?pageNum=${pageMaker.endPage + 1 }&amount=${pageMaker.cri.amount }" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>
					</c:if>
				  </ul>
      <ul class="nav navbar-nav">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">제목<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">제목</a></li>
            <li class="divider"></li>
            <li><a href="#">내용</a></li>
            <li class="divider"></li>
            <li><a href="#">작성자</a></li>      
          </ul>
        </li>
      </ul>
				<form class="navbar-form navbar-left" style="padding-top:8px; " role="search">
  <div class="form-group">
    <input type="text" class="form-control" style="height: 29px;" placeholder="Search">
  </div>
  <button type="submit" class="btn btn-warning"style="padding: 5px 10px">Submit</button>
</form>
</div>	
				</nav>
				
				<!-- page End -->
			</div><!-- .widget -->
		</div><!-- END column -->
	</div>
  </div><!-- class="wrap" -->
  
<%@ include file="../includes/footer.jsp" %>