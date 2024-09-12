package hotelcmshsjnewnew.infra;

import hotelcmshsjnewnew.config.kafka.KafkaProcessor;
import hotelcmshsjnewnew.domain.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ReservationStatusViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private ReservationStatusRepository reservationStatusRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCheckInInfoRegistered_then_CREATE_1(
        @Payload CheckInInfoRegistered checkInInfoRegistered
    ) {
        try {
            if (!checkInInfoRegistered.validate()) return;

            // view 객체 생성
            ReservationStatus reservationStatus = new ReservationStatus();
            // view 객체에 이벤트의 Value 를 set 함
            reservationStatus.setAccomodationid(
                checkInInfoRegistered.getAccomodationId()
            );
            reservationStatus.setRoomId(checkInInfoRegistered.getRoomId());

            Date utilDate = checkInInfoRegistered.getCheckInTime(); // Date 타입의 변수
            reservationStatus.setCheckInTime(utilDate);

            // view 레파지 토리에 save
            reservationStatusRepository.save(reservationStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCheckoutInfoRegistered_then_CREATE_2(
        @Payload CheckoutInfoRegistered checkoutInfoRegistered
    ) {
        try {
            if (!checkoutInfoRegistered.validate()) return;

            // view 객체 생성
            ReservationStatus reservationStatus = new ReservationStatus();
            // view 객체에 이벤트의 Value 를 set 함
            reservationStatus.setAccomodationid(
                checkoutInfoRegistered.getAccomodationId()
            );
            reservationStatus.setRoomId(checkoutInfoRegistered.getRoomId());

            Date utilDate = checkoutInfoRegistered.getCheckOutTime(); // Date 타입의 변수
            reservationStatus.setCheckOutTime(utilDate);
            // view 레파지 토리에 save
            reservationStatusRepository.save(reservationStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}