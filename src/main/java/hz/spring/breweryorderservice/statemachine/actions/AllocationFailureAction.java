package hz.spring.breweryorderservice.statemachine.actions;

import hz.spring.breweryorderservice.domain.BeerOrderEventEnum;
import hz.spring.breweryorderservice.domain.BeerOrderStatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AllocationFailureAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {
    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> stateContext) {

    }
}
