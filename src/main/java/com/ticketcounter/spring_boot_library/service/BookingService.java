package com.ticketcounter.spring_boot_library.service;
import com.ticketcounter.spring_boot_library.dao.BookedSeatsRepository;
import com.ticketcounter.spring_boot_library.dao.BookingRepository;
import com.ticketcounter.spring_boot_library.dto.SeatStatusResponse;
import com.ticketcounter.spring_boot_library.entity.Booking;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Transactional
    public void performCancelBooking(Long bookingId) throws MessagingException {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        Booking booking = bookingOptional.orElseThrow(() -> new RuntimeException("Booking not found"));

        bookingRepository.cancelBooking(bookingId);

        String emailContent = generateCancellationEmailContent(booking);
        String userEmail = booking.getUser().getEmail();

        emailService.sendEmail(userEmail, "Booking Cancelled", emailContent, null, null);

    }

    private String generateCancellationEmailContent(Booking booking) {
        StringBuilder content = new StringBuilder();
        content.append("<html>")
                .append("<head>")
                .append("<style>")
                .append("body { font-family: Arial, sans-serif; margin: 20px; color: #333; }")
                .append("h1 { color: #d9534f; }")
                .append("p { margin: 0 0 10px 0; }")
                .append(".container { max-width: 600px; margin: auto; padding: 20px; border: 1px solid #ddd; border-radius: 8px; }")
                .append(".header { text-align: center; margin-bottom: 20px; }")
                .append(".header img { width: 100px; }")
                .append(".content { line-height: 1.6; }")
                .append(".footer { text-align: center; margin-top: 20px; font-size: 0.9em; color: #555; }")
                .append("</style>")
                .append("</head>")
                .append("<body>")
                .append("<div class='container'>")
                .append("<div class='header'>")
                .append("<h1>Booking Cancelled</h1>")
                .append("</div>")
                .append("<div class='content'>")
                .append("<p>Dear ").append(booking.getUser().getUsername()).append(",</p>")
                .append("<p>Your booking has been cancelled. Here are the details:</p>")
                .append("<p><strong>Movie:</strong> ").append(booking.getShow().getMovie().getTitle()).append("</p>")
                .append("<p><strong>Theatre:</strong> ").append(booking.getShow().getTheatre().getName()).append("</p>")
                .append("<p><strong>Seats:</strong> ").append(booking.getBookedSeats().stream().map(seat -> seat.getSeat().getSeatNumber()).collect(Collectors.joining(", "))).append("</p>")
                .append("<p><strong>Amount Refunded:</strong> ").append(booking.getAmountPaid()).append("</p>")
                .append("</div>")
                .append("<div class='footer'>")
                .append("<p>We hope to serve you again in the future.</p>")
                .append("<p>Best Regards,</p>")
                .append("<p>The Ticket Counter Team</p>")
                .append("</div>")
                .append("</div>")
                .append("</body>")
                .append("</html>");

        return content.toString();
    }
}
