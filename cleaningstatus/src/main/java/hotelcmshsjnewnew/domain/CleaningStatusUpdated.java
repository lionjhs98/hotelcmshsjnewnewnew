package hotelcmshsjnewnew.domain;

import hotelcmshsjnewnew.domain.*;
import hotelcmshsjnewnew.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class CleaningStatusUpdated extends AbstractEvent {

    private Long housekeepingId;
    private Long accomodationId;
    private Long housekeeperId;
    private Boolean cleaned;
    private Long roomId;

    public CleaningStatusUpdated(CleaningStatus aggregate) {
        super(aggregate);
    }

    public CleaningStatusUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
