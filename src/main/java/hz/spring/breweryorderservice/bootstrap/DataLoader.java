package hz.spring.breweryorderservice.bootstrap;

import hz.spring.breweryorderservice.domain.Customer;
import hz.spring.breweryorderservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    public static final String TASTING_ROOM = "Tasting Room";
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    public static final UUID BEER_1_UUID = UUID.fromString("095c83ee-c1bf-4933-8389-21bb6cedcd3f");
    public static final UUID BEER_2_UUID = UUID.fromString("259a01d9-d16e-403a-98a3-00df8983f731");
    public static final UUID BEER_3_UUID = UUID.fromString("9ad82b04-9660-4683-ab21-e134cd400565");

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCustomerData();
    }

    private void loadCustomerData() {
        if (customerRepository.findAllByCustomerNameLike(DataLoader.TASTING_ROOM).size() == 0) {
            Customer savedCustomer = customerRepository.save(Customer.builder()
                    .customerName(TASTING_ROOM)
                    .apiKey(UUID.randomUUID())
                    .build());

            log.debug("Tasting room customerId: " + savedCustomer.getId().toString());
        }
    }
}
