package com.ticketcounter.spring_boot_library.entity;
import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="movies")
@Data //lombok automatic getters and setters handler
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public byte[] getHorizontalImage() {
		return horizontalImage;
	}

	public void setHorizontalImage(byte[] horizontalImage) {
		this.horizontalImage = horizontalImage;
	}

	public String getYoutubeUrl() {
		return youtubeUrl;
	}

	public void setYoutubeUrl(String youtubeUrl) {
		this.youtubeUrl = youtubeUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(nullable = false, length = 255,name="title")
    private String title;

    @Column(length = 255,name="genre")
    private String genre;

    @Column(length = 255,name="director")
    private String director;

    @Lob //Large Object
    @Column(length = 255,name="synopsis")
    private String synopsis;

    @Lob
    @Column(length = 255,name="image")
    private byte[] image;

    @Lob
    @Column(length = 255,name="horizontal_image")
    private byte[] horizontalImage;

    @Column(length = 255,name="youtube_url")
    private String youtubeUrl;

}
