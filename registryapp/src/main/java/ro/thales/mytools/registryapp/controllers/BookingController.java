package ro.thales.mytools.registryapp.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.thales.mytools.registryapp.ResourceNotFoundException;
import ro.thales.mytools.registryapp.entities.Booking;
import ro.thales.mytools.registryapp.entities.Template;
import ro.thales.mytools.registryapp.repositories.AppUserRepository;
import ro.thales.mytools.registryapp.requests.BookingRequest;
import ro.thales.mytools.registryapp.requests.EmailRequest;
import ro.thales.mytools.registryapp.requests.StatusUpdateRequest;
import ro.thales.mytools.registryapp.requests.TemplateRequest;
import ro.thales.mytools.registryapp.responses.BookingResponse;
import ro.thales.mytools.registryapp.services.BookingService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/booking")
@AllArgsConstructor
@CrossOrigin()
public class BookingController {
    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings(){
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PostMapping(path = "/getasman")
    public ResponseEntity<List<BookingResponse>> getAllBookingAsMan(@RequestBody EmailRequest emailRequest){
        return ResponseEntity.ok(bookingService.getAllBookingAsMan(emailRequest.getEmail()));
    }

    @PostMapping(path = "/getasusr")
    public ResponseEntity<List<BookingResponse>> getAllBookingAsUsr(@RequestBody EmailRequest emailRequest){
        return ResponseEntity.ok(bookingService.getAllBookingAsUsr(emailRequest.getEmail()));
    }

    @PostMapping
    public ResponseEntity<Booking> addBooking(@RequestBody BookingRequest bookingRequest){
        return ResponseEntity.ok(bookingService.addBooking(bookingRequest));
    }

    @PostMapping(path = "/bookingreq")
    public ResponseEntity<List<BookingResponse>> getAllBookingRequests(@RequestBody EmailRequest emailRequest){
        return ResponseEntity.ok(bookingService.getAllBookingRequests(emailRequest.getEmail()));
    }

    @PutMapping(path = "/update/{booking_id}")
    public void updateStatus(@PathVariable Long booking_id, @RequestBody StatusUpdateRequest statusUpdateRequest){
        this.bookingService.updatestatus(booking_id,statusUpdateRequest.getStatus());
    }

    @DeleteMapping(path = "/delete/{id}")
    public Map<String, Boolean> deleteBooking(@PathVariable(value = "id") Long bookingId){
        return bookingService.deleteBooking(bookingId);
    }

    @GetMapping(path= "/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable(value = "id") Long bookingId){
        return ResponseEntity.ok(bookingService.getBookingById(bookingId));
    }

    @PutMapping(path= "/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable(value = "id") Long bookingId,
                                                 @RequestBody BookingRequest bookingRequest) throws ResourceNotFoundException {

        Booking updatedBooking = bookingService.updateBooking(bookingId,bookingRequest);
        return ResponseEntity.ok(updatedBooking);
    }
}
