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
		<table class="table table-hover">
			<h1>회원 정보 상세 보기</h1>
			<thead>
				<tr>
					<th>memberId</th>
					<th>Create_date</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><%=request.getAttribute("memberId")%></td>
					<td><%=request.getAttribute("createDate")%></td>
				</tr>
			</tbody>
			
		</table>
		<div>
			<a href="<%=request.getContextPath()%>/UpdateMemberPwController?memberId=<%=request.getAttribute("memberId")%>" class="btn btn-primary">수정</a>
			<a href="<%=request.getContextPath() %>/DeleteMemberController?memberId=<%=request.getAttribute("memberId") %>" class="btn btn-primary">삭제</a>
		</div>
	</div>
	
</body>
</html>