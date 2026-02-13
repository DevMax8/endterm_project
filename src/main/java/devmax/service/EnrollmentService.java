package devmax.service;

import devmax.dto.EnrollmentCreateRequest;
import devmax.dto.EnrollmentUpdateRequest;
import devmax.exception.NotFoundException;
import devmax.model.Enrollment;
import devmax.repository.CourseRepository;
import devmax.repository.EnrollmentRepository;
import devmax.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Enrollment> getAll() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Enrollment not found: id=" + id));
    }

    public Long create(EnrollmentCreateRequest req) {
        if (!studentRepository.existsById(req.getStudentId())) {
            throw new NotFoundException("Student not found: id=" + req.getStudentId());
        }
        if (!courseRepository.existsById(req.getCourseId())) {
            throw new NotFoundException("Course not found: id=" + req.getCourseId());
        }
        return enrollmentRepository.create(req);
    }

    public Enrollment update(Long id, EnrollmentUpdateRequest req) {
        if (!enrollmentRepository.existsById(id)) {
            throw new NotFoundException("Enrollment not found: id=" + id);
        }
        if (!studentRepository.existsById(req.getStudentId())) {
            throw new NotFoundException("Student not found: id=" + req.getStudentId());
        }
        if (!courseRepository.existsById(req.getCourseId())) {
            throw new NotFoundException("Course not found: id=" + req.getCourseId());
        }

        enrollmentRepository.update(id, req);
        return getById(id);
    }

    public void delete(Long id) {
        boolean ok = enrollmentRepository.deleteById(id);
        if (!ok) throw new NotFoundException("Enrollment not found: id=" + id);
    }
}
