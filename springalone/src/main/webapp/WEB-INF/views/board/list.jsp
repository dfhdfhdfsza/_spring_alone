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
	
	<form action="/board/list" method="get">
		<c:set value="${ph.pgvo.type}" var="typed"></c:set>
		<select name="type">
			<option ${typed eq null ? 'selected':''}>choose...</option>
			<option value="t" ${typed eq 't' ? 'selected':''}>Title</option>
			<option value="w" ${typed eq 'w' ? 'selected':''}>writer</option>
			<option value="c" ${typed eq 'c' ? 'selected':''}>content</option>
			<option value="tw" ${typed eq 'tw' ? 'selected':''}>Title or Writer</option>
			<option value="cw" ${typed eq 'cw' ? 'selected':''}>Title or Content</option>
			<option value="tcw" ${typed eq 'tcw' ? 'selected':''}>all</option>
		</select>
		<input type="search" name="keyword" value="${ph.pgvo.keyword}">
		<input type="hidden" name="pageNo" value="1">
		<input type="hidden" name="qty" value="${ph.pgvo.qty}">
		<button type="submit">Search<span>${ph.totalCount}</span></button> 
	</form> 
	
	<table border="1">
		<thead>
			<tr>
				<th>bno</th>
				<th>title</th>
				<th>content</th>
				<th>writer</th>
				<th>registerDate</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="bvo">
				<tr>
					<td><a href="/board/detail?bno=${bvo.bno}"> ${bvo.bno}</a></td>
					<td>${bvo.title }</td>
					<td>${bvo.content }</td>
					<td>${bvo.writer }</td>
					<td>${bvo.regAt }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item ${(ph.prev eq false)? 'disabled':''}"><a
				class="page-link"
				href="/board/list?pageNo=${ph.startPage-1}&qty=${ph.pgvo.qty}&keyword=${ph.pgvo.keyword}&type=${ph.pgvo.type}">prev</a></li>
			<c:forEach begin="${ph.startPage}" end="${ph.endPage}" var="i">
				<li class="page-item"><a class="page-link" href="/board/list?pageNo=${i}&qty=${ph.pgvo.qty}&keyword=${ph.pgvo.keyword}&type=${ph.pgvo.type}">${i}</a></li>
			</c:forEach>
			<li class="page-item ${(ph.next eq false)? 'disabled':''}"><a
				class="page-link"
				href="/board/list?pageNo=${ph.endPage+1}&qty=${ph.pgvo.qty}&keyword=${ph.pgvo.keyword}&type=${ph.pgvo.type}">next</a></li>
		</ul>
	</nav>

</body>
</html>