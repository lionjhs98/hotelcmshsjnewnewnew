package hotelcmshsjnewnew.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "HousekeepingAssignmentInformation_table")
@Data
public class HousekeepingAssignmentInformation {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long housekeepingId;

    private Long housekeeperId;
    private Long accomodationId;
    private Long roomId;
}
