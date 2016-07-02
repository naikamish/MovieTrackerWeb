package com.movietracker;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ShowMapper implements RowMapper<Show>{
  public Show mapRow(ResultSet rs, int rowNum) throws SQLException {
    Show show = new Show();
    
    show.setTitle(rs.getString("title"));
    show.setImdbID(rs.getString("imdbID"));
    
    return show;
  }
}
