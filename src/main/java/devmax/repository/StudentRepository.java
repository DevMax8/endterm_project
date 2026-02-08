package devmax.repository;

import devmax.dto.StudentCreateRequest;
import devmax.model.Student;
import devmax.utils.RowMappers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {
    private final JdbcTemplate jdbc;

    public StudentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Student> findAll() {
        String sql = "SELECT id, full_name, email, age FROM students ORDER BY id";
        return jdbc.query(sql, RowMappers.studentRowMapper());
    }

    public Optional<Student> findById(Long id) {
        try {
            String sql = "SELECT id, full_name, email, age FROM students WHERE id = ?";
            return Optional.ofNullable(jdbc.queryForObject(sql, RowMappers.studentRowMapper(), id));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    public Long create(StudentCreateRequest req) {
        String sql = "INSERT INTO students(full_name, email, age) VALUES (?, ?, ?) RETURNING id";
        return jdbc.queryForObject(sql, Long.class, req.getFullName(), req.getEmail(), req.getAge());
    }
}
