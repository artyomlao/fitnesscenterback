package fitness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fitness.entity.EquipmentEntity;
import fitness.exception.EntityNotFoundException;
import fitness.repository.EquipmentRepository;

import java.util.List;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    @Autowired
    public EquipmentService(final EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public List<EquipmentEntity> list() {
        return equipmentRepository.findAll();
    }

    public EquipmentEntity getById(final Long id) throws EntityNotFoundException {
        return equipmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EquipmentEntity.class, "No equipment with such id: " + id));
    }

    public EquipmentEntity insertEquipment(final EquipmentEntity equipmentEntity) {
        return equipmentRepository.save(equipmentEntity);
    }

    public EquipmentEntity updateEquipment(final EquipmentEntity entityToUpdate)
            throws EntityNotFoundException {

        final EquipmentEntity oldEntity = equipmentRepository.findById(entityToUpdate.getId())
                .orElseThrow(() -> new EntityNotFoundException(EquipmentEntity.class, "No such entity to update"));

        return equipmentRepository.save(oldEntity
                .setName(entityToUpdate.getName())
                .setDescription(entityToUpdate.getDescription()));
    }

    public void deleteEquipment(final Long id) {
        equipmentRepository.deleteById(id);
    }
}
