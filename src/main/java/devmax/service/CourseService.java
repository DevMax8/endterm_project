package devmax.service;

import devmax.dto.CourseCreateRequest;
import devmax.model.Course;
import devmax.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public List<Course> all() { return repo.findAll(); }

    public Course one(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Course not found: " + id));
    }

    public Long create(CourseCreateRequest req) { return repo.create(req); }
}
