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
	<div class="container border my-3 p-3">
		<h1>Cashbook 수정</h1>
		<form action="<%=request.getContextPath()%>/UpdateCashBookController" method="post">
			<table class="table table-hover">
				<tr>
					<td>CashbookNo</td>
					<td><input type="number" name="cashbookNo" readOnly="readOnly" value="<%=request.getAttribute("cashbookNo") %>"></td>
				</tr>		
				<tr>
					<td>CashDate</td>
					<td><input type="text" name="cashDate"></td>
				</tr>	
				<tr>
					<td>Kind</td>
					<td>
						<input type="radio" name="kind" value="지출">지출
						<input type="radio" name="kind" value="수입">수입
					</td>
				</tr>
				<tr>
					<td>cash</td>
					<td><input type="number" name="cash"></td>
				</tr>
				<tr>
					<td>memo</td>
					<td><textarea rows="4" cols="50" name="memo"></textarea></td>
				</tr>
			</table>
			<button type="submit" class="btn btn-primary">수정</button>
		</form>
	</div>
</body>
</html>