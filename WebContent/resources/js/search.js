$(document).ready(function(){
	var searchTitle = getUrlParameter('searchTitle');
	var request = new XMLHttpRequest();

	request.open('GET', 'https://api.themoviedb.org/3/search/movie?api_key=78d8e4223cc69693424d6ca812c1bfcb&query='+searchTitle);

	request.setRequestHeader('Accept', 'application/json');

	request.onreadystatechange = function () {
	  if (this.readyState === 4 && this.status === 200) {
		  var myArr = JSON.parse(this.responseText);
		  $.each(myArr.results, function(index,elem){
			  $("#searchTable tbody").append('<tr data-id="'+elem.id+'"><td><img src="https://image.tmdb.org/t/p/w185'
					  								+elem.poster_path+'"></td><td>'
					  								+elem.release_date+'</td><td>'
					  								+elem.title +'</td><td>'
					  								+elem.overview+'</td></tr>');
		  });

	  }
	};

	request.send();
	$("#searchTable tbody").on("click", "tr", function(){
		var tmdbID = $(this).data('id');
		
		var req = new XMLHttpRequest();

		req.open('GET', 'https://api.themoviedb.org/3/movie/'+tmdbID+'?api_key=78d8e4223cc69693424d6ca812c1bfcb&append_to_response=credits');

		req.setRequestHeader('Accept', 'application/json');

		req.onreadystatechange = function () {
		  if (this.readyState === 4 && this.status === 200) {
			  var myArr = JSON.parse(this.responseText);
			  setValues(myArr);
		  }
		};

		req.send();
	});
});

function setValues(myArr){
	console.log(myArr);
    var tmdbID = myArr.id;
    var imdbID = myArr.imdb_id.substring(2);
    var title = myArr.title;
    var release = (new Date(myArr.release_date)).getFullYear();
    var runtime = myArr.runtime;
    var genreArray = [];
    $.each(myArr.genres, function(index,elem){
	    genreArray.push(elem.name);
    });
    var genre = genreArray.join();
    var credits = myArr.credits.crew;
    var director;
    $.each(credits, function(index, elem){
    	if(elem.job==="Director")
    		director = elem.name;
    });
	$('#addMovie #tmdbID').val( tmdbID );
	$('#addMovie #imdbID').val( imdbID );
	$('#addMovie #title').val( title );
	$('#addMovie #release').val( release );
	$('#addMovie #runtime').val( runtime );
	$('#addMovie #genre').val( genre );
	$('#addMovie #director').val( director );
	$('#addMovie').modal('toggle');
}

function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

$(function() {
    $('.date-picker').datepicker( {
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true
    });
});