package com.java.po;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "movie", catalog = "test")
public class Movie extends Extend implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer movieId;
	private String movieName;
	private String moviePicture;
	private String movieGnosis;
	private Integer movieShow;
	private String movieRemarks;
	private Timestamp movieShowTime;
	private Timestamp movieCreateTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "movieId", unique = true, nullable = false)
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	@Column(name = "movieName", length = 25)
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	@Column(name = "moviePicture", length = 100)
	public String getMoviePicture() {
		return moviePicture;
	}
	public void setMoviePicture(String moviePicture) {
		this.moviePicture = moviePicture;
	}
	@Column(name = "movieGnosis", length = 100)
	public String getMovieGnosis() {
		return movieGnosis;
	}
	public void setMovieGnosis(String movieGnosis) {
		this.movieGnosis = movieGnosis;
	}
	@Column(name = "movieShow")
	public Integer getMovieShow() {
		return movieShow;
	}
	public void setMovieShow(Integer movieShow) {
		this.movieShow = movieShow;
	}
	@Column(name = "movieRemarks", length = 100)
	public String getMovieRemarks() {
		return movieRemarks;
	}
	public void setMovieRemarks(String movieRemarks) {
		this.movieRemarks = movieRemarks;
	}
	@Column(name = "movieShowTime")
	public Timestamp getMovieShowTime() {
		return movieShowTime;
	}
	public void setMovieShowTime(Timestamp movieShowTime) {
		this.movieShowTime = movieShowTime;
	}
	@Column(name = "movieCreateTime")
	public Timestamp getMovieCreateTime() {
		return movieCreateTime;
	}
	public void setMovieCreateTime(Timestamp movieCreateTime) {
		this.movieCreateTime = movieCreateTime;
	}
}
