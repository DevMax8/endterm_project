package devmax.dto;

public class CourseUpdateRequest {
    private String title;
    private Integer credits;
    private String platform;
    private String room;

    public String getTitle() { return title; }
    public Integer getCredits() { return credits; }
    public String getPlatform() { return platform; }
    public String getRoom() { return room; }

    public void setTitle(String title) { this.title = title; }
    public void setCredits(Integer credits) { this.credits = credits; }
    public void setPlatform(String platform) { this.platform = platform; }
    public void setRoom(String room) { this.room = room; }
}
