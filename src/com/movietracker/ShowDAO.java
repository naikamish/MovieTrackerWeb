package com.movietracker;

import java.util.List;

import javax.sql.DataSource;

public interface ShowDAO {
	public void setDataSource(DataSource ds);
	public List<Show> listShows();
}
