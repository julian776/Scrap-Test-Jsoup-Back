package co.com.sofka.scraping.infra.handle;

import co.com.sofka.scraping.domain.section.command.AddMovieCommand;
import co.com.sofka.scraping.infra.generic.UseCaseHandle;
import co.com.sofka.scraping.usecases.ExtractMoviesSectionUseCase;
import io.quarkus.vertx.ConsumeEvent;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddMovieUseCaseHandle extends UseCaseHandle {

    private final ExtractMoviesSectionUseCase extractMoviesSectionUseCase;

    public AddMovieUseCaseHandle(ExtractMoviesSectionUseCase extractMoviesSectionUseCase) {
        this.extractMoviesSectionUseCase = extractMoviesSectionUseCase;
    }

    @ConsumeEvent(value = "sofkau.scraping.addmovie")
    void consumeBlocking(AddMovieCommand command) {
        var events = extractMoviesSectionUseCase.apply(command);
        saveProgram(command.getSectionId(), events);
    }
}
