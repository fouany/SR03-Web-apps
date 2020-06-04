
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 <%--@include file="../src/Model/Forum.java" --%> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
<c:forEach items="${requestScope.forums}" var="forum">
<h2>${forum.title}</h2>
<h3>${forum.description}</h3>
<h3>${forum.id}</h3>
<a href="AfficheMessage?id=1">Accéder au forum</a>
 
</c:forEach>
</body>
</html>
