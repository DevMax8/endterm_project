package devmax.dto;

public class StudentUpdateRequest {
    private String fullName;
    private String email;

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
}
