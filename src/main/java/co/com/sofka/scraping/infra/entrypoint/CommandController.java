package co.com.sofka.scraping.infra.entrypoint;

import co.com.sofka.scraping.domain.section.command.AddMovieCommand;
import co.com.sofka.scraping.domain.section.command.CreateSectionCommand;
import io.vertx.mutiny.core.eventbus.EventBus;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
public class CommandController {

    private final EventBus bus;

    public CommandController(EventBus bus) {
        this.bus = bus;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addMovie")
    public Response executor(AddMovieCommand command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/createSection")
    public Response executor(CreateSectionCommand command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }

}