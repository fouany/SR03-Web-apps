<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Affiche Utilisateurs</title>
</head>
<body>
	<c:forEach items="${requestScope.user}" var="user">
		<h2>${user.login}</h2>
		<h3>${user.firstName}</h3>
		<h3>${user.lastName}</h3>
		<h3>${user.id}</h3>
				<form action="DeleteUser">
					<input type='hidden' name='lastName' value='${user.lastName}'>
					<input type='hidden' name='firstName' value='${user.firstName}'>
					<input type='hidden' name='id' value='${user.id}'>
					<input type="submit" value="Supprimer l'utilisateur">
				</form>
	</c:forEach>
</body>
</html>
