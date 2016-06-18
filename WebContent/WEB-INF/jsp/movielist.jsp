<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page isELIgnored="false" %>

<html>
<head>
<title>Insert title here</title>
	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
	<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" hre5f="<c:url value="/resources/css/styles.css" />">
	<script src="<c:url value="/resources/js/functions.js" />"></script>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
</head>
<body>
<h2>Movie List</h2>
<div class="container-fluid">
<form id="searchForm" class="form-horizontal" role="form" method="GET" action="/MovieTracker/search">
	<div class="col-sm-10">
		<input name="searchTitle" type="text" class="form-control" placeholder="Movie name...">
	</div>
	<div class="col-sm-offset-1">
		<button id="searchButton" type="submit" class="btn btn-success">Search</button>
	</div>
</form>
</div>

<div class="container-fluid">
	<div class="row table-responsive">
	<table class="table table-hover table-condensed">
	  <thead>
	    <tr>
	      <th class="col-md-1">View Date</th>
	      <th class="col-md-4">Title</th>
	      <th class="col-md-2">Director</th>
	      <th class="col-md-1">Year</th>
	      <th class="col-md-1">Runtime</th>
	      <th class="col-md-3">Genre</th>
	    </tr>
	  </thead>
	  <tbody class="searchable">
			<c:if test="${not empty lists}">
				<c:forEach var="a" items="${lists}">
					<tr class="tableRow" />
						<td><c:out value="${a.date}" /></td>
						<td><c:out value="${a.title}" /></td>
						<td><c:out value="${a.director}" /></td>
						<td><c:out value="${a.release}" /></td>
						<td><c:out value="${a.runtime}" /></td>
						<td><c:out value="${a.genre}" /></td>
					<tr>
				</c:forEach>
			</c:if>
	  </tbody>
	</table>
	</div>
</div>
</body>
</html>