package my.think.proxy.sample.domain.school;

public class Grade {

    private double value;

    public Grade() {
        this(0);
    }

    public Grade(double value) {
        this.value = value;
    }

    public double value() {
        return value;
    }
}
