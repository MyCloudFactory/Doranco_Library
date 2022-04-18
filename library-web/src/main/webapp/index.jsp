<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ZE Library</title>
</head>
<body>
	<h1>Liste des livres</h1>
	<ul>
		<c:forEach items="${books}" var="book">
			<li>${book}</li>
		</c:forEach>

	</ul>
</body>
</html>