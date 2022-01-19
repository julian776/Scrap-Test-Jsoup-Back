package co.com.sofka.scraping.domain.section;

import co.com.sofka.scraping.domain.generic.EventChange;
import co.com.sofka.scraping.domain.section.event.CourseAdded;
import co.com.sofka.scraping.domain.section.event.SectionCreated;

import java.util.HashMap;
import java.util.Objects;

public class SectionEventChange implements EventChange {
    public SectionEventChange(Section section) {

        listener((SectionCreated event) -> {
            section.name = event.getName();
            section.courses = new HashMap<>();
        });
        listener((CourseAdded event) -> {
            var course = new Course(event.getId(), event.getUrl(), event.getTutor(), event.getPrice(), event.getTittle());
            section.courses.put(event.getId(), course);
        });
    }
}
