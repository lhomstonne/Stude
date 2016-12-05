<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
	<meta charset="utf-8" />
	<title>Spring MVC ShopItem</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/edit" var="urlAddItem" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">ShopItem WebApp</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a href="${urlAddItem}">Add Item</a></li>
			</ul>
		</div>
	</div>
</nav>