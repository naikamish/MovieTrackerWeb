<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page isELIgnored="false" %>

<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
	<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
	<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
	<script src="<c:url value="/resources/js/functions.js" />"></script>
	<script src="<c:url value="/resources/js/episodelist.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap3-typeahead.min.js" />"></script>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
</head>
<body>
<h2>Episode List</h2>
<div class="container-fluid">
<form id="searchForm" class="form-horizontal" role="form" method="GET" action="/MovieTracker/searchEpisode">
	<div class="col-sm-10">
		<input id="searchShow" name="searchTitle" type="text" class="form-control" placeholder="Show name...">
	</div>
	<div class="col-sm-offset-1">
		<button id="searchButton" type="submit" class="btn btn-success">Search</button>
	</div>
</form>
</div>

<div class="container container-table">
    <div class="row vertical-center-row">
        <div class="text-center col-md-4 col-md-offset-4">
					<button type="button" class="btn btn-success btn-lg" data-toggle="modal" data-target="#addEpisode">
						Add Episode
					</button>
				</div>
    </div>
</div>

<div class="container-fluid">
	<div class="row table-responsive">
	<table class="table table-hover table-condensed">
	  <thead>
	    <tr>
	      <th class="col-md-1">Date</th>
	      <th class="col-md-4">Show Title</th>
	      <th class="col-md-1">Season</th>
	      <th class="col-md-1">Episode</th>
	      <th class="col-md-3">Episode Title</th>
	      <th class="col-md-1">Year</th>
	      <th class="col-md-1">Runtime</th>
	    </tr>
	  </thead>
	  <tbody class="searchable">
			<c:if test="${not empty lists}">
				<c:forEach var="a" items="${lists}">
					<tr class="tableRow" />
						<td><c:out value="${a.date}" /></td>
						<td><c:out value="${a.show.title}" /></td>
						<td><c:out value="${a.season}" /></td>
						<td><c:out value="${a.episode}" /></td>
						<td><c:out value="${a.title}" /></td>
						<td><c:out value="${a.release}" /></td>
						<td><c:out value="${a.runtime}" /></td>
					<tr>
				</c:forEach>
			</c:if>
	  </tbody>
	</table>
	</div>
</div>



<div class="modal fade ui-front" id="addEpisode" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
       					<button type="button" class="close" data-dismiss="modal">
		                <span aria-hidden="true">&times;</span>
		                <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    Add Episode
                </h4>
            </div>
            
            <!-- Modal Body -->
            <div class="modal-body">
                <form:form id="addEpisodeForm" method="POST" action="/MovieTracker/addEpisode" class="form-horizontal" role="form">
                  <div class="form-group">
                    <label  class="col-sm-2 control-label" for="name">Name</label>
                    <div class="col-sm-10">
                    	<form:input path="show.title" class="form-control" id="showTitle" placeholder="Title"/>
                    	<form:input type="hidden" path="imdbID" class="form-control" id="imdbID"/>
                    	<form:input type="hidden" path="tmdbID" class="form-control" id="tmdbID"/>
                    	<form:input type="hidden" path="show.tmdbID" class="form-control" id="showTmdbID"/>
                    	<form:input type="hidden" path="title" class="form-control" id="title"/>
                    	<form:input type="hidden" path="release" class="form-control" id="release"/>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label" for="season" >Season</label>
                    <div class="col-sm-10">
                    	<form:input path="season" class="form-control" id="season" placeholder="Season"/>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label" for="season" >Episode</label>
                    <div class="col-sm-10">
                    	<form:input path="episode" class="form-control" id="episode" placeholder="Episode"/>
                    </div>
                  </div>
                  <div class="form-group">
                    <label class="col-sm-2 control-label" for="date" >Date</label>
                    <div class="col-sm-10">
                        <form:input path="date" class="form-control date-picker" id="date" placeholder="Date" />
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-2">
                      <button type="submit" class="btn btn-success btn-block" id="submitForm">Add</button>
                    </div>
                  </div>
                </form:form>   
            </div>
        </div>
    </div>
</div>
</body>
</html>