package ro.thales.mytools.registryapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.thales.mytools.registryapp.entities.Booking;
import ro.thales.mytools.registryapp.repositories.AppUserRepository;
import ro.thales.mytools.registryapp.repositories.BookingRepository;
import ro.thales.mytools.registryapp.requests.BookingRequest;
import ro.thales.mytools.registryapp.responses.BookingResponse;

import java.util.List;
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

    public List<BookingResponse> getAllBookingAsMan(String email) {
        return bookingRepository.getAllBookingAsMan(email).get().stream().map(Booking::getBookingResponse).collect(Collectors.toList());
    }

    public List<BookingResponse> getAllBookingAsUsr(String email) {
        return bookingRepository.getAllBookingAsUsr(email).get().stream().map(Booking::getBookingResponse).collect(Collectors.toList());
    }
}
