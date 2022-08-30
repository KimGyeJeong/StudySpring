<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<link href="/resources/member/css/style.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<h1>Member Main Page</h1>


	<div>
		<%-- 시큐어리이 태그 사용하여 로그인, 로그아웃 확인 --%>
		<sec:authorize access="isAnonymous()">
			<table>
				<tr>
					<td><button onclick="window.location='/common/login'">로그인</button></td>
				</tr>
				<tr>
					<td><button onclick="window.location='/common/signup'">회원가입</button></td>
				</tr>
				<tr>
					<td><button onclick="window.location='/board/list'">게시판</button></td>
				</tr>
			</table>
		</sec:authorize>

		<%-- 시큐어리티 태그 사용. 로그인 상태 --%>
		<sec:authorize access="isAuthenticated()">
			<table>
				<tr>
					<td>
						<%-- 시큐리티로 로그아웃. /logout post 요청, csrf 토큰 필수 --%>
						<form action="/logout" method="post">
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}">
							<button>로그아웃</button>
						</form>
					</td>
				</tr>
				<tr>
					<td><button onclick="window.location='/member/mypage'">마이페이지</button></td>
				</tr>
				<tr>
					<td><button onclick="window.location='/board/list'">게시판</button></td>
				</tr>
			</table>
			<br>
			<h3>사용자 정보 출력</h3>
			<table>
				<tr>
					<td>principal</td>
					<td><sec:authentication property="principal" /></td>
				</tr>
				<tr>
					<%-- principal이 customuser --%>
					<td>MemberVO</td>
					<td><sec:authentication property="principal.member" /></td>
				</tr>
				<tr>
					<td>사용자이름</td>
					<td><sec:authentication property="principal.member.name" /></td>
				</tr>
				<tr>
					<td>사용자ID</td>
					<td><sec:authentication property="principal.member.id" /></td>
					<td><sec:authentication property="principal.username" /></td>
				</tr>
				<tr>
					<td>권한 리스트</td>
					<td><sec:authentication property="principal.member.authList" /></td>
				</tr>
			</table>
		</sec:authorize>
		<br /> <br />
		<div align="center">
			<img src="/resources/member/imgs/고양이.jpg" width="600" />
		</div>
		<br /> <br />

	</div>

</body>
</html>