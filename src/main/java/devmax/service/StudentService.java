package devmax.service;

import devmax.dto.StudentCreateRequest;
import devmax.model.Student;
import devmax.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public List<Student> all() { return repo.findAll(); }

    public Student one(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found: " + id));
    }

    public Long create(StudentCreateRequest req) { return repo.create(req); }
}
