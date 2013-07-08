package my.think.proxy.sample.domain.school;

public class Teacher {

    private Course course;

    public Teacher() {
        this(new MathClass());
    }

    public Teacher(Course course) {
        this.course = course;
    }

    public Course teach() {
        return course;
    }
}
