<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ page import="java.util.*" %>
<%
	List<Cashbook> list =(List<Cashbook>)request.getAttribute("list");
	int cashbookNo = (Integer)request.getAttribute("cashbookNo");
	System.out.println(cashbookNo+"One");

%>
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
			<thead>
				<tr>
					<th>CashbookNo</th>
					<th>CashDate</th>
					<th>Kind</th>
					<th>Cash</th>
					<th>Memo</th>
					<th>UpdateDate</th>
					<th>CreateDate</th>
				</tr>
			</thead>
			<tbody>
				<%
					for(Cashbook c: list){
				%> 
					<tr>
						<td><%=c.getCashbookNo()%></td>
						<td><%=c.getCashDate() %></td>
						<td><%=c.getKind() %></td>
						<td><%=c.getCash() %></td>
						<td><%=c.getMemo() %></td>
						<td><%=c.getUpdateDate() %></td>
						<td><%=c.getCreateDate() %></td>
					
					</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<a href="<%=request.getContextPath() %>/DeleteCashBookController?cashbookNo=<%=cashbookNo%>" class="btn btn-danger">삭제</a>
		<a href="<%=request.getContextPath() %>/UpdateCashBookController?cashbookNo=<%=cashbookNo%>" class="btn btn-primary">수정</a>
	</div>
</body>
</html>