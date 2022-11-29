<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Logout Page</title>
</head>
<body>
<h1>Logout Page</h1>
<form method="post" action="/admin/customLogout">
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token}"><!-- post로 값을 넘길때마다 csrf값을 넘겨줘야함 해당값을 쓰지않을경우 security-context에서<security:csrf disabled="true"/> 작성-->
<button>로그아웃</button>
</form>
</body>
</html>