package ro.thales.mytools.registryapp.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@AllArgsConstructor
@Data
public class BookingRequest {

    @NotNull(message = "request_by_id cannot be null")
    @Min(value=1, message="request_by_id must be equal or greater than 1")
    private Long request_by_id;

    @NotNull(message = "request_for_id cannot be null")
    @Min(value=1, message="request_for_id must be equal or greater than 1")
    private Long request_for_id;

    @NotNull(message = "startDate cannot be null")
    private Date startDate;

    @NotNull(message = "endDate cannot be null")
    private Date endDate;

    @NotNull(message = "accessFloors cannot be null")
    @NotEmpty(message = "accessFloors cannot be empty")
    private int[] accessFloors;

    @NotNull(message = "kitNeeded cannot be null")
    @NotEmpty(message = "kitNeeded cannot be empty")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+", message = "kitNeeded cannot be whitespaces")
    private String kitNeeded;

    @NotNull(message = "status cannot be null")
    @Min(value=0, message="status be equal or greater than 0")
    @Max(value=2, message="status be equal or less than 2")
    private Integer status;
}
