<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div  class="container p-3 my-3 border">
		<form action="<%=request.getContextPath()%>/LoginController" method="post">
			<h1>Login</h1>
			<table class='table table-border table-hover'>
				<tr>
					<td>MemberID :</td>
					<td><input type="text" name="memberId"></td>
				</tr>
				<tr>
					<td>MemberPw :</td>
					<td><input type="password" name="memberPw"></td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit" class="btn btn-secondary">로그인</button>
					</td>
				</tr>
			</table>
		</form>
		<div >
			<a href="<%=request.getContextPath()%>/InsertMemberController" class="btn btn-primary">회원 가입</a>
		</div>
	</div>

</body>
</html>