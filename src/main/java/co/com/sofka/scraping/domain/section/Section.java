package co.com.sofka.scraping.domain.section;

import co.com.sofka.scraping.domain.generic.AggregateRoot;
import co.com.sofka.scraping.domain.generic.DomainEvent;
import co.com.sofka.scraping.domain.section.event.MovieAdded;
import co.com.sofka.scraping.domain.section.event.SectionCreated;

import java.util.List;
import java.util.Set;

public class Section extends AggregateRoot {

    protected Set<Movie> movies;
    protected String name;

    public Section(String sectionId, String name) {
        super(sectionId);
        subscribe(new SectionEventChange(this));
        appendChange(new SectionCreated(name)).apply();
    }

    private Section(String id) {
        super(id);
        subscribe(new SectionEventChange(this));
    }

    public static Section from(String id, List<DomainEvent> events) {
        var section = new Section(id);
        events.forEach(section::applyEvent);
        return section;
    }

    public void addMovie(String id, String url, String tittle, String image) {
        appendChange(new MovieAdded(id, tittle, url, image)).apply();
    }

    public Set<Movie> movies() {
        return movies;
    }

    public String name() {
        return name;
    }
}
