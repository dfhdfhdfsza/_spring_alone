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
<table border="1">
	<tr>
		<th>bno</th>
		<td>${bvo.bno}</td>
	</tr>
	<tr>
		<th>title</th>
		<td>${bvo.title}</td>
	</tr>
	<tr>
		<th>writer</th>
		<td>${bvo.writer}</td>
	</tr>
	<tr>
		<th>content</th>
		<td>${bvo.content}</td>
	</tr>
	<tr>
		<th>regAt</th>
		<td>${bvo.regAt}</td>
	</tr>
	<tr>
		<th>modAt</th>
		<td>${bvo.modAt}</td>
	</tr>
	<tr>
		<th>readCount</th>
		<td>${bvo.readCount}</td>
	</tr>
	<tr>
		<th>cmtQty</th>
		<td>${bvo.cmtQty}</td>
	</tr>
</table>
<a href="/board/modify?bno=${bvo.bno}"><button type="button">수정</button></a>
<a href="/board/remove?bno=${bvo.bno}"><button type="button">삭제</button></a>
</body>
</html>