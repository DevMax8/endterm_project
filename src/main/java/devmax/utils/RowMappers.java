package devmax.utils;

import devmax.model.Course;
import devmax.model.Enrollment;
import devmax.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;

public class RowMappers {

    public static RowMapper<Student> studentRowMapper() {
        return (rs, rowNum) -> new Student(
                rs.getLong("id"),
                rs.getString("full_name"),
                rs.getString("email"),
                (Integer) rs.getObject("age")
        );
    }

    public static RowMapper<Course> courseRowMapper() {
        return (rs, rowNum) -> new Course(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("credits")
        );
    }

    public static RowMapper<Enrollment> enrollmentRowMapper() {
        return (rs, rowNum) -> {
            Timestamp ts = rs.getTimestamp("enrolled_at");
            return new Enrollment(
                    rs.getLong("id"),
                    rs.getLong("student_id"),
                    rs.getLong("course_id"),
                    ts == null ? null : ts.toLocalDateTime()
            );
        };
    }
}
