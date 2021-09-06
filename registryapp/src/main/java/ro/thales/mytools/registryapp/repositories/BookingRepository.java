package ro.thales.mytools.registryapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.thales.mytools.registryapp.entities.Booking;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query(value = "SELECT BOOKINGS.* FROM BOOKINGS JOIN APP_USER" +
            " ON REQUEST_BY_ID = APP_USER.ID WHERE BOOKINGS.STATUS = 2" +
            " AND APP_USER.TEAM_ID =" +
            " (SELECT TEAMS.ID FROM TEAMS JOIN APP_USER ON TEAMS.ID = TEAM_ID WHERE APP_USER.EMAIL = :u_email)", nativeQuery = true)
    Optional<List<Booking>> getAllBookingRequests(@Param("u_email") String email);
}
