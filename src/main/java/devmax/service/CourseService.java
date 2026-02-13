package devmax.service;

import devmax.dto.CourseCreateRequest;
import devmax.dto.CourseUpdateRequest;
import devmax.exception.NotFoundException;
import devmax.model.Course;
import devmax.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course getById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found: id=" + id));
    }

    public Long create(CourseCreateRequest req) {
        return courseRepository.create(req);
    }

    public Course update(Long id, CourseUpdateRequest req) {
        if (!courseRepository.existsById(id)) {
            throw new NotFoundException("Course not found: id=" + id);
        }
        courseRepository.update(id, req);
        return getById(id);
    }

    public void delete(Long id) {
        boolean ok = courseRepository.deleteById(id);
        if (!ok) throw new NotFoundException("Course not found: id=" + id);
    }
}
