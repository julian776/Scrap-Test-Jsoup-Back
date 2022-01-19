package co.com.sofka.scraping.domain.section;

import java.util.Objects;

public final class Course {

    private final String id;
    private String tittle;
    private final String url;
    private final String tutor;
    private final String price;

    public Course(String id, String url, String tutor, String price, String tittle) {
        this.id = Objects.requireNonNull(id);
        this.url = Objects.requireNonNull(url);
        this.tutor = Objects.requireNonNull(tutor);
        this.price = Objects.requireNonNull(price);
        this.tittle = Objects.requireNonNull(tittle);
    }

    public void updateTittle(String tittle) {
        this.tittle = Objects.requireNonNull(tittle);
    }

    public String id() {
        return id;
    }

    public String url() {
        return url;
    }

    public String tutor() {
        return tutor;
    }

    public String price() {
        return price;
    }

    public String tittle() {
        return tittle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(url, course.url) && Objects.equals(tutor, course.tutor) && Objects.equals(price, course.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
