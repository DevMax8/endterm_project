package devmax.repository;

import devmax.dto.CourseCreateRequest;
import devmax.dto.CourseUpdateRequest;
import devmax.model.Course;
import devmax.utils.RowMappers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {

    private final JdbcTemplate jdbc;

    public CourseRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Course> findAll() {
        String sql = "SELECT id, title, description FROM courses ORDER BY id";
        return jdbc.query(sql, RowMappers.courseRowMapper());
    }

    public Optional<Course> findById(Long id) {
        String sql = "SELECT id, title, description FROM courses WHERE id = ?";
        List<Course> list = jdbc.query(sql, RowMappers.courseRowMapper(), id);
        return list.stream().findFirst();
    }

    public Long create(CourseCreateRequest req) {
        String sql = "INSERT INTO courses(title, description) VALUES (?, ?) RETURNING id";
        return jdbc.queryForObject(sql, Long.class, req.getTitle(), req.getDescription());
    }

    public boolean update(Long id, CourseUpdateRequest req) {
        String sql = "UPDATE courses SET title = ?, description = ? WHERE id = ?";
        int updated = jdbc.update(sql, req.getTitle(), req.getDescription(), id);
        return updated > 0;
    }

    public boolean deleteById(Long id) {
        String sql = "DELETE FROM courses WHERE id = ?";
        int deleted = jdbc.update(sql, id);
        return deleted > 0;
    }

    public boolean existsById(Long id) {
        String sql = "SELECT EXISTS(SELECT 1 FROM courses WHERE id = ?)";
        Boolean exists = jdbc.queryForObject(sql, Boolean.class, id);
        return exists != null && exists;
    }
}
