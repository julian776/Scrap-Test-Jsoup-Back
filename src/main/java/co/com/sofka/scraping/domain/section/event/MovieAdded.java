package co.com.sofka.scraping.domain.section.event;

import co.com.sofka.scraping.domain.generic.DomainEvent;

public class MovieAdded extends DomainEvent {
    private final String movieId;
    private final String url;
    private final String tittle;
    private final String image;

    public MovieAdded(String movieId, String url, String tittle, String image) {
        super("sofkau.scraping.movieadded");
        this.movieId = movieId;
        this.url = url;
        this.tittle = tittle;
        this.image = image;
    }

    @Override
    public String getMovieId() {
        return movieId;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public String getTittle() {
        return tittle;
    }
}
