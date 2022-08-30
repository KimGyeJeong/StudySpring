<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<link href="/resources/member/css/style.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div>
		<br />
		<h1 align="center">로그인</h1>
		<%--로그인 처리는 시큐리티가 처리, 경로와 method 주의 --%>
		<form action="/login" method="post">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}">
			<table>
					<%-- name 속성 시큐리티에 맞게 해주어야함 --%>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="checkbox" name="remember-me"/> 자동로그인</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="로그인" /> <input
						type="button" value="회원가입"
						onclick="window.location='/common/signup'" /> <input
						type="button" value="메인" onclick="window.location='/common/main'" />
					</td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>