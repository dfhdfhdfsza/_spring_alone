<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/nav.jsp"></jsp:include>
	<c:set value="${bdto.bvo}" var="bvo"/>
	<form action="/board/modify" method="post" enctype="multipart/form-data">
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
		<c:forEach items="${bdto.flist}" var="fvo">
			<li data-uuid="${fvo.uuid}">
				<img src="/upload/${fn:replace(fvo.saveDir,'\\','/') }/${fvo.uuid}_th_${fvo.fileName}">
				<span>${fvo.fileSize}Byte</span>
				<button type="button" class="fileMod">X</button>
			</li>
		</c:forEach>
		<div>
			<label>File</label>
			<input type="file" id="files" name="files" style="display: none;" multiple="multiple">
			<!-- input button trigger 용도의 버튼 -->
			<button type="button" id="trigger">FileUpload</button>
		</div>
		<!-- 첨부파일 표시될 영역 -->
		<div id="fileZone">
			
		</div>
		<button type="submit" id="regBtn">수정</button>
	</form>
	<script type="text/javascript" src="/resources/boardRegister.js"></script>
	
</body>
</html>