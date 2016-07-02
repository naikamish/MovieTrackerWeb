package com.movietracker;

import java.util.Date;

public class Episode {
	private Date date;
	private String title;
	private String imdbID;
	private String tmdbID;
	private int runtime;
	private int release;
	private int episode;
	private int season;
	private Show show;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getRelease() {
		return release;
	}
	public void setRelease(int release) {
		this.release = release;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImdbID() {
		return imdbID;
	}
	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}
	public String getTmdbID() {
		return tmdbID;
	}
	public void setTmdbID(String tmdbID) {
		this.tmdbID = tmdbID;
	}
	public int getRuntime() {
		return runtime;
	}
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	public int getEpisode() {
		return episode;
	}
	public void setEpisode(int episode) {
		this.episode = episode;
	}
	public int getSeason() {
		return season;
	}
	public void setSeason(int season) {
		this.season = season;
	}
	public Show getShow() {
		return show;
	}
	public void setShow(Show show) {
		this.show = show;
	}
	
}
