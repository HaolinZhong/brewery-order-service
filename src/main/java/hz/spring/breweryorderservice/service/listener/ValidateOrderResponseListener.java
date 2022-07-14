package hz.spring.breweryorderservice.service.listener;

import hz.spring.breweryorderservice.config.JmsConfig;
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

        beerOrderManager.processValidationResult(result.getOrderId(), result.getIsValid());
    }
}
