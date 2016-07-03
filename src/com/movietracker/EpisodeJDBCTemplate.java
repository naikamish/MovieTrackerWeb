package com.movietracker;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class EpisodeJDBCTemplate implements EpisodeDAO{
  private DataSource dataSource;
  private JdbcTemplate jdbcTemplateObject;
  
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
  }
  
  public List<Episode> listEpisodes() {
    String SQL = "select v.viewdate, s.title as showtitle, e.season, e.episode, e.title as episodetitle, e.releasedate, e.runtime from episodewatched v inner join showlist s on v.showimdbid=s.imdbid inner join episodelist e on v.episodeimdbid=e.imdbid order by viewdate desc, episodewatchedid desc;";
    List <Episode> episodes = jdbcTemplateObject.query(SQL, new EpisodeMapper());
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
