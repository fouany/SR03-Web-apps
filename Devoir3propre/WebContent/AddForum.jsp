<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Forum</title>
</head>
<body>
	<form action="AddForum">
		<input type='text' name="titre">Titre</input>
		<input type='text' name="description">Description</input>
		<input type="submit" value="Créerr le forum">
	</form>
</body>
</html>