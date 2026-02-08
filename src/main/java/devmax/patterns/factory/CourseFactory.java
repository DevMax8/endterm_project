package devmax.patterns.factory;

import devmax.model.Course;
import devmax.model.OfflineCourse;
import devmax.model.OnlineCourse;

public class CourseFactory {
    public static Course create(String type) {
        if (type == null) throw new IllegalArgumentException("type is required");
        return switch (type.toLowerCase()) {
            case "online" -> new OnlineCourse();
            case "offline" -> new OfflineCourse();
            default -> throw new IllegalArgumentException("Unknown course type: " + type);
        };
    }
}
