package fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fitness.entity.OrderEntity;
import fitness.exception.EntityNotFoundException;
import fitness.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin
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
    public ResponseEntity<List<OrderEntity>> byId(final @RequestParam Long userId) {
        return ResponseEntity.ok(orderService.getByUserId(userId));
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
