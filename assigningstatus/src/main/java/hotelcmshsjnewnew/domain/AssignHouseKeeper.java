package hotelcmshsjnewnew.domain;

import hotelcmshsjnewnew.AssigningstatusApplication;
import hotelcmshsjnewnew.domain.HousekeeperAssigned;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AssignHouseKeeper_table")
@Data
//<<< DDD / Aggregate Root
public class AssignHouseKeeper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long housekeepingId;

    private Long accomodationId;

    private Long housekeeperId;

    private Long roomId;

    @PostPersist
    public void onPostPersist() {
        HousekeeperAssigned housekeeperAssigned = new HousekeeperAssigned(this);
        housekeeperAssigned.publishAfterCommit();
    }

    public static AssignHouseKeeperRepository repository() {
        AssignHouseKeeperRepository assignHouseKeeperRepository = AssigningstatusApplication.applicationContext.getBean(
            AssignHouseKeeperRepository.class
        );
        return assignHouseKeeperRepository;
    }
}
//>>> DDD / Aggregate Root
