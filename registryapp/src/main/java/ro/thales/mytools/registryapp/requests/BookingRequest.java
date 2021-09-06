package ro.thales.mytools.registryapp.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class BookingRequest {
    private Long request_by_id;
    private Long request_for_id;
    private Date startDate;
    private Date endDate;
    private int[] accessFloors;
    private String kitNeeded;
    private Integer status;
}
