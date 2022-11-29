<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri ="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>/sample/all</h1>
<sec:authorize access="isAnonymous()">
<a href="/admin/customLogin">로그인</a>
</sec:authorize><!-- 익명의 사용자 -->
<sec:authorize access="isAuthenticated()">
<a href="/admin/customLogout">로그아웃</a>
</sec:authorize><!-- 인증된 사용자-->
</body>
</html>