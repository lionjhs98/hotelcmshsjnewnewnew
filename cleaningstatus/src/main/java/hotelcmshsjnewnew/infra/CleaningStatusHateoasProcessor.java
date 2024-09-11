package hotelcmshsjnewnew.infra;

import hotelcmshsjnewnew.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class CleaningStatusHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<CleaningStatus>> {

    @Override
    public EntityModel<CleaningStatus> process(
        EntityModel<CleaningStatus> model
    ) {
        return model;
    }
}
