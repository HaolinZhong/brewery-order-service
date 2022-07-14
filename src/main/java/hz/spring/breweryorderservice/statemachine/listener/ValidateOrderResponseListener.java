package hz.spring.breweryorderservice.statemachine.listener;

import hz.spring.breweryorderservice.config.JmsConfig;
import hz.spring.breweryorderservice.domain.BeerOrder;
import hz.spring.breweryorderservice.domain.BeerOrderEventEnum;
import hz.spring.breweryorderservice.repository.BeerOrderRepository;
import hz.spring.breweryorderservice.service.BeerOrderManager;
import hz.spring.common.event.ValidateBeerOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ValidateOrderResponseListener {

    private final BeerOrderManager beerOrderManager;
    private final BeerOrderRepository beerOrderRepository;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE)
    public void listen(ValidateBeerOrderResult result) {

        log.debug("Validation result received for order: " + result.getOrderId());

        BeerOrder beerOrder = beerOrderRepository.getReferenceById(result.getOrderId());

        if (result.getIsValid()) {
            beerOrderManager.sendBeerOrderEvent(beerOrder, BeerOrderEventEnum.VALIDATION_PASSED);
        } else {
            beerOrderManager.sendBeerOrderEvent(beerOrder, BeerOrderEventEnum.VALIDATION_FAILED);
        }
    }
}
