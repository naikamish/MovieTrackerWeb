$(document).ready(function(){
	var searchTitle = getUrlParameter('searchTitle');
	var request = new XMLHttpRequest();

	request.open('GET', 'https://api.themoviedb.org/3/search/tv?api_key=78d8e4223cc69693424d6ca812c1bfcb&query='+searchTitle);

	request.setRequestHeader('Accept', 'application/json');

	request.onreadystatechange = function () {
	  if (this.readyState === 4 && this.status === 200) {
		  var myArr = JSON.parse(this.responseText);
		  $.each(myArr.results, function(index,elem){
			  $("#searchTable tbody").append('<tr data-id="'+elem.id+'"><td><img src="https://image.tmdb.org/t/p/w185'
					  								+elem.poster_path+'"></td><td>'
					  								+elem.original_name +'</td><td>'
					  								+elem.overview+'</td></tr>');
		  });

	  }
	};

	request.send();
	$("#searchTable tbody").on("click", "tr", function(){
		var tmdbID = $(this).data('id');
		
		var req = new XMLHttpRequest();

		req.open('GET', 'https://api.themoviedb.org/3/tv/'+tmdbID+'?api_key=78d8e4223cc69693424d6ca812c1bfcb&append_to_response=external_ids');

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
    var imdbID = myArr.external_ids.imdb_id.substring(2);
    var title = myArr.original_name;
    var runtime = 0;
    $('#addShow #runtime').val( runtime );
	$('#addShow #tmdbID').val( tmdbID );
	$('#addShow #imdbID').val( imdbID );
	$('#addShow #title').val( title );
	$('#addShow').modal('toggle');
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
