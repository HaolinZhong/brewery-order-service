package hz.spring.breweryorderservice.statemachine.actions;

import hz.spring.breweryorderservice.config.JmsConfig;
import hz.spring.breweryorderservice.domain.BeerOrder;
import hz.spring.breweryorderservice.domain.BeerOrderEventEnum;
import hz.spring.breweryorderservice.domain.BeerOrderStatusEnum;
import hz.spring.breweryorderservice.repository.BeerOrderRepository;
import hz.spring.breweryorderservice.service.BeerOrderManagerImpl;
import hz.spring.breweryorderservice.web.mappers.BeerOrderMapper;
import hz.spring.common.event.AllocateBeerOrderRequest;
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
public class AllocateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> stateContext) {
        String beerOrderId = (String) stateContext.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);

        BeerOrder beerOrder = beerOrderRepository.findOneById(UUID.fromString(beerOrderId));

        AllocateBeerOrderRequest request = AllocateBeerOrderRequest
                .builder()
                .beerOrderDTO(beerOrderMapper.BeerOrderToDTO(beerOrder))
                .build();

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_QUEUE, request);
    }

}
