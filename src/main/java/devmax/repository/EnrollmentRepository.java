package devmax.repository;

import devmax.dto.EnrollmentCreateRequest;
import devmax.dto.EnrollmentUpdateRequest;
import devmax.model.Enrollment;
import devmax.utils.RowMappers;
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
        String sql = "SELECT id, student_id, course_id, enrolled_at FROM enrollments WHERE id = ?";
        List<Enrollment> list = jdbc.query(sql, RowMappers.enrollmentRowMapper(), id);
        return list.stream().findFirst();
    }

    public Long create(EnrollmentCreateRequest req) {
        String sql = """
                INSERT INTO enrollments(student_id, course_id)
                VALUES (?, ?)
                RETURNING id
                """;
        return jdbc.queryForObject(sql, Long.class, req.getStudentId(), req.getCourseId());
    }

    public boolean update(Long id, EnrollmentUpdateRequest req) {
        String sql = """
                UPDATE enrollments
                SET student_id = ?, course_id = ?
                WHERE id = ?
                """;
        int updated = jdbc.update(sql, req.getStudentId(), req.getCourseId(), id);
        return updated > 0;
    }

    public boolean deleteById(Long id) {
        String sql = "DELETE FROM enrollments WHERE id = ?";
        int deleted = jdbc.update(sql, id);
        return deleted > 0;
    }

    public boolean existsById(Long id) {
        String sql = "SELECT EXISTS(SELECT 1 FROM enrollments WHERE id = ?)";
        Boolean exists = jdbc.queryForObject(sql, Boolean.class, id);
        return exists != null && exists;
    }
}
