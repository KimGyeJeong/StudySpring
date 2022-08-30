<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifypage</title>
<link href="/resources/member/css/style.css" rel="stylesheet"
	type="text/css">

</head>
<body>
	<h1>ModifyPage</h1>


	<div>
		<form action="/member/modify" method="post">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}">
			<table>
				<tr>
					<td>아이디 *</td>
					<td>${member.id }</td>
				</tr>
				<tr>
					<td>비밀번호 *</td>
					<td><input type="text" name="pw" /></td>
				</tr>
				<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" name="pwck" /></td>
				</tr>
				<tr>
					<td>이름 *</td>
					<td><input type="text" name="name" value="${member.name}" /></td>
				</tr>
				<tr>
					<td>성별</td>
					<td><c:if test="${member.gender == 'male'}">남자</c:if> <c:if
							test="${member.gender == 'female'}">여자</c:if></td>
				</tr>
				<tr>
					<td>email</td>
					<td><input type="text" name="email" value="${member.email }" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="정보 수정" /> <input
						type="reset" value="재작성" /> <input type="button" value="취소"
						onclick="window.location='/common/main'" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>