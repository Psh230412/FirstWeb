<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body>
	<form method="post">
		<p><input type="text" name="id" value="${ id }"> ${ idError }</p>
		<p><input type="password" name="password" value="${ password }"> ${ passwordError }</p>
		<p><input type="text" name="nickname" value="${ nickname }"> ${ nicknameError }</p>
		<p><input type="submit" value="확인"></p>
	</form>
</body>
	<script></script>
</html>