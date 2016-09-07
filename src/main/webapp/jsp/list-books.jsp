<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<title>List books</title>
</head>
<body>
<div class="container">
	<h2>Books</h2>
	<form action="${pageContext.request.contextPath}/book" method="get" id="seachBookForm" role="form">
		<div class="form-group col-xs-5">
			<input type="text" name="nameSearch" id="nameSearch" class="form-control" required="true"
				   placeholder="search book..."/>
		</div>
		<button type="submit" class="btn btn-info">
			<span class="glyphicon goliphicon-search"></span>Search
		</button>
		<br></br>
		<input type="radio" id="searchByBookTitle" name="searchAction" value="searchByBookTitle" checked="checked"/>Search
		by book title&nbsp
		<input type="radio" id="searchByAuthorName" name="searchAction" value="searchByAuthorName"/>Search by author
		name
	</form>
	<form action="${pageContext.request.contextPath}/book/${book.id}" method="post" role="form" id="booksForm">
		<c:choose>
			<c:when test="${not empty bookList}">
				<div class="table-responsive">
					<table style="width: auto" class="table table-striped table-bordered">
						<thead>
						<tr>
							<th>Num</th>
							<th>Title</th>
							<th>Price</th>
							<th>Authors</th>
							<th>Number of page</th>
							<th>Description</th>
							<th>Remove</th>
						</tr>
						</thead>
						<c:forEach var="book" items="${bookList}" varStatus="counter">
							<tr class="${classSucess}">
								<td>
									<a href="${pageContext.request.contextPath}/book/${book.id}">${counter.count}</a>
								</td>
								<td>${book.title}</td>
								<td>${book.price}</td>
								<td><c:forEach var="authors" items="${book.authors}" varStatus="loop">
									${authors.name}<c:if test="${!loop.last}">,</c:if>
								</c:forEach>
								</td>
								<td>${book.nbOfPage}</td>
								<td>${book.description}</td>
								<td><a href="#" id="remove"
									   onclick="document.getElementById('booksForm')
										   .action='${pageContext.request.contextPath}/book/delete/${book.id}';
										   document.getElementById('booksForm').submit();">
									<span class="glyphicon glyphicon-trash"/>
								</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<br></br>
				<div class="alert alert-info">
					No people found matching your search creiteria
				</div>
			</c:otherwise>
		</c:choose>
	</form>
	<br></br>
	<a href="${pageContext.request.contextPath}/book?addForm">Add new book</a>
</div>

</body>
</html>
