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
						<c:forEach items="${list }" var="news">
						<tr>
							<td><c:out value="${news.bno }"></c:out></td>
							<td><a href="get?bno=<c:out value="${news.bno }"></c:out>">
							<c:out value="${news.title }"></c:out></a>
							<c:if test="${news.replyCnt gt 0}">
								<span class="badge badge-danger"><c:out value="${news.replyCnt }"></c:out></span>
							</c:if>
							</td>
							<td><c:out value="${news.writer }"></c:out></td>
							<td><fmt:formatDate value="${news.regdate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td><fmt:formatDate value="${news.updateDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td><c:out value="${news.hit }"></c:out></td>
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
				<div class="row">
					<div class="col-md-6 pagination">
					<form action="" class="form-inline">
						<select name="type" class="form-control" style="min-width: 10px;">
							<option value="">선택</option>
							<option value="T">제목</option>
							<option value="C">내용</option>
							<option value="W">작성자</option>
							<option value="TC">제목+내용</option>
						</select>
						<input type="text" name="keyword" class="form-control">
						<button class="btn btn-warning"><i class="fa fa-search" aria-hidden="true"></i></button>
					</form>
					</div>
					<div class="col-md-6">				
					<!-- page -->
					<nav style="text-align:right;">
					  <ul class="pagination">
					  	<c:if test="${pageMaker.prev}">
					    <li>
					      <a href="${pageMaker.startPage - 1 }" aria-label="Previous" class="btn_pagination">
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
						    	<a href="${num }" class="btn_pagination">${num }</a>
						    </li>
					    </c:if>
					    </c:forEach>
					    <c:if test="${pageMaker.next}">
					    <li>
					      <a href="${pageMaker.endPage + 1 }" aria-label="Next" class="btn_pagination">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
						</c:if>
					  </ul>
					</nav>
					<!-- page End -->
					</div>
				</div>
			</div><!-- .widget -->
		</div><!-- END column -->
	</div>
  </div><!-- class="wrap" -->
  <!-- list?pageNum=3&amount=10&type=T&keyword=새글 -->
  <form id="frm">
	  <input type="hidden" name="pageNum" value="">
	  <input type="hidden" name="amount" value="${pageMaker.cri.amount }">
	  <input type="hidden" name="type" value="${pageMaker.cri.type }">
	  <input type="hidden" name="keyword" value="${pageMaker.cri.keyword }">
  </form>
  
  <script>
  $(document).ready(function(){
	  $(".btn_pagination").on("click",function(e){
		  e.preventDefault();
		  //console.log("BTN"+$(this).attr("href"));
		  let href = $(this).attr("href");
		  $("input[name=pageNum]").val(href);
		  $("#frm").submit();
	  });
  });
  </script>
  
<%@ include file="../includes/footer.jsp" %>