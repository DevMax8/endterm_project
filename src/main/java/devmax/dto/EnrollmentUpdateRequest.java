package devmax.dto;

public class EnrollmentUpdateRequest {
    private Long studentId;
    private Long courseId;

    public EnrollmentUpdateRequest() {}

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
}
