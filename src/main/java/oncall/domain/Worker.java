package oncall.domain;

public class Worker {
    private Course course;
    private String name;

    public Worker(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }
}
