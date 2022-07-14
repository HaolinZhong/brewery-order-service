package hz.spring.breweryorderservice.web.mappers;

import hz.spring.breweryorderservice.domain.BeerOrder;
import hz.spring.common.model.BeerOrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {
    @Mapping(target = "customerId", source = "customer.id")
    BeerOrderDTO BeerOrderToDTO(BeerOrder beerOrder);
    BeerOrder DTOToBeerOrder(BeerOrderDTO beerOrderDTO);
}
