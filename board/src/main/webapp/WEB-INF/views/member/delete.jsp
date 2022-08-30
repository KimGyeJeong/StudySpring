<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>delete</title>
<link href="/resources/member/css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
<div>
		<form action="/member/delete" method="post">
		<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}">
			<table>
				<tr>
					<td>탈퇴를 원하시면 비밀번호를 입력하세요 <br /> <input type="password"
						name="pw" />
					</td>
				</tr>
				<tr>
					<td><input type="submit" value="탈퇴" /> <input type="button"
						value="취소" onclick="window.location='/member/mypage'" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>