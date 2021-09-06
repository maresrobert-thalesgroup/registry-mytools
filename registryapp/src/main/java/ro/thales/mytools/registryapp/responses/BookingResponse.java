package ro.thales.mytools.registryapp.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BookingResponse implements Serializable {
    private Long id;
    private SimpleUserResponse request_by;
    private SimpleUserResponse request_for;
    private Date startDate;
    private Date endDate;
    private String accessFloors;
    private String kitNeeded;
    private Integer status;
}
