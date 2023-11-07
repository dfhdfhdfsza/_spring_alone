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
	<form action="/board/register" method="post" enctype="multipart/form-data">
		Title:<input type="text" name="title"> <br> Content: <input
			type="text" name="content"> <br> Text: <input
			type="text" name="writer"> <br>
		<div>
			<!-- <label for="exampleFormControlInput1" class="form-label">File</label> -->
			<input type="file" id="files" name="files" style="display: none;"
				multiple="multiple">
			<!-- input button trigger 용도의 button -->
			<button type="button" id="trigger" >FileUpload</button>
		</div>
		<div id="fileZone"></div>
		<button type="submit" id="regBtn">등록</button>
	</form>
<script type="text/javascript" src="/resources/boardRegister.js"></script>

</body>
</html>