package hz.spring.common.event;

import hz.spring.common.model.BeerOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocateBeerOrderResult {

    private BeerOrderDTO beerOrderDTO;
    private Boolean allocationError = false;
    private Boolean pendingInventory = false;
    
}
