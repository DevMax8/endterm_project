package devmax.repository;

import devmax.dto.CourseCreateRequest;
import devmax.model.Course;
import devmax.utils.RowMappers;
import org.springframework.dao.EmptyResultDataAccessException;
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
        String sql = "SELECT id, name, credits FROM courses ORDER BY id";
        return jdbc.query(sql, RowMappers.courseRowMapper());
    }

    public Optional<Course> findById(Long id) {
        try {
            String sql = "SELECT id, name, credits FROM courses WHERE id = ?";
            return Optional.ofNullable(jdbc.queryForObject(sql, RowMappers.courseRowMapper(), id));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    public Long create(CourseCreateRequest req) {
        String sql = "INSERT INTO courses(name, credits) VALUES (?, ?) RETURNING id";
        return jdbc.queryForObject(sql, Long.class, req.getName(), req.getCredits());
    }
}
