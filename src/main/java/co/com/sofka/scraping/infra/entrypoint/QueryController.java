package co.com.sofka.scraping.infra.entrypoint;

import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/api")
public class QueryController {
    private final MongoClient mongoClient;

    public QueryController(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/section/{id}")
    public Response get(@PathParam("id") String sectionId) {
        List<Document> documentList = new ArrayList<>();
        mongoClient.getDatabase("queries")
                .getCollection("section")
                .find(Filters.eq("_id", sectionId))
                .forEach(documentList::add);
        return Response.ok(documentList).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sections")
    public Response getSections(){
        List<String> sections = new ArrayList<>();
        mongoClient.getDatabase("queries")
                .getCollection("section")
                .find().forEach(document -> sections.add(document.get("_id").toString()));
        return Response.ok(sections).build();
    }
}