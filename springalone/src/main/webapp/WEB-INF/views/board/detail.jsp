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

	<!-- 댓글 영역 -->
	<div>
		<span id="cmtWriter">writer</span> <input type="text" id="cmtText">
		<button type="button" id="cmtPostBtn">등록</button>
	</div>
	<!-- 댓글 표시 라인 -->
	<table id="tb">
		<thead>
			<tr>
				<th>cno</th>
				<th>writer</th>
				<th>modAt</th>
				<th>Mod</th>
				<th>Del</th>
			</tr>
		</thead>
		<tbody id="cmtArea"></tbody>
	</table>
	<div>
		<div>
			<button type="button" id="moreBtn" data-page="1"
				class="btn btn-primary" style="visibility: hidden">MORE+</button>
		</div>
	</div>

	<script type="text/javascript">
		let bnoVal = `<c:out value="${bvo.bno}"/>`;
		console.log(bnoVal);
	</script>

	<script type="text/javascript" src="/resources/boardComment.js"></script>

	<script type="text/javascript">
		getCommentList(bnoVal);
	</script>
</body>
</html>