package br.com.skipthedishes.controller;

import br.com.skipthedishes.dto.OrderDTO;
import br.com.skipthedishes.dto.OrderItemDTO;
import br.com.skipthedishes.entity.OrderEntity;
import br.com.skipthedishes.entity.OrderItemEntity;
import br.com.skipthedishes.repository.*;
import br.com.skipthedishes.security.AuthKeyThreadLocal;
import br.com.skipthedishes.security.AuthRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/Order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderResource {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductRepository productRepository;

    @AuthRequired
    @RequestMapping(method = RequestMethod.GET, value = "/{orderId}")
    public ResponseEntity<OrderEntity> getOrder(@PathVariable Long orderId) {

        OrderEntity orderEntity = orderRepository.findOne(orderId);
        orderEntity.setOrderItems(orderItemRepository.findByOrderId(orderId));

        return ResponseEntity.ok(orderEntity);
    }

    @AuthRequired
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OrderEntity> create(@RequestBody OrderDTO orderDTO) {
        OrderEntity orderEntity = orderRepository.save(convertFrom(orderDTO));

        orderDTO.getOrderItems().stream().forEach(item -> {
            orderEntity.getOrderItems().add(
                    orderItemRepository.save(
                            convertFrom(orderEntity, item)
                    )
            );
        });

        return ResponseEntity.ok(orderEntity);
    }

    @AuthRequired
    @RequestMapping(method = RequestMethod.GET, value = "/customer")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders() {
        Long customerId = AuthKeyThreadLocal.getAuthKey();

        List<OrderEntity> customerOrders = orderRepository.findByCustomerId(customerId);
        if (customerOrders != null) {
            customerOrders.stream().forEach(o -> o.setOrderItems(orderItemRepository.findByOrderId(o.getId())));
        }

        return ResponseEntity.ok(customerOrders);
    }

    private OrderEntity convertFrom(OrderDTO orderDTO) {
        return new OrderEntity(orderDTO.getDate(),
                customerRepository.findOne(orderDTO.getCustomerId()),
                orderDTO.getDeliveryAddress(),
                orderDTO.getContact(),
                storeRepository.findOne(orderDTO.getStoreId()),
                new ArrayList<>(),
                orderDTO.getTotal(),
                orderDTO.getStatus(),
                orderDTO.getLastUpdate());
    }

    private OrderItemEntity convertFrom(OrderEntity orderEntity, OrderItemDTO orderItemDTO) {
        return new OrderItemEntity(orderEntity,
                productRepository.findOne(orderItemDTO.getProductId()),
                orderItemDTO.getPrice(),
                orderItemDTO.getQuantity(),
                orderItemDTO.getTotal());
    }
}
