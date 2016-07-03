$(document).ready(function(){
	var request = new XMLHttpRequest();

	request.open('GET', '/MovieTracker/showlist');

	request.setRequestHeader('Accept', 'application/json');

	request.onreadystatechange = function () {
	  if (this.readyState === 4 && this.status === 200) {
		  var myArr = JSON.parse(this.responseText);
		  console.log(myArr);
		  var data=[];
		  $.each(myArr, function(index,elem){
			  var myObj = {
					    "value" : elem.tmdbID,    
					    "label" : elem.title   
					};
			   data.push(myObj);
		  });
		  console.log(data);
		  $("#showTitle").autocomplete({
			    source: data,
				select: function (event, ui) {
			        $("#showTitle").val(ui.item.label); // display the selected text
			        $("#showTmdbID").val(ui.item.value);
			        console.log($("#showTmdbID").val());
			        return false;
			    }
			});
	  }
	};
	request.send();
	
	$("#submitForm").click("click", function(e){
		e.preventDefault();
		var season = $("#season").val();
		var episode = $("#episode").val();
		var tmdbID = $("#showTmdbID").val();
		var request2 = new XMLHttpRequest();

		request2.open('GET', 'https://api.themoviedb.org/3/tv/'+tmdbID+'/season/'+season+'/episode/'+episode+'?api_key=78d8e4223cc69693424d6ca812c1bfcb&append_to_response=external_ids');

		request2.setRequestHeader('Accept', 'application/json');

		request2.onreadystatechange = function () {
		  if (this.readyState === 4 && this.status === 200) {
			  var respArr = JSON.parse(this.responseText);
			  var episodeImdbID = respArr.external_ids.imdb_id.substring(2);
			  $("#imdbID").val(episodeImdbID);
			  $("#tmdbID").val(respArr.id);
			  $("#title").val(respArr.name);
			  $("#release").val((new Date(respArr.air_date)).getFullYear());
			  $("#addEpisodeForm").submit();
		  }
		};
		request2.send();
	});
});

$(function() {
    $('.date-picker').datepicker( {
        changeMonth: true,
        changeYear: true,
        showButtonPanel: true
    });
});