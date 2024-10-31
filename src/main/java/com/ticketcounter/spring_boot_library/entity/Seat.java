package com.ticketcounter.spring_boot_library.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="seats")
@Data
public class Seat {

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="theatre_id",nullable = false)
    private Theatre theatre;

    @ManyToOne
    @JoinColumn(name="show_id")
    private Show show;

    @Column(name="seat_number",nullable = false)
    private String seatNumber;

    @Column(name="category",nullable = false)
    private String category;

    @Column(name="rate",nullable = false)
    private String rate;
}
