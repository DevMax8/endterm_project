package devmax.repository;

import devmax.dto.StudentCreateRequest;
import devmax.dto.StudentUpdateRequest;
import devmax.model.Student;
import devmax.utils.RowMappers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
        String sql = "SELECT id, full_name, email, age FROM students WHERE id = ?";
        List<Student> list = jdbc.query(sql, RowMappers.studentRowMapper(), id);
        return list.stream().findFirst();
    }

    public Long create(StudentCreateRequest req) {
        String sql = "INSERT INTO students(full_name, email, age) VALUES (?, ?, ?) RETURNING id";

        // ең қарапайым әрі сенімді вариант: queryForObject
        return jdbc.queryForObject(sql, Long.class, req.getFullName(), req.getEmail(), req.getAge());
    }

    public boolean update(Long id, StudentUpdateRequest req) {
        String sql = "UPDATE students SET full_name = ?, email = ?, age = ? WHERE id = ?";
        int updated = jdbc.update(sql, req.getFullName(), req.getEmail(), req.getAge(), id);
        return updated > 0;
    }

    // ✅ МІНЕ ОСЫ deleteById() жетіспей тұр сенде
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM students WHERE id = ?";
        int deleted = jdbc.update(sql, id);
        return deleted > 0;
    }

    public boolean existsById(Long id) {
        String sql = "SELECT EXISTS(SELECT 1 FROM students WHERE id = ?)";
        Boolean exists = jdbc.queryForObject(sql, Boolean.class, id);
        return exists != null && exists;
    }
}
