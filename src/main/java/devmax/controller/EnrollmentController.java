package devmax.controller;

import devmax.dto.EnrollmentCreateRequest;
import devmax.dto.EnrollmentUpdateRequest;
import devmax.model.Enrollment;
import devmax.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // GET /enrollments
    @GetMapping
    public List<Enrollment> getAll() {
        return enrollmentService.getAll();
    }

    // GET /enrollments/{id}
    @GetMapping("/{id}")
    public Enrollment getById(@PathVariable Long id) {
        return enrollmentService.getById(id);
    }

    // POST /enrollments
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody EnrollmentCreateRequest req) {
        return enrollmentService.create(req);
    }

    // PUT /enrollments/{id}
    @PutMapping("/{id}")
    public Enrollment update(@PathVariable Long id, @RequestBody EnrollmentUpdateRequest req) {
        return enrollmentService.update(id, req);
    }

    // DELETE /enrollments/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        enrollmentService.delete(id);
    }
}
