package devmax.patterns.builder;

import devmax.model.Enrollment;

public class EnrollmentBuilder {

    private Long studentId;
    private Long courseId;

    public EnrollmentBuilder setStudentId(Long studentId) {
        this.studentId = studentId;
        return this;
    }

    public EnrollmentBuilder setCourseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public Enrollment build() {
        Enrollment e = new Enrollment();
        e.setStudentId(studentId);
        e.setCourseId(courseId);
        return e;
    }
}
