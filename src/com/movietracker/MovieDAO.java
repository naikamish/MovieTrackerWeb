package com.movietracker;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

public interface MovieDAO {
	public void setDataSource(DataSource ds);
	public List<Movie> listMovies();
	public void create(String imdbID, String tmdbID, Date date, String title, String director, int runtime, int release, String genre);
}
