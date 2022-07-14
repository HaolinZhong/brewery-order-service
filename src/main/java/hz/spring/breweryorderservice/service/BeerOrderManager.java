package hz.spring.breweryorderservice.service;

import hz.spring.breweryorderservice.domain.BeerOrder;
import hz.spring.common.model.BeerOrderDTO;

import java.util.UUID;

public interface BeerOrderManager {

    BeerOrder newBeerOrder(BeerOrder beerOrder);

    void processValidationResult(UUID beerOrderId, Boolean isValid);

    void beerOrderAllocationPassed(BeerOrderDTO beerOrderDTO);

    void beerOrderAllocationPendingInventory(BeerOrderDTO beerOrderDTO);

    void beerOrderAllocationFailed(BeerOrderDTO beerOrderDTO);

}
