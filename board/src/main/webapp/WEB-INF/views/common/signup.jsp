<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>signup</title>
<link href="/resources/member/css/style.css" rel="stylesheet"
	type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div id="signupHere">
		<form action="/common/signup" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<table>
				<tr>
					<td>권한</td>
					<td>
					<input type="radio" name="au" value="member" checked="checked"/>일반회원
					<input type="radio" name="au" value="admin" />관리자
					</td>
				</tr>
				<tr>
					<td>아이디 *</td>
					<td><input type="text" name="id" id="id" /></td>
				</tr>
				<tr>
					<td>아이디 사용가능 여부</td>
					<td><input type="text" id="checkResult" disabled="disabled" /></td>
				</tr>
				<tr>
					<td>비밀번호 *</td>
					<td><input type="password" name="pw" /></td>
				</tr>
				<tr>
					<td>비밀번호 확인 *</td>
					<td><input type="password" name="pwch" /></td>
				</tr>
				<tr>
					<td>이름 *</td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>남 <input type="radio" name="gender" value="male" checked />
						여 <input type="radio" name="gender" value="female" />
					</td>
				</tr>
				<tr>
					<td>email</td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="회원 가입" /> <input
						type="reset" value="재작성" /> <input type="button" value="취소"
						onclick="window.location='/common/main'" /></td>
				</tr>
			</table>
		</form>
	</div>
	<!--  
	<script>
		$(document).ready(function() {
			$("#id").change(function() {
				//id 입력란에 값을 입력했을때 실행...
				// 1. id 입력란에 사용자가 입력한 값이 필요
				let idVal = $("#id").val();

				// 정상출력.
				//console.log(idVal);

				// 2. 꺼낸 입력값을 서버에 보내서 DB에 동일한 id가 있는지 체크
				$.ajax({
					type : "post",
					url : "/member/idAvail", //컨트롤러에 requestmapping 필요
					data : {
						id : idVal
					},
					success : function(result) {
						console.log("Success");
						console.log(result);
						// 3. 결과를 아이디 사용가능 여부 input 태그에 value 값으로 출력
						$("#checkResult").val(result);
					},
					error : function(e) {
						console.log("ajax ERROR");
						// e 는 자바의 excepton 같은...
						console.log(e);
					}
				});

			});
		});
	</script>
-->
</body>
</html>