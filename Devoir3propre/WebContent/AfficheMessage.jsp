<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<c:forEach items="${requestScope.message}" var="message">
<h2>${message.content}</h2>
<h3>${message.editor}</h3>
</c:forEach>

<form action="AddMessage">
<textarea name="Text_message" ></textarea>
<input type="submit" value="Envoyer le message">
</form>

</br>
<form action="Subscribe">
<input type="submit" value="S'abonner">
</form>
</body>
</html>