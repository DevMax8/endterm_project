package devmax.service;

import devmax.dto.EnrollmentCreateRequest;
import devmax.model.Enrollment;
import devmax.repository.CourseRepository;
import devmax.repository.EnrollmentRepository;
import devmax.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository repo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public EnrollmentService(EnrollmentRepository repo, StudentRepository studentRepo, CourseRepository courseRepo) {
        this.repo = repo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    public List<Enrollment> all() { return repo.findAll(); }

    public Enrollment one(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Enrollment not found: " + id));
    }

    public Enrollment create(EnrollmentCreateRequest req) {
        studentRepo.findById(req.getStudentId()).orElseThrow(() -> new IllegalArgumentException("Student not found: " + req.getStudentId()));
        courseRepo.findById(req.getCourseId()).orElseThrow(() -> new IllegalArgumentException("Course not found: " + req.getCourseId()));

        Long id = repo.create(req);
        return one(id);
    }

    public Enrollment update(Long id, EnrollmentCreateRequest req) {
        studentRepo.findById(req.getStudentId()).orElseThrow(() -> new IllegalArgumentException("Student not found: " + req.getStudentId()));
        courseRepo.findById(req.getCourseId()).orElseThrow(() -> new IllegalArgumentException("Course not found: " + req.getCourseId()));

        boolean ok = repo.update(id, req);
        if (!ok) throw new IllegalArgumentException("Enrollment not found: " + id);
        return one(id);
    }

    public void delete(Long id) {
        boolean ok = repo.delete(id);
        if (!ok) throw new IllegalArgumentException("Enrollment not found: " + id);
    }
}
