package hotelcmshsjnewnew.external;

import java.util.Date;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "assigningstatus", url = "${api.url.assigningstatus}")
public interface AssignHouseKeeperService {
    @RequestMapping(method = RequestMethod.POST, path = "/assignHouseKeepers")
    public void assignHousekeeper(
        @RequestBody AssignHouseKeeper assignHouseKeeper
    );
}
