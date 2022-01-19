package co.com.sofka.scraping.domain.section.command;

import co.com.sofka.scraping.domain.generic.Command;

public class CreateSectionCommand extends Command {

    private String sectionId;
    private String name;

    public CreateSectionCommand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }
}
