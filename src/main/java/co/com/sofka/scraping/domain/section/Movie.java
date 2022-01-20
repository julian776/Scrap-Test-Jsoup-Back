package co.com.sofka.scraping.domain.section;

import java.util.Objects;

public final class Movie {

    private final String id;
    private String tittle;
    private String image;
    private final String url;

    public Movie(String id, String url, String tittle, String image) {
        this.id = Objects.requireNonNull(id);
        this.url = Objects.requireNonNull(url);
        this.tittle = Objects.requireNonNull(tittle);
        this.image = image;
    }

    public String id() {
        return id;
    }

    public String url() {
        return url;
    }

    public String tittle() {
        return tittle;
    }

    public String image() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(tittle, movie.tittle) && Objects.equals(image, movie.image) && Objects.equals(url, movie.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
