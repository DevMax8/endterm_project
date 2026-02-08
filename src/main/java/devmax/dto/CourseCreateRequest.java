package devmax.dto;

public class CourseCreateRequest {
    private String name;
    private Integer credits;

    public CourseCreateRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }
}
