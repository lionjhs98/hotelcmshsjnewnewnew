package hotelcmshsjnewnew.infra;

import hotelcmshsjnewnew.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class AssignHouseKeeperHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<AssignHouseKeeper>> {

    @Override
    public EntityModel<AssignHouseKeeper> process(
        EntityModel<AssignHouseKeeper> model
    ) {
        return model;
    }
}
