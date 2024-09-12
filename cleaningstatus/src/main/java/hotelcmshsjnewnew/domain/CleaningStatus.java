package hotelcmshsjnewnew.domain;

import hotelcmshsjnewnew.CleaningstatusApplication;
import hotelcmshsjnewnew.domain.CleaningStatusUpdated;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CleaningStatus_table")
@Data
//<<< DDD / Aggregate Root
public class CleaningStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long housekeepingId;

    private Long accomodationId;

    private Long housekeeperId;

    private Boolean cleaned;

    private Long roomId;

    @PostPersist
    public void onPostPersist() {
        // 이벤트 발행 -> CleaningStatusUpdated (Publish)
        CleaningStatusUpdated cleaningStatusUpdated = new CleaningStatusUpdated(
            this
        );
        cleaningStatusUpdated.publishAfterCommit();
    }

    public static CleaningStatusRepository repository() {
        CleaningStatusRepository cleaningStatusRepository = CleaningstatusApplication.applicationContext.getBean(
            CleaningStatusRepository.class
        );
        return cleaningStatusRepository;
    }

    //<<< Clean Arch / Port Method
    public static void updateAssigningStatus(
        HousekeeperAssigned housekeeperAssigned
    ) {
        //implement business logic here:

        CleaningStatus cleaningStatus = new CleaningStatus();
        cleaningStatus.accomodationId = housekeeperAssigned.getAccomodationId();
        cleaningStatus.housekeeperId = housekeeperAssigned.getHousekeeperId();
        cleaningStatus.roomId = housekeeperAssigned.getRoomId();
        cleaningStatus.cleaned = false;
        repository().save(cleaningStatus);

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
