package devmax.utils;

import devmax.model.Course;
import devmax.model.Enrollment;
import devmax.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;

public class RowMappers {

    public static RowMapper<Student> studentRowMapper() {
        return (rs, rowNum) -> new Student(
                rs.getLong("id"),
                rs.getString("full_name"),
                rs.getString("email"),
                rs.getInt("age")
        );
    }

    public static RowMapper<Course> courseRowMapper() {
        return (rs, rowNum) -> new Course(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("description")
        );
    }

    public static RowMapper<Enrollment> enrollmentRowMapper() {
        return (rs, rowNum) -> {
            LocalDateTime enrolledAt = null;
            var ts = rs.getTimestamp("enrolled_at");
            if (ts != null) enrolledAt = ts.toLocalDateTime();

            return new Enrollment(
                    rs.getLong("id"),
                    rs.getLong("student_id"),
                    rs.getLong("course_id"),
                    enrolledAt
            );
        };
    }
}
