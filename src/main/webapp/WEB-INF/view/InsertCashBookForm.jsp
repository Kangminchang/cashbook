<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <!-- Latest compiled and minified CSS -->
   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
   <!-- jQuery library -->
   <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
   <!-- Popper JS -->
   <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
   <!-- Latest compiled JavaScript -->
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>InsertCashBookForm</h1>
		<form action="<%=request.getContextPath() %>/InsertCashBookController" method="post">
			<table class="table table-hover border">
				<tr>
					<td>날짜</td>
					<td><input type="text" name="cashDate" value="<%=(String)request.getAttribute("cashDate")%>" readOnly="readOnly"></td>
				</tr>
				<tr>
					<td>Kind</td>
					<td>
						<input type="radio" name="kind" value="수입">수입
						<input type="radio" name="kind" value="지출">지출
					</td>
				</tr>
				<tr>
					<td>Cash</td>
					<td><input type="number" name="cash"></td>
				</tr>
				<tr>
					<td>Memo</td>
					<td>
						<textarea rows="4" cols="50" name="memo"></textarea>
					</td>
				</tr>
			</table>
			<button type="submit" class="btn btn-primary">입력</button>
		</form>
	</div>
</body>
</html>