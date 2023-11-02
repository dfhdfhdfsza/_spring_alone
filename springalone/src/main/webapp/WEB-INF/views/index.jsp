<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html>
<head>
<title>Home</title>
</head>
<body>
	<jsp:include page="common/header.jsp"></jsp:include>
	<jsp:include page="common/nav.jsp"></jsp:include>
	<h1>Hello world!</h1>


	<P>The time on the server is ${serverTime}.</P>
</body>
</html>
