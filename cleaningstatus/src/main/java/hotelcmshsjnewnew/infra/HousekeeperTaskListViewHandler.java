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
public class HousekeeperTaskListViewHandler {

//<<< DDD / CQRS
    @Autowired
    private HousekeeperTaskListRepository housekeeperTaskListRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenHousekeeperAssigned_then_CREATE_1 (@Payload HousekeeperAssigned housekeeperAssigned) {
        try {

            if (!housekeeperAssigned.validate()) return;

            // view 객체 생성
            HousekeeperTaskList housekeeperTaskList = new HousekeeperTaskList();
            // view 객체에 이벤트의 Value 를 set 함
            housekeeperTaskList.setHousekeepingId(housekeeperAssigned.getHousekeepingId());
            housekeeperTaskList.setHousekeeperId(housekeeperAssigned.getHousekeeperId());
            housekeeperTaskList.setAccomodationId(housekeeperAssigned.getAccomodationId());
            // view 레파지 토리에 save
            housekeeperTaskListRepository.save(housekeeperTaskList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void when_then_CREATE_ (@Payload  ) {
        try {

            if (!.validate()) return;

            // view 객체 생성
            HousekeeperTaskList housekeeperTaskList = new HousekeeperTaskList();
            // view 객체에 이벤트의 Value 를 set 함
            housekeeperTaskList.setCleaned(.getCleaned());
            housekeeperTaskList.setFoundLostItem(.getFoundLostItem());
            housekeeperTaskList.set();
            // view 레파지 토리에 save
            housekeeperTaskListRepository.save(housekeeperTaskList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }




//>>> DDD / CQRS
}

