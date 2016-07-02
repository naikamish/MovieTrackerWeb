package com.movietracker;

import java.util.List;

import javax.sql.DataSource;

public interface EpisodeDAO {
	public void setDataSource(DataSource ds);
	public List<Episode> listEpisodes();
}
