package co.com.sofka.scraping.domain.section;

import co.com.sofka.scraping.domain.generic.EventChange;
import co.com.sofka.scraping.domain.section.event.MovieAdded;
import co.com.sofka.scraping.domain.section.event.SectionCreated;

import java.util.HashSet;

public class SectionEventChange implements EventChange {
    public SectionEventChange(Section section) {

        listener((SectionCreated event) -> {
            section.name = event.getName();
            section.movies = new HashSet<>();
        });
        listener((MovieAdded event) -> {
            var movie = new Movie(event.getMovieId(), event.getUrl(), event.getTittle(), event.getImage());
            section.movies = section.movies == null ? new HashSet<>() : section.movies;
            section.movies.add(movie);
        });
    }
}
