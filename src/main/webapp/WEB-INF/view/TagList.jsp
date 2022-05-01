<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	List<Map<String, Object>> list = (List<Map<String, Object>>)request.getAttribute("list");
%>
	<div class="container my-3 p-3">
		<h1>tag rank</h1>
		<div> 수입/지출별 검색</div>
		<div> 날짜별 검색</div>
		<table class="table border table-hover">
			<tr>
				<th>rank</th>
				<th>tag</th>
				<th>count</th>
			</tr>
			<%
				for(Map<String, Object> map : list) {
			%>
					<tr>
						<td><%=map.get("rank")%></td>
						<td><a href=""><%=map.get("tag")%></a></td>
						<td><%=map.get("cnt")%></td>
					</tr>
			<%			
				}
			%>
		</table>
	</div>

</body>
</html>