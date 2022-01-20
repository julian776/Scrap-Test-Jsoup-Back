package co.com.sofka.scraping.infra.materialize;

import co.com.sofka.scraping.domain.section.event.MovieAdded;
import co.com.sofka.scraping.domain.section.event.SectionCreated;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class SectionHandle {
    private final MongoClient mongoClient;

    public SectionHandle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }


    @ConsumeEvent(value = "sofkau.scraping.sectioncreated", blocking = true)
    void consumeProgramCreated(SectionCreated event) {
        Map<String, Object> document = new HashMap<>();
        document.put("_id", event.getAggregateId());
        document.put("name", event.getName());

        mongoClient.getDatabase("queries")
                .getCollection("section")
                .insertOne(new Document(document));
    }

    @ConsumeEvent(value = "sofkau.scraping.movieadded", blocking = true)
    void consumeMovieAdded(MovieAdded event) {
        BasicDBObject document = new BasicDBObject();

        var key = "movies." + event.getTittle();
        document.put(key+".id", event.getMovieId());
        document.put(key+".name", event.getTittle());
        document.put(key+".url_image", event.getImage());
        document.put(key+".url_video", event.getUrl());

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", document);

        mongoClient.getDatabase("queries")
                .getCollection("section")
                .updateOne( Filters.eq("_id", event.getAggregateId()), updateObject);
    }
    

}