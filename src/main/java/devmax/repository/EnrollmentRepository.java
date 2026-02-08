package devmax.repository;

import devmax.dto.EnrollmentCreateRequest;
import devmax.model.Enrollment;
import devmax.utils.RowMappers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EnrollmentRepository {
    private final JdbcTemplate jdbc;

    public EnrollmentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Enrollment> findAll() {
        String sql = "SELECT id, student_id, course_id, enrolled_at FROM enrollments ORDER BY id";
        return jdbc.query(sql, RowMappers.enrollmentRowMapper());
    }

    public Optional<Enrollment> findById(Long id) {
        try {
            String sql = "SELECT id, student_id, course_id, enrolled_at FROM enrollments WHERE id = ?";
            return Optional.ofNullable(jdbc.queryForObject(sql, RowMappers.enrollmentRowMapper(), id));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    public Long create(EnrollmentCreateRequest req) {
        String sql = "INSERT INTO enrollments(student_id, course_id) VALUES (?, ?) RETURNING id";
        return jdbc.queryForObject(sql, Long.class, req.getStudentId(), req.getCourseId());
    }

    public boolean update(Long id, EnrollmentCreateRequest req) {
        String sql = "UPDATE enrollments SET student_id = ?, course_id = ? WHERE id = ?";
        return jdbc.update(sql, req.getStudentId(), req.getCourseId(), id) > 0;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM enrollments WHERE id = ?";
        return jdbc.update(sql, id) > 0;
    }
}
