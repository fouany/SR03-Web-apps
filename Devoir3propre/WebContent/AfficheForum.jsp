<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Affiche Forum</title>
</head>
<body>
	<c:forEach items="${requestScope.forums}" var="forum">
		<h2>${forum.title}</h2>
		<h3>${forum.description}</h3>
		<a href="AfficheMessage?id=${forum.id}&abonne=${requestScope.abonne}">Accéder au forum</a>
		<c:choose>
			<c:when test="${requestScope.isadmin=='1'}">
				<form action="DeleteForum">
					<input type='hidden' name='id' value='${forum.id}'>
					<input type="submit" value="Supprimer le forum">
				</form>
			</c:when>
		</c:choose>
	</c:forEach>
</body>
</html>
