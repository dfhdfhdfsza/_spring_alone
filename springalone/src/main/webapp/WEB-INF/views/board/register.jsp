<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/nav.jsp"></jsp:include>
	<form action="/board/register" method="post">
		Title:<input type="text" name="title"> <br> 
		Content: <input type="text" name="content"> <br> 
		Text: <input type="text" name="writer"> <br>
		<button type="submit">등록</button>
	</form>


</body>
</html>