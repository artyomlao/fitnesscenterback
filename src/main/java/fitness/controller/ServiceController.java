package fitness.controller;

import fitness.entity.ServiceEntity;
import fitness.exception.EntityNotFoundException;
import fitness.model.ServiceDTO;
import fitness.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service")
@CrossOrigin
public class ServiceController {

    private final ServiceService serviceService;

    @Autowired
    public ServiceController(final ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ServiceEntity>> list() {
        return ResponseEntity.ok(serviceService.list());
    }

    @GetMapping
    public ResponseEntity<ServiceEntity> byId(final @RequestParam Long id)
            throws EntityNotFoundException {

        return ResponseEntity.ok(serviceService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ServiceEntity> add(final @RequestBody ServiceDTO serviceDTO)
            throws EntityNotFoundException {

        return ResponseEntity.ok(serviceService.insertService(serviceDTO));
    }

    @PutMapping
    public ResponseEntity<ServiceEntity> update(final @RequestBody ServiceEntity serviceEntity)
            throws EntityNotFoundException {

        return ResponseEntity.ok(serviceService.updateService(serviceEntity));
    }

    @DeleteMapping
    public ResponseEntity<ServiceEntity> delete(final @RequestParam Long id) throws EntityNotFoundException {
        final ServiceEntity entity = serviceService.getById(id);
        serviceService.deleteService(id);
        return ResponseEntity.ok(entity);
    }
}
