package hotelcmshsjnewnew.domain;

import hotelcmshsjnewnew.domain.*;
import hotelcmshsjnewnew.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class CheckoutInfoRegistered extends AbstractEvent {

    private Long accomodationId;
    private DateTime checkOutTime;
    private Long roomId;

    public CheckoutInfoRegistered(Accomodation aggregate) {
        super(aggregate);
    }

    public CheckoutInfoRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
