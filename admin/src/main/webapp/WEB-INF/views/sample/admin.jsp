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
<h1>/sample/admin</h1>

<p>principal: <sec:authentication property="principal"/><!--로그인한 사용자의 정보-->
<p>MemberVO: <sec:authentication property="principal.member"/><!-- 멤버정보 -->
<p>사용자이름 : <sec:authentication property="principal.member.userName"/><!-- 사용자이름 -->
<p>권한리스트 : <sec:authentication property="principal.member.authList"/><!-- 권한리스트 -->
<a href ="/admin/customLogout">로그아웃</a>
</body>
</html>