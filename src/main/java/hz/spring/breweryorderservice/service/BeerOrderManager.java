package hz.spring.breweryorderservice.service;

import hz.spring.breweryorderservice.domain.BeerOrder;

import java.util.UUID;

public interface BeerOrderManager {
    BeerOrder newBeerOrder(BeerOrder beerOrder);
    void processValidationResult(UUID beerOrderId, Boolean isValid);
}
