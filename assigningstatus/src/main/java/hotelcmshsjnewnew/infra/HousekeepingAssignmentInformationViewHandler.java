package hotelcmshsjnewnew.infra;

import hotelcmshsjnewnew.domain.*;
import hotelcmshsjnewnew.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class HousekeepingAssignmentInformationViewHandler {

//<<< DDD / CQRS
    @Autowired
    private HousekeepingAssignmentInformationRepository housekeepingAssignmentInformationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenHousekeeperAssigned_then_CREATE_1 (@Payload HousekeeperAssigned housekeeperAssigned) {
        try {

            if (!housekeeperAssigned.validate()) return;

            // view 객체 생성
            HousekeepingAssignmentInformation housekeepingAssignmentInformation = new HousekeepingAssignmentInformation();
            // view 객체에 이벤트의 Value 를 set 함
            housekeepingAssignmentInformation.setHousekeepingId(housekeeperAssigned.getHousekeepingId());
            housekeepingAssignmentInformation.setHousekeeperId(housekeeperAssigned.getHousekeeperId());
            housekeepingAssignmentInformation.setAccomodationId(housekeeperAssigned.getAccomodationId());
            // view 레파지 토리에 save
            housekeepingAssignmentInformationRepository.save(housekeepingAssignmentInformation);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void when_then_CREATE_ (@Payload  ) {
        try {

            if (!.validate()) return;

            // view 객체 생성
            HousekeepingAssignmentInformation housekeepingAssignmentInformation = new HousekeepingAssignmentInformation();
            // view 객체에 이벤트의 Value 를 set 함
            housekeepingAssignmentInformation.setHousekeepingId(.getHousekeepingId());
            housekeepingAssignmentInformation.setHousekeeperId(.getHousekeeperId());
            housekeepingAssignmentInformation.setAccomodationId(.getAccomodationId());
            housekeepingAssignmentInformation.setCleaned(.getCleaned());
            housekeepingAssignmentInformation.setFoundLostItem(.getFoundLostItem());
            housekeepingAssignmentInformation.set();
            // view 레파지 토리에 save
            housekeepingAssignmentInformationRepository.save(housekeepingAssignmentInformation);

        }catch (Exception e){
            e.printStackTrace();
        }
    }




//>>> DDD / CQRS
}

