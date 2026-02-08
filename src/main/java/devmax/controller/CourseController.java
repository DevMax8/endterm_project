package devmax.controller;

import devmax.dto.CourseCreateRequest;
import devmax.model.Course;
import devmax.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Course> all() { return service.all(); }

    @GetMapping("/{id}")
    public Course one(@PathVariable Long id) { return service.one(id); }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CourseCreateRequest req) {
        Long id = service.create(req);
        return ResponseEntity.created(URI.create("/courses/" + id)).body(service.one(id));
    }
}
