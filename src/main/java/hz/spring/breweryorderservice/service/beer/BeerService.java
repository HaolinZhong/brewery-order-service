package hz.spring.breweryorderservice.service.beer;

import hz.spring.breweryorderservice.web.model.BeerDTO;

import java.util.Optional;
import java.util.UUID;

public interface BeerService {
    Optional<BeerDTO> getBeerById(UUID uuid);
    Optional<BeerDTO> getBeerByUpc(String upc);
}
