package co.com.sofka.scraping.domain.section.event;

import co.com.sofka.scraping.domain.generic.DomainEvent;

public class CourseAdded extends DomainEvent {
    private final String id;
    private final String url;
    private final String tutor;
    private final String price;
    private final String tittle;

    public CourseAdded(String id, String url, String tutor, String price, String tittle) {
        super("sofkau.scraping.courseadded");
        this.id = id;
        this.url = url;
        this.tutor = tutor;
        this.price = price;
        this.tittle = tittle;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getTutor() {
        return tutor;
    }

    public String getPrice() {
        return price;
    }

    public String getTittle() {
        return tittle;
    }
}
