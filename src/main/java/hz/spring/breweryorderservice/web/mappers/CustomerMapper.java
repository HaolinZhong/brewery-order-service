package hz.spring.breweryorderservice.web.mappers;

import hz.spring.breweryorderservice.domain.Customer;
import hz.spring.common.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface CustomerMapper {
    CustomerDTO CustomerToDTO(Customer customer);
    Customer DTOToCustomer(CustomerDTO customerDTO);
}
