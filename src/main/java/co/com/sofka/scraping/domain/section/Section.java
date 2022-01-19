package co.com.sofka.scraping.domain.section;

import co.com.sofka.scraping.domain.generic.AggregateRoot;
import co.com.sofka.scraping.domain.generic.DomainEvent;
import co.com.sofka.scraping.domain.section.event.CourseAdded;
import co.com.sofka.scraping.domain.section.event.SectionCreated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Section extends AggregateRoot {

    protected Map<String, Course> courses;
    protected String name;

    protected Section(String sectionId, String name) {
        super(sectionId);
        subscribe(new SectionEventChange(this));
        appendChange(new SectionCreated(name)).apply();
    }

    private Section(String id){
        super(id);
        subscribe(new SectionEventChange(this));
    }

    public static Section from(String id, List<DomainEvent> events){
        var section = new Section(id);
        events.forEach(section::applyEvent);
        return section;
    }

    public void addCourse(String id, String url, String tutor, String price, String tittle){
        appendChange(new CourseAdded(id,url,tutor, price, tittle)).apply();
    }

    public Map<String, Course> courses() {
        return courses;
    }

    public String name() {
        return name;
    }
}
