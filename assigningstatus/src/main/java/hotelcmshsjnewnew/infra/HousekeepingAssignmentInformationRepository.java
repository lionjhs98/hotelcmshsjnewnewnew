package hotelcmshsjnewnew.infra;

import hotelcmshsjnewnew.domain.*;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "housekeepingAssignmentInformations",
    path = "housekeepingAssignmentInformations"
)
public interface HousekeepingAssignmentInformationRepository
    extends
        PagingAndSortingRepository<HousekeepingAssignmentInformation, Long> {}
