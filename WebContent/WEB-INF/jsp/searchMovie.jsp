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
	<link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />">
	<script src="<c:url value="/resources/js/functions.js" />"></script>
	<script src="<c:url value="/resources/js/search.js" />"></script>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
</head>
<body>
<div class="container-fluid">
	<div class="row table-responsive">
	<table class="table table-hover table-condensed" id="searchTable">
	  <thead>
	    <tr>
	      <th class="col-md-1"></th>
	      <th class="col-md-2">Release Date</th>
	      <th class="col-md-4">Title</th>
	      <th class="col-md-5">Description</th>
	    </tr>
	  </thead>
	  <tbody class="searchable">
	  </tbody>
	</table>
	</div>
</div>

<div class="modal fade" id="addMovie" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                       <span aria-hidden="true">&times;</span>
                       <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">
                    Add Movie
                </h4>
            </div>
            
            <!-- Modal Body -->
            <div class="modal-body">
                <form:form method="POST" action="/MovieTracker/addMovie" class="form-horizontal" role="form">
                  <form:input type="hidden" path="tmdbID" class="form-control" id="tmdbID"/>
                  <form:input type="hidden" path="imdbID" class="form-control" id="imdbID"/>
                  <form:input type="hidden" path="title" class="form-control" id="title"/>
                  <form:input type="hidden" path="release" class="form-control" id="release"/>
                  <form:input type="hidden" path="runtime" class="form-control" id="runtime"/>
                  <form:input type="hidden" path="director" class="form-control" id="director"/>
                  <form:input type="hidden" path="genre" class="form-control" id="genre"/>
                  <div class="form-group">
                    <label class="col-sm-3 control-label" for="viewDate" >ViewDate</label>
                    <div class="col-sm-9">
                    		<form:input path="date" class="form-control date-picker" id="date" placeholder="Date" />
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                      <button type="submit" class="btn btn-success">Add</button>
                    </div>
                  </div>
                </form:form> 
            </div>
        </div>
    </div>
</div>
</body>
</html>