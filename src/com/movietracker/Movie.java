package com.movietracker;

import java.util.Date;

public class Movie {
	private String title;
	private int release;
	private int runtime;
	private String director;
	private String genre;
	private Date date;
	private String imdbID;
	private String tmdbID;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getRelease() {
		return release;
	}
	public void setRelease(int release) {
		this.release = release;
	}
	public int getRuntime() {
		return runtime;
	}
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		String gen = genre.replaceAll(",", ", ");
		this.genre = gen;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
