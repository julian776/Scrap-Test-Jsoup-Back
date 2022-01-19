package co.com.sofka.scraping.domain.section.event;

import co.com.sofka.scraping.domain.generic.DomainEvent;

public class SectionCreated extends DomainEvent {

    private final String name;

    public SectionCreated(String name) {
        super("sofkau.scraping.sectioncreated");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
