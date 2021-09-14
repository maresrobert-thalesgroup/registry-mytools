package ro.thales.mytools.registryapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.thales.mytools.registryapp.ResourceNotFoundException;
import ro.thales.mytools.registryapp.entities.Booking;
import ro.thales.mytools.registryapp.entities.Template;
import ro.thales.mytools.registryapp.repositories.AppUserRepository;
import ro.thales.mytools.registryapp.repositories.BookingRepository;
import ro.thales.mytools.registryapp.requests.BookingRequest;
import ro.thales.mytools.registryapp.responses.BookingResponse;
import ro.thales.mytools.registryapp.responses.SimpleUserResponse;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final AppUserRepository appUserRepository;

    public List<BookingResponse> getAllBookings(){
        return bookingRepository.findAll().stream().map(Booking::getBookingResponse).collect(Collectors.toList());
    }

    public Booking addBooking(BookingRequest bookingRequest){
        return bookingRepository.save(Booking.builder()
                .requestBy(appUserRepository.findById(bookingRequest.getRequest_by_id()).get())
                .requestFor(appUserRepository.findById(bookingRequest.getRequest_for_id()).get())
                .startDate(bookingRequest.getStartDate())
                .endDate(bookingRequest.getEndDate())
                .accessFloors(bookingRequest.getAccessFloors())
                .kitNeeded(bookingRequest.getKitNeeded())
                .status(bookingRequest.getStatus())
                .build());
    }

    public List<BookingResponse> getAllBookingRequests(String email){
        return bookingRepository.getAllBookingRequests(email).get().stream().map(Booking::getBookingResponse).collect(Collectors.toList());
    }

    public void updatestatus(Long booking_id, Integer status) {
        Booking booking = this.bookingRepository.findById(booking_id).get();
        booking.setStatus(status);
        this.bookingRepository.save(booking);
    }

    public Map<String, Boolean> deleteBooking(Long bookingId){

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + bookingId));
        bookingRepository.delete(booking);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public BookingResponse getBookingById(Long id){

        Booking booking = bookingRepository.findById(id).get();
        BookingResponse bookingResponse = BookingResponse.builder()
                                          .id(id)
                                          .request_by(new SimpleUserResponse(booking.getRequestBy().getId(),booking.getRequestBy().getEmail()))
                                          .request_for(new SimpleUserResponse(booking.getRequestFor().getId(),booking.getRequestFor().getEmail()))
                                          .startDate(booking.getStartDate())
                                          .endDate(booking.getEndDate())
                                          .accessFloors(booking.getAccessFloors())
                                          .kitNeeded(booking.getKitNeeded())
                                          .status(booking.getStatus())
                                          .build();
        return bookingResponse;
    }

    public Booking updateBooking(Long bookingId, BookingRequest bookingRequest){

        Booking booking = bookingRepository.findById(bookingId).
                orElseThrow(() -> new ResourceNotFoundException("Booking not found for this id :: " + bookingId));

        booking.setRequestBy(appUserRepository.findById(bookingRequest.getRequest_by_id()).get());
        booking.setRequestFor(appUserRepository.findById(bookingRequest.getRequest_for_id()).get());
        booking.setStartDate(bookingRequest.getStartDate());
        booking.setEndDate(bookingRequest.getEndDate());
        booking.setAccessFloors(bookingRequest.getAccessFloors());
        booking.setKitNeeded(bookingRequest.getKitNeeded());
        booking.setStatus(bookingRequest.getStatus());

        bookingRepository.save(booking);

        return booking;
    }
}
