package devmax.service;

import devmax.dto.StudentCreateRequest;
import devmax.dto.StudentUpdateRequest;
import devmax.exception.NotFoundException;
import devmax.model.Student;
import devmax.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found: id=" + id));
    }

    public Long create(StudentCreateRequest req) {
        return studentRepository.create(req);
    }

    public Student update(Long id, StudentUpdateRequest req) {
        if (!studentRepository.existsById(id)) {
            throw new NotFoundException("Student not found: id=" + id);
        }
        studentRepository.update(id, req);
        return getById(id);
    }

    public void delete(Long id) {
        boolean ok = studentRepository.deleteById(id);
        if (!ok) {
            throw new NotFoundException("Student not found: id=" + id);
        }
    }
}
