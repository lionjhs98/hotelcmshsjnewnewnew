package hotelcmshsjnewnew.domain;

import hotelcmshsjnewnew.RoomApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Room_table")
@Data
//<<< DDD / Aggregate Root
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;

    private Boolean cleaned;

    private String roomName;

    private String roomType;

    private Boolean checkedIn;

    public static RoomRepository repository() {
        RoomRepository roomRepository = RoomApplication.applicationContext.getBean(
            RoomRepository.class
        );
        return roomRepository;
    }

    //<<< Clean Arch / Port Method
    public static void cleaningStatusUpdate(
        CleaningStatusUpdated cleaningStatusUpdated
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Room room = new Room();
        repository().save(room);

        */

        /** Example 2:  finding and process
        
        repository().findById(cleaningStatusUpdated.get???()).ifPresent(room->{
            
            room // do something
            repository().save(room);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void checkinStatusUpdate(
        CheckInInfoRegistered checkInInfoRegistered
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Room room = new Room();
        repository().save(room);

        */

        /** Example 2:  finding and process
        
        repository().findById(checkInInfoRegistered.get???()).ifPresent(room->{
            
            room // do something
            repository().save(room);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
