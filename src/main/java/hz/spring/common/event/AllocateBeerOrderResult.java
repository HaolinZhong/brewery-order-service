package hz.spring.common.event;

import hz.spring.common.model.BeerOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocateBeerOrderResult implements Serializable {

    private final long serialVersionUID = -819767001583940248L;

    private BeerOrderDTO beerOrderDTO;
    private Boolean allocationError = false;
    private Boolean pendingInventory = false;
    
}
