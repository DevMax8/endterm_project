package devmax.controller;

import devmax.dto.EnrollmentCreateRequest;
import devmax.model.Enrollment;
import devmax.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Enrollment> all() { return service.all(); }

    @GetMapping("/{id}")
    public Enrollment one(@PathVariable Long id) { return service.one(id); }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EnrollmentCreateRequest req) {
        Enrollment created = service.create(req);
        return ResponseEntity.created(URI.create("/enrollments/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public Enrollment update(@PathVariable Long id, @RequestBody EnrollmentCreateRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
