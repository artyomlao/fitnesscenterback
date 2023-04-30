package fitness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import fitness.entity.ServiceEntity;
import fitness.exception.EntityNotFoundException;
import fitness.service.ServiceService;

import java.util.List;

@RestController
@RequestMapping("/api/service")
@CrossOrigin(origins = "http://localhost:3000")
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
    public ResponseEntity<ServiceEntity> add(final @RequestBody ServiceEntity serviceEntity) {
        return ResponseEntity.ok(serviceService.insertService(serviceEntity));
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
