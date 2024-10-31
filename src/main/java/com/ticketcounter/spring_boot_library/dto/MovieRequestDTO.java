package com.ticketcounter.spring_boot_library.dto;

import lombok.Data;

@Data
public class MovieRequestDTO {
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getYoutubeUrl() {
		return youtubeUrl;
	}
	public void setYoutubeUrl(String youtubeUrl) {
		this.youtubeUrl = youtubeUrl;
	}
	public String getHorizontalImage() {
		return horizontalImage;
	}
	public void setHorizontalImage(String horizontalImage) {
		this.horizontalImage = horizontalImage;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	private String title;
    private String genre;
    private String director;
    private String synopsis;
    private String youtubeUrl;
    private String horizontalImage;
    private String image;  
}
