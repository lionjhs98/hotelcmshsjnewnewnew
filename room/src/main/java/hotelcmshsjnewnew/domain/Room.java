package hotelcmshsjnewnew.domain;

import hotelcmshsjnewnew.RoomApplication;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.transaction.Transactional;

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

    //@Transactional
    public static void cleaningStatusUpdate(
        CleaningStatusUpdated cleaningStatusUpdated
    ) {
        System.out.println("CleaningStatusUpdated Object" + cleaningStatusUpdated.toString());
        repository().findById(cleaningStatusUpdated.getRoomId()).ifPresent(room -> {
            room.setCleaned(true); // 청소 상태 업데이트
            System.out.println("cleaningStatusUpdated : " + room.toString());
            repository().save(room);
        });
    }

    //@Transactional
    public static void checkinStatusUpdate(
        CheckInInfoRegistered checkInInfoRegistered
    ) { 
        System.out.println("CheckInInfoRegistered Object" + checkInInfoRegistered.toString());
        repository().findById(checkInInfoRegistered.getRoomId()).ifPresent(room -> {
            room.setCheckedIn(true); // 체크인 상태 업데이트
            room.setCleaned(false); // 청소 상태 업데이트
            System.out.println("checkInInfoRegistered : " + room.toString());
            repository().save(room);
        });

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
