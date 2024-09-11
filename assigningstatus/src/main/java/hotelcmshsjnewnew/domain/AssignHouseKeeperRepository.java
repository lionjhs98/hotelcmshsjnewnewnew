package hotelcmshsjnewnew.domain;

import hotelcmshsjnewnew.domain.*;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "assignHouseKeepers",
    path = "assignHouseKeepers"
)
public interface AssignHouseKeeperRepository
    extends PagingAndSortingRepository<AssignHouseKeeper, Long> {}
