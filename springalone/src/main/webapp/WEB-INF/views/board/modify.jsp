<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/nav.jsp"></jsp:include>
	<form action="/board/modify" method="post">
		<table>
			<tr>
				<th>bno</th>
				<td><input type="text" value="${bvo.bno}" readonly="readonly"
					name="bno"></td>
			</tr>
			<tr>
				<th>title</th>
				<td><input type="text" value="${bvo.title}" name="title"></td>
			</tr>
			<tr>
				<th>writer</th>
				<td><input type="text" value="${bvo.writer}" name="writer"
					readonly="readonly"></td>
			</tr>
			<tr>
				<th>content</th>
				<td><input type="text" value="${bvo.content}" name="content"></td>
			</tr>
		</table>
		<button type="submit">수정</button>
	</form>

</body>
</html>