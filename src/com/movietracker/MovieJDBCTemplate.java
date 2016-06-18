package com.movietracker;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class MovieJDBCTemplate implements MovieDAO{
  private DataSource dataSource;
  private JdbcTemplate jdbcTemplateObject;
  
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
  }
  
  public List<Movie> listMovies() {
    String SQL = "select a.viewdate, b.title, b.releasedate, b.director, b.runtime, b.genre, b.imdbid, b.tmdbid from movie a inner join movielist b on a.imdbid = b.imdbid order by a.viewdate desc";
    List <Movie> movies = jdbcTemplateObject.query(SQL, new MovieMapper());
    return movies;
  }
  
  public void create(String imdbID, String tmdbID, Date date, String title, String director, int runtime, int release, String genre) {
    String SQL = "insert into movie(viewdate, imdbid) values (?, ?)";
    jdbcTemplateObject.update( SQL, date, imdbID);
    
    String SQL2 = "insert into movielist(imdbid, tmdbid, title, releasedate, director, runtime, genre) values (?, ?, ?, ?, ?, ?, ?)";
    jdbcTemplateObject.update( SQL2, imdbID, tmdbID, title, release, director, runtime, genre);
    return;
 }
}
