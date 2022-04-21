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
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<table border="1">
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
		<a href="<%=request.getContextPath() %>/DeleteCashBookController?cashbookNo=<%=cashbookNo%>">삭제</a>
		<a href="<%=request.getContextPath() %>/UpdateCashBookController?cashbookNo=<%=cashbookNo%>">수정</a>
</body>
</html>