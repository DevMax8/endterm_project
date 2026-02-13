package devmax.dto;

public class CourseCreateRequest {
    private String title;
    private String description;

    public CourseCreateRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
