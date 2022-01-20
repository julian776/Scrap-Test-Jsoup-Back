package co.com.sofka.scraping.infra.handle;

import co.com.sofka.scraping.domain.section.command.CreateSectionCommand;
import co.com.sofka.scraping.infra.generic.UseCaseHandle;
import co.com.sofka.scraping.usecases.CreateSectionUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateSectionUseCaseHandle extends UseCaseHandle {

    private final CreateSectionUseCase createSectionUseCase;

    public CreateSectionUseCaseHandle(CreateSectionUseCase createSectionUseCase) {
        this.createSectionUseCase = createSectionUseCase;
    }

    @ConsumeEvent(value = "sofkau.scraping.createsection")
    void consumeBlocking(CreateSectionCommand command) {
        var events = createSectionUseCase.apply(command);
        saveProgram(command.getSectionId(), events);
    }

}
