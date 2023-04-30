package fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fitness.entity.EquipmentEntity;
import fitness.exception.EntityNotFoundException;
import fitness.service.EquipmentService;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@CrossOrigin
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(final EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<EquipmentEntity>> list() {
        return ResponseEntity.ok(equipmentService.list());
    }

    @GetMapping
    public ResponseEntity<EquipmentEntity> byId(final @RequestParam Long id)
            throws EntityNotFoundException {

        return ResponseEntity.ok(equipmentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<EquipmentEntity> add(final @RequestBody EquipmentEntity equipmentEntity) {
        return ResponseEntity.ok(equipmentService.insertEquipment(equipmentEntity));
    }

    @PutMapping
    public ResponseEntity<EquipmentEntity> update(final @RequestBody EquipmentEntity equipmentEntity)
            throws EntityNotFoundException {

        return ResponseEntity.ok(equipmentService.updateEquipment(equipmentEntity));
    }

    @DeleteMapping
    public ResponseEntity<EquipmentEntity> delete(final @RequestParam Long id) throws EntityNotFoundException {
        final EquipmentEntity entity = equipmentService.getById(id);
        equipmentService.deleteEquipment(id);
        return ResponseEntity.ok(entity);
    }
}
