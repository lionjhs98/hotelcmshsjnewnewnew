package hotelcmshsjnewnew.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "ReservationStatus_table")
@Data
public class ReservationStatus {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long accomodationid;

    private Long roomId;
    private Date checkInTime;
    private Date checkOutTime;
}
