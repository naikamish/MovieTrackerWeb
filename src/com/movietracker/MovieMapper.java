package com.movietracker;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


public class MovieMapper implements RowMapper<Movie>{
  public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
    Movie movie = new Movie();
    movie.setTitle(rs.getString("title"));
    movie.setRelease(rs.getInt("releasedate"));
    movie.setRuntime(rs.getInt("runtime"));
    movie.setDirector(rs.getString("director"));
    movie.setGenre(rs.getString("genre"));
    movie.setDate(rs.getDate("viewdate"));
    movie.setImdbID(rs.getString("imdbid"));
    movie.setTmdbID(rs.getString("tmdbid"));
    return movie;
 }
}
