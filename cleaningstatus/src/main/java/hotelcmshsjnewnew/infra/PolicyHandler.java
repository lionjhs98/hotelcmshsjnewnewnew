package hotelcmshsjnewnew.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import hotelcmshsjnewnew.config.kafka.KafkaProcessor;
import hotelcmshsjnewnew.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    CleaningStatusRepository cleaningStatusRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='HousekeeperAssigned'"
    )
    public void wheneverHousekeeperAssigned_UpdateAssigningStatus(
        @Payload HousekeeperAssigned housekeeperAssigned
    ) {
        HousekeeperAssigned event = housekeeperAssigned;
        System.out.println(
            "\n\n##### listener UpdateAssigningStatus : " +
            housekeeperAssigned +
            "\n\n"
        );

        // Sample Logic //
        CleaningStatus.updateAssigningStatus(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
