package hotelcmshsjnewnew.external;

import java.util.Date;
import lombok.Data;

@Data
public class AssignHouseKeeper {

    private Long housekeepingId;
    private Long accomodationId;
    private Long housekeeperId;
    private Long roomId;
}
