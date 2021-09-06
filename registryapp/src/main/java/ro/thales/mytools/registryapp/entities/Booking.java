package ro.thales.mytools.registryapp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import ro.thales.mytools.registryapp.responses.BookingResponse;
import ro.thales.mytools.registryapp.responses.SimpleUserResponse;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Bookings")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_by_id")
    private AppUser requestBy;

    @ManyToOne
    @JoinColumn(name = "request_for_id")
    private AppUser requestFor;

    private Date startDate;
    private Date endDate;
    private String accessFloors;
    private String kitNeeded;
    @Range(min = 0, max = 2)
    private Integer status;

    public Booking(AppUser requestBy, AppUser requestFor, Date startDate, Date endDate, String accessFloors, String kitNeeded, Integer status) {
        this.requestBy = requestBy;
        this.requestFor = requestFor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.accessFloors = accessFloors;
        this.kitNeeded = kitNeeded;
        this.status = status;
    }

    public BookingResponse getBookingResponse(){
        return BookingResponse.builder()
                .id(this.id)
                .request_by(new SimpleUserResponse(this.requestBy.getId(),this.requestBy.getEmail()))
                .request_for(new SimpleUserResponse(this.requestFor.getId(),this.requestFor.getEmail()))
                .startDate(this.startDate)
                .endDate(this.endDate)
                .accessFloors(this.accessFloors)
                .kitNeeded(this.kitNeeded)
                .status(this.status)
                .build();
    }
}
