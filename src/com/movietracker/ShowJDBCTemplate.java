package com.movietracker;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class ShowJDBCTemplate implements ShowDAO{
  private DataSource dataSource;
  private JdbcTemplate jdbcTemplateObject;
  
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
  }
  
  public List<Show> listShows() {
    String SQL = "select title, imdbid from showlist";
    List <Show> shows = jdbcTemplateObject.query(SQL, new ShowMapper());
    return shows;
  }

  public void create(String imdbID, String tmdbID, String title, int runtime) {
    String SQL = "insert into showlist(imdbid, tmdbid, title, runtime) values (?, ?, ?, ?)";
    jdbcTemplateObject.update( SQL, imdbID, tmdbID, title, runtime);
    return;
  }
}