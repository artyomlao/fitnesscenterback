package fitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fitness.entity.OrderEntity;
import fitness.entity.UserEntity;
import fitness.exception.EntityNotFoundException;
import fitness.exception.UserNotFoundException;
import fitness.repository.OrderRepository;
import fitness.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(final OrderRepository orderRepository, final UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<OrderEntity> list() {
        return orderRepository.findAll();
    }

    public OrderEntity getById(final Long id) throws EntityNotFoundException {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(OrderEntity.class, "No order with such id: " + id));
    }

    public List<OrderEntity> getByUserId(final Long userId) {
        final UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Such user doesn't exist"));

        return orderRepository.findAllByUserEntity(userEntity);
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
