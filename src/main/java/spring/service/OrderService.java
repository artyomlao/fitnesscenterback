package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.entity.OrderEntity;
import spring.exception.EntityNotFoundException;
import spring.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderEntity> list() {
        return orderRepository.findAll();
    }

    public OrderEntity getById(final Long id) throws EntityNotFoundException {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(OrderEntity.class, "No order with such id: " + id));
    }

    public OrderEntity insertOrder(final OrderEntity orderEntity) {
        return orderRepository.save(orderEntity.setInsertTime(LocalDateTime.now()));
    }

    public OrderEntity updateOrder(final OrderEntity entityToUpdate)
            throws EntityNotFoundException {

        final OrderEntity oldEntity = orderRepository.findById(entityToUpdate.getId())
                .orElseThrow(() -> new EntityNotFoundException(OrderEntity.class, "No such entity to update"));

        return orderRepository.save(oldEntity
                .setServiceEntity(entityToUpdate.getServiceEntity())
                .setUserEntity(entityToUpdate.getUserEntity())
                .setInsertTime(entityToUpdate.getInsertTime()));
    }

    public void deleteOrder(final Long id) {
        orderRepository.deleteById(id);
    }
}
