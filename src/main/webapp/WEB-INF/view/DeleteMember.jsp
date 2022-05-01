<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container border p-3 my-3">
		<h1>Member 삭제</h1>
		<form action="<%=request.getContextPath()%>/DeleteMemberController" method="post">
			<table class="table table-hover border">
				<tr>
					<td>MemberId</td>
					<td><input type="text" name="memberId" value="<%=request.getParameter("memberId")%>" readOnly="readOnly"></td>
				</tr>
				<tr>
					<td>MemberPw</td>
					<td><input type="password" name="memberPw"></td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit" class="btn btn-danger">삭제</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>