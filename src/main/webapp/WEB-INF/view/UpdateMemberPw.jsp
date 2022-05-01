<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container border my-3 p-3">
		<form action="<%=request.getContextPath() %>/UpdateMemberPwController" method="post">
			<h1>정보 수정</h1>
			<table class="table table-hover">
				<tr>
					<td>memberId</td>
					<td><input type="text" name="memberId" value="<%=request.getParameter("memberId")%>"></td>
				</tr>
				<tr>
					<td>memberPw</td>
					<td><input type="password" name="memberPw"></td>
				</tr>
				<tr>
					<td colspan="2"><button type="submit" class="btn btn-primary">수정</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>