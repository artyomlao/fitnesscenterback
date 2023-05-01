package fitness.service;

import fitness.entity.OrderEntity;
import fitness.entity.ServiceEntity;
import fitness.entity.UserEntity;
import fitness.exception.EntityNotFoundException;
import fitness.exception.UserNotFoundException;
import fitness.model.OrderDTO;
import fitness.repository.OrderRepository;
import fitness.repository.ServiceRepository;
import fitness.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;

    @Autowired
    public OrderService(
            final OrderRepository orderRepository,
            final UserRepository userRepository,
            final ServiceRepository serviceRepository) {

        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
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

    public OrderEntity insertOrder(final OrderDTO orderDTO) throws EntityNotFoundException {
        validateDto(orderDTO);
        final ServiceEntity serviceEntity = serviceRepository.findById(orderDTO.getServiceId())
                        .orElseThrow(() -> new EntityNotFoundException(ServiceEntity.class, "Service doesn't exist"));

        final UserEntity userEntity = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User doesn't exist"));

        return orderRepository.save(new OrderEntity()
                .setServiceEntity(serviceEntity)
                .setUserEntity(userEntity)
                .setOrderTime(LocalDateTime.now()));
    }

    public OrderEntity updateOrder(final OrderEntity entityToUpdate)
            throws EntityNotFoundException {

        final OrderEntity oldEntity = orderRepository.findById(entityToUpdate.getId())
                .orElseThrow(() -> new EntityNotFoundException(OrderEntity.class, "No such entity to update"));

        return orderRepository.save(oldEntity
                .setServiceEntity(entityToUpdate.getServiceEntity())
                .setUserEntity(entityToUpdate.getUserEntity())
                .setOrderTime(entityToUpdate.getOrderTime()));
    }

    public void deleteOrder(final Long id) {
        orderRepository.deleteById(id);
    }

    private void validateDto(final OrderDTO orderDTO) throws EntityNotFoundException {
        if (orderDTO.getUserId() == null) {
            throw new EntityNotFoundException(UserEntity.class, "User can't be found, his id is null");
        }

        if (orderDTO.getServiceId() == null) {
            throw new EntityNotFoundException(ServiceEntity.class, "Service can't be found, his id is null");
        }
    }
}
