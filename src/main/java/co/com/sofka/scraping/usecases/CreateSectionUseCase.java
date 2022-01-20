package co.com.sofka.scraping.usecases;

import co.com.sofka.scraping.domain.generic.DomainEvent;
import co.com.sofka.scraping.domain.section.Section;
import co.com.sofka.scraping.domain.section.command.CreateSectionCommand;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;

@Dependent
public class CreateSectionUseCase  implements Function<CreateSectionCommand, List<DomainEvent>> {
    @Override
    public List<DomainEvent> apply(CreateSectionCommand command) {
        var section = new Section(command.getSectionId(), command.getName());
        return section.getUncommittedChanges();
    }
}