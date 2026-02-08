package devmax.controller;

import devmax.dto.StudentCreateRequest;
import devmax.model.Student;
import devmax.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> all() { return service.all(); }

    @GetMapping("/{id}")
    public Student one(@PathVariable Long id) { return service.one(id); }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody StudentCreateRequest req) {
        Long id = service.create(req);
        return ResponseEntity.created(URI.create("/students/" + id)).body(service.one(id));
    }
}
