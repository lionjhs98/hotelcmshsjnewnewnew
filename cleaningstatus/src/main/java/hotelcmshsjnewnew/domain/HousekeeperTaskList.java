package hotelcmshsjnewnew.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

//<<< EDA / CQRS
@Entity
@Table(name = "HousekeeperTaskList_table")
@Data
public class HousekeeperTaskList {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long housekeepingId;

    private Long housekeeperId;
    private Long accomodationId;
    private Boolean cleaned;
    private Long roomId;
}
