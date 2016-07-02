package com.movietracker;

public class Show {
	private String title;
	private String imdbID;
	private String tmdbID;
	private int runtime;
	
	public int getRuntime() {
		return runtime;
	}
	public void setRuntime(int runtime) {
		this.runtime = runtime;
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
}
