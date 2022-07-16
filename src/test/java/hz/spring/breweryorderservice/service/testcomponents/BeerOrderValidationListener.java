package hz.spring.breweryorderservice.service.testcomponents;

import hz.spring.breweryorderservice.config.JmsConfig;
import hz.spring.common.event.ValidateBeerOrderRequest;
import hz.spring.common.event.ValidateBeerOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(Message msg) {

        ValidateBeerOrderRequest request = (ValidateBeerOrderRequest) msg.getPayload();

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
                ValidateBeerOrderResult.builder()
                .isValid(true)
                .orderId(request.getBeerOrderDTO().getId())
                .build());

    }

}
