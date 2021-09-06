package ro.thales.mytools.registryapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.thales.mytools.registryapp.entities.Booking;
import ro.thales.mytools.registryapp.repositories.AppUserRepository;
import ro.thales.mytools.registryapp.requests.BookingRequest;
import ro.thales.mytools.registryapp.requests.EmailRequest;
import ro.thales.mytools.registryapp.requests.StatusUpdateRequest;
import ro.thales.mytools.registryapp.responses.BookingResponse;
import ro.thales.mytools.registryapp.services.BookingService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/booking")
@AllArgsConstructor
@CrossOrigin()
public class BookingController {
    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings(){
        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Booking> addBooking(@RequestBody BookingRequest bookingRequest){
        return new ResponseEntity<>(bookingService.addBooking(bookingRequest),HttpStatus.OK);
    }

    @PostMapping(path = "/bookingreq")
    public ResponseEntity<List<BookingResponse>> getAllBookingRequests(@RequestBody EmailRequest emailRequest){
        return ResponseEntity.ok(bookingService.getAllBookingRequests(emailRequest.getEmail()));
    }

    @PutMapping(path = "/update/{booking_id}")
    public void updateStatus(@PathVariable Long booking_id, @RequestBody StatusUpdateRequest statusUpdateRequest){
        this.bookingService.updatestatus(booking_id,statusUpdateRequest.getStatus());
    }
}
