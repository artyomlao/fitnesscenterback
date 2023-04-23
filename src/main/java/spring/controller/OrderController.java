package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.entity.OrderEntity;
import spring.exception.EntityNotFoundException;
import spring.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrderEntity>> list() {
        return ResponseEntity.ok(orderService.list());
    }

    @GetMapping
    public ResponseEntity<OrderEntity> byId(final @RequestParam Long id)
            throws EntityNotFoundException {

        return ResponseEntity.ok(orderService.getById(id));
    }

    @PostMapping
    public ResponseEntity<OrderEntity> add(final @RequestBody OrderEntity orderEntity) {
        return ResponseEntity.ok(orderService.insertOrder(orderEntity));
    }

    @PutMapping
    public ResponseEntity<OrderEntity> update(final @RequestBody OrderEntity orderEntity)
            throws EntityNotFoundException {

        return ResponseEntity.ok(orderService.updateOrder(orderEntity));
    }

    @DeleteMapping
    public ResponseEntity<OrderEntity> delete(final @RequestParam Long id) throws EntityNotFoundException {
        final OrderEntity entity = orderService.getById(id);
        orderService.deleteOrder(id);
        return ResponseEntity.ok(entity);
    }
}
