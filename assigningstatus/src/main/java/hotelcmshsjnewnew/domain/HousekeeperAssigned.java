package hotelcmshsjnewnew.domain;

import hotelcmshsjnewnew.domain.*;
import hotelcmshsjnewnew.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class HousekeeperAssigned extends AbstractEvent {

    private Long housekeepingId;
    private Long accomodationId;
    private Long housekeeperId;
    private Long roomId;

    public HousekeeperAssigned(AssignHouseKeeper aggregate) {
        super(aggregate);
    }

    public HousekeeperAssigned() {
        super();
    }
}
//>>> DDD / Domain Event
