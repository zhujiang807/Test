package com.java.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_movie", catalog = "test")
public class UserMovie implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer userMovieId;
	private Integer userId;
	private Integer movieId;
	private String movieGnosis;
	private Date createTime;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "userMovieId", unique = true, nullable = false)
	public Integer getUserMovieId() {
		return userMovieId;
	}
	public void setUserMovieId(Integer userMovieId) {
		this.userMovieId = userMovieId;
	}
	@Column(name = "userId")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "movieId")
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "movieGnosis")
	public String getMovieGnosis() {
		return movieGnosis;
	}
	public void setMovieGnosis(String movieGnosis) {
		this.movieGnosis = movieGnosis;
	}
}
