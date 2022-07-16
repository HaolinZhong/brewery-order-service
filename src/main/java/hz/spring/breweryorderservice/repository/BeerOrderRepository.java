package hz.spring.breweryorderservice.repository;

import hz.spring.breweryorderservice.domain.BeerOrder;
import hz.spring.breweryorderservice.domain.BeerOrderStatusEnum;
import hz.spring.breweryorderservice.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {

    Page<BeerOrder> findAllByCustomer(Customer customer, Pageable pageable);

    List<BeerOrder> findAllByOrderStatus(BeerOrderStatusEnum beerOrderStatusEnum);

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    BeerOrder findOneById(UUID id);
}
