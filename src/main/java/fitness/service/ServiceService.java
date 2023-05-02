package fitness.service;

import fitness.entity.CategoryEntity;
import fitness.entity.ServiceEntity;
import fitness.exception.EntityNotFoundException;
import fitness.model.ServiceDTO;
import fitness.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final CategoryService categoryService;

    @Autowired
    public ServiceService(final ServiceRepository serviceRepository, final CategoryService categoryService) {
        this.serviceRepository = serviceRepository;
        this.categoryService = categoryService;
    }

    public List<ServiceEntity> list() {
        return serviceRepository.findAll();
    }

    public ServiceEntity getById(final Long id) throws EntityNotFoundException {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ServiceEntity.class, "No service with such id: " + id));
    }

    public ServiceEntity insertService(final ServiceDTO serviceDTO) throws EntityNotFoundException {
        return serviceRepository.save(new ServiceEntity()
                .setName(serviceDTO.getName())
                .setPrice(serviceDTO.getPrice())
                .setCategoryEntity(categoryService.getById(serviceDTO.getCategoryId())));
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
