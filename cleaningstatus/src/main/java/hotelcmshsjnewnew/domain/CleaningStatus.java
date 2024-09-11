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

        /** Example 1:  new item 
        CleaningStatus cleaningStatus = new CleaningStatus();
        repository().save(cleaningStatus);

        */

        /** Example 2:  finding and process
        
        repository().findById(housekeeperAssigned.get???()).ifPresent(cleaningStatus->{
            
            cleaningStatus // do something
            repository().save(cleaningStatus);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
