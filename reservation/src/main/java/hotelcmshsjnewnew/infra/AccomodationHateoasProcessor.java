package hotelcmshsjnewnew.infra;

import hotelcmshsjnewnew.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class AccomodationHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Accomodation>> {

    @Override
    public EntityModel<Accomodation> process(EntityModel<Accomodation> model) {
        return model;
    }
}
