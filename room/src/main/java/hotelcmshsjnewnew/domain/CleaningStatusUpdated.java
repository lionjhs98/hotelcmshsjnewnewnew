package hotelcmshsjnewnew.domain;

import hotelcmshsjnewnew.domain.*;
import hotelcmshsjnewnew.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class CleaningStatusUpdated extends AbstractEvent {

    private Long housekeepingId;
    private Long accomodationId;
    private Long housekeeperId;
    private Boolean cleaned;
    private Long roomId;
}
