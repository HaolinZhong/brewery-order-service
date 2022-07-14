package hz.spring.breweryorderservice.statemachine.actions;

import hz.spring.breweryorderservice.config.JmsConfig;
import hz.spring.breweryorderservice.domain.BeerOrder;
import hz.spring.breweryorderservice.domain.BeerOrderEventEnum;
import hz.spring.breweryorderservice.domain.BeerOrderStatusEnum;
import hz.spring.breweryorderservice.repository.BeerOrderRepository;
import hz.spring.breweryorderservice.service.BeerOrderManagerImpl;
import hz.spring.breweryorderservice.web.mappers.BeerOrderMapper;
import hz.spring.common.event.ValidateBeerOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;
    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> stateContext) {
        String beerOrderId = (String) stateContext.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        BeerOrder beerOrder = beerOrderRepository.findOneById(UUID.fromString(beerOrderId));
        ValidateBeerOrderRequest request = ValidateBeerOrderRequest.builder()
                .beerOrderDTO(beerOrderMapper.BeerOrderToDTO(beerOrder))
                .build();

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_QUEUE, request);

        log.debug("Send validation request to queue for order id " + beerOrderId);
    }
}
