package com.movietracker;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class EpisodeJDBCTemplate implements EpisodeDAO{
  private DataSource dataSource;
  private JdbcTemplate jdbcTemplateObject;
  
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
  }
  
  public List<Episode> listEpisodes() {
  	SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplateObject)
  			.withProcedureName("usp_getShowList")
  			.returningResultSet("episodes", new EpisodeMapper());
    Map<String, Object> out = jdbcCall.execute();
    List<Episode> episodes = (List<Episode>)out.get("episodes"); 
    return episodes;
  }
  
  public String getImdbID(String showTmdbID){
  	String SQL = "select imdbid from showlist where tmdbid=?";
  	String imdbID = jdbcTemplateObject.queryForObject(SQL, new Object[]{showTmdbID}, String.class);
  	return imdbID;
  }
  
  public void create(Date date, String showImdbID, String showTmdbID, String episodeImdbID, String tmdbID, String title, int release, int runtime, int season, int episode) {
  	//String SQL = "select imdbid from showlist where tmdbid=?";
  	//String imdbID = jdbcTemplateObject.queryForObject(SQL, new Object[]{showTmdbID}, String.class);
  	
  	String SQL = "insert into episodelist(imdbid, tmdbid, title, runtime, releaseDate, season, episode) values (?, ?, ?, ?, ?, ?, ?)";
    jdbcTemplateObject.update( SQL, episodeImdbID, tmdbID, title, Integer.toString(runtime), Integer.toString(release), season, episode);
    
    SQL = "insert into episodewatched(viewdate, showimdbid, episodeimdbid) values (?, ?, ?)";
    jdbcTemplateObject.update( SQL, date, showImdbID, episodeImdbID);
    return;
  }
}
