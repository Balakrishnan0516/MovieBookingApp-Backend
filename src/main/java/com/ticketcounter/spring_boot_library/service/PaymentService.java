package com.ticketcounter.spring_boot_library.service;

import com.ticketcounter.spring_boot_library.dao.BookedSeatsRepository;
import com.ticketcounter.spring_boot_library.dao.BookingRepository;
import com.ticketcounter.spring_boot_library.dao.SeatRepository;
import com.ticketcounter.spring_boot_library.dao.ShowRepository;
import com.ticketcounter.spring_boot_library.dto.PaymentRequest;
import com.ticketcounter.spring_boot_library.entity.BookedSeat;
import com.ticketcounter.spring_boot_library.entity.Booking;
import com.ticketcounter.spring_boot_library.entity.Seat;
import com.ticketcounter.spring_boot_library.entity.Show;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookedSeatsRepository bookedSeatsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowService showService;

    @Autowired
    private EmailService emailService;

    @Transactional
    public void processPayment(PaymentRequest paymentRequest) throws MessagingException {
        // Find the show entity
        Optional<Show> showOptional = showRepository.findById(paymentRequest.getShowId());
        Show show = showOptional.orElseThrow(() -> new RuntimeException("Show not found"));

        // Create and save booking entity
        Booking booking = new Booking();
        booking.setUser(userService.getUserByUserId(paymentRequest.getUserId()));
        booking.setShow(show);
        booking.setNumberOfSeats(paymentRequest.getSelectedSeats().size());
        booking.setStatus("Confirmed");
        booking.setAmountPaid(paymentRequest.getTotalAmount());
        booking = bookingRepository.save(booking); // Save and get the saved instance

        // Loop through selected seats and book them
        for (String seatNumber : paymentRequest.getSelectedSeats()) {
            // Find seat based on seat number, show, and theatre
            Optional<Seat> seatOptional = seatRepository.findByTheatreIdAndShowIdAndSeatNumber(
                    show.getTheatre().getId(), show.getId(), seatNumber);

            Seat seatEntity = seatOptional.orElseThrow(() -> new RuntimeException("Seat not found: " + seatNumber));

            // Check if the seat is already booked
            if (bookedSeatsRepository.existsBySeatAndShow(seatEntity, show)) {
                throw new RuntimeException("Seat already booked: " + seatNumber);
            }

            // Create and save booked seat entity
            BookedSeat seatBooked = new BookedSeat();
            seatBooked.setUser(userService.getUserByUserId(paymentRequest.getUserId()));
            seatBooked.setShow(show);
            seatBooked.setSeat(seatEntity);
            seatBooked.setBooking(booking);
            bookedSeatsRepository.save(seatBooked);
        }

        // Prepare email content and send email
        String movieImageCid = "movieImage";
        String emailContent = generateEmailContent(paymentRequest, movieImageCid);
        String userEmail = userService.getUserByUserId(paymentRequest.getUserId()).getEmail();

        byte[] movieImageData = show.getMovie().getImage();
        emailService.sendEmail(userEmail, "Booking Confirmation", emailContent, movieImageData, movieImageCid);
    }

    private String generateEmailContent(PaymentRequest paymentRequest, String movieImageCid) {
        // Build email content (no changes necessary here)
        Optional<Show> showOptional = showRepository.findById(paymentRequest.getShowId());
        Show show = showOptional.orElseThrow(() -> new RuntimeException("Show not found"));

        StringBuilder content = new StringBuilder();
        content.append("<html>")
                .append("<head>")
                .append("<style>")
                .append("body { font-family: Arial, sans-serif; margin: 20px; color: #333; }")
                .append("h1 { color: #0056b3; }")
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
                .append("<h1>Booking Confirmation</h1>")
                .append("</div>")
                .append("<div class='content'>")
                .append("<p>Dear ").append(userService.getUserByUserId(paymentRequest.getUserId()).getUsername()).append(",</p>")
                .append("<p>Your booking has been confirmed. Here are the details:</p>")
                .append("<p><strong>Movie:</strong> ").append(show.getMovie().getTitle()).append("</p>")
                .append("<p><strong>Theatre:</strong> ").append(show.getTheatre().getName()).append("</p>")
                .append("<p><strong>Location:</strong> ").append(show.getTheatre().getLocation()).append("</p>")
                .append("<p><strong>Seats:</strong> ").append(String.join(", ", paymentRequest.getSelectedSeats())).append("</p>")
                .append("<p><strong>Amount Paid:</strong> ").append(paymentRequest.getTotalAmount()).append("</p>")
                .append("<p><strong>Movie Poster:</strong></p>")
                .append("<p><img src='cid:movieImage' alt='Movie Poster' width='200' height='300'/></p>")
                .append("</div>")
                .append("<div class='footer'>")
                .append("<p>Thank you for booking with us!</p>")
                .append("<p>Best Regards,</p>")
                .append("<p>The Ticket Counter Team</p>")
                .append("</div>")
                .append("</div>")
                .append("</body>")
                .append("</html>");

        return content.toString();
    }
}

