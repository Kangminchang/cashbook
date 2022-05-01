<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/InsertMemberController" method="post">
		<h1>회원 가입</h1>
		<table border="1">
			<tr>
				<td>MemberId :</td>
				<td><input type="text" name="memberId"></td>
			</tr>
			<tr>
				<td>MemberPw :</td>
				<td><input type="password" name="memberPw"></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit">회원 가입</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>