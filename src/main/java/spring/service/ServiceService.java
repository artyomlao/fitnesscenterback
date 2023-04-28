package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.entity.ServiceEntity;
import spring.exception.EntityNotFoundException;
import spring.repository.ServiceRepository;

import java.util.List;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceService(final ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ServiceEntity> list() {
        return serviceRepository.findAll();
    }

    public ServiceEntity getById(final Long id) throws EntityNotFoundException {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ServiceEntity.class, "No service with such id: " + id));
    }

    public ServiceEntity insertService(final ServiceEntity serviceEntity) {
        return serviceRepository.save(serviceEntity);
    }

    public ServiceEntity updateService(final ServiceEntity entityToUpdate)
            throws EntityNotFoundException {

        final ServiceEntity oldEntity = serviceRepository.findById(entityToUpdate.getId())
                .orElseThrow(() -> new EntityNotFoundException(ServiceEntity.class, "No such entity to update"));

        return serviceRepository.save(oldEntity
                .setName(entityToUpdate.getName())
                .setPrice(entityToUpdate.getPrice())
                .setEquipmentEntity(entityToUpdate.getEquipmentEntity())
                .setCategoryEntity(entityToUpdate.getCategoryEntity()));
    }

    public void deleteService(final Long id) {
        serviceRepository.deleteById(id);
    }
}