package br.com.skipthedishes.controller;

import br.com.skipthedishes.entity.*;
import br.com.skipthedishes.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/teste")
public class TesteResource {

    @Autowired
    private CousineRepository cousineRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> insertData() {
        CousineEntity cousine1 = cousineRepository.save(new CousineEntity("Chinese"));
        CousineEntity cousine2 = cousineRepository.save(new CousineEntity("Pizza"));
        CousineEntity cousine3 = cousineRepository.save(new CousineEntity("Vietnamese"));
        CousineEntity cousine4 = cousineRepository.save(new CousineEntity("Sushi"));

        StoreEntity store1 = storeRepository.save(new StoreEntity("Hai Shang", "2991 Pembina Hwy, Winnipeg, Manitoba R3T 2H5, Canada", cousine1));
        StoreEntity store2 = storeRepository.save(new StoreEntity("Ye's", "616 St James St, Winnipeg, Manitoba R3G 3J5, Canada", cousine1));
        StoreEntity store3 = storeRepository.save(new StoreEntity("Good Earth Chop Suey House", "1849 Portage Ave, Winnipeg, Manitoba R3J 0G8, Canada", cousine1));
        StoreEntity store4 = storeRepository.save(new StoreEntity("Za Pizza Bistro", "E-1220 St Mary's Rd, Winnipeg, Manitoba R2M 3V6, Canada", cousine2));

        ProductEntity product1 = productRepository.save(new ProductEntity("Shrimp Tempura", "Fresh shrimp battered and deep fried until golden brown", 10.95, store1));
        ProductEntity product2 = productRepository.save(new ProductEntity("Shrimp with Snow Peas and Cashew", "A delicious combination of fresh shrimp, snow peas, and cashew", 12.5, store1));
        ProductEntity product3 = productRepository.save(new ProductEntity("Special Deep-Fried Fish", "Tilapia fish deep fried until flaky and tender", 12.95, store1));

        CustomerEntity customerEntity1 = customerRepository.save(new CustomerEntity("user@teste.com", "Test User", "Street test", new Date(), "123456"));

        OrderEntity orderEntity1 = orderRepository.save(new OrderEntity(new Date(), customerEntity1, "Deliver St", "Contact", store1, new ArrayList<>(), 10.0, "waiting", new Date()));

        OrderItemEntity orderItemEntity = orderItemRepository.save(new OrderItemEntity(orderEntity1, product1, 10.0, 1.0, 10.0));

        return ResponseEntity.ok("{\"status\": \"OK\"}");
    }
}
