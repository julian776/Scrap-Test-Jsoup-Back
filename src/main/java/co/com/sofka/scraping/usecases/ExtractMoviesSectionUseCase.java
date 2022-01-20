package co.com.sofka.scraping.usecases;

import co.com.sofka.scraping.domain.generic.DomainEvent;
import co.com.sofka.scraping.domain.generic.EventStoreRepository;
import co.com.sofka.scraping.domain.section.Movie;
import co.com.sofka.scraping.domain.section.Section;
import co.com.sofka.scraping.domain.section.command.AddMovieCommand;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.enterprise.context.Dependent;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.logging.Level;

import static org.jboss.resteasy.plugins.providers.jsonb.i18n.LogMessages.LOGGER;

@Dependent
public class ExtractMoviesSectionUseCase implements Function<AddMovieCommand, List<DomainEvent>> {
    private static final String URL_BASE = "https://cuevana3.io";
    private final EventStoreRepository repository;

    public ExtractMoviesSectionUseCase(EventStoreRepository repository){
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AddMovieCommand command) {
        var section = Section.from(command.getSectionId(),
                repository.getEventsBy("section", command.getSectionId())
        );
        try {
            Document doc = Jsoup.connect(command.getUrl()).get();
            for (Element e : doc.select("ul.MovieList.Rows.AX.A06.B04.C03.E20 li")) {
                String tittle = e.select("h2.Title").text();
                String url = getValuableLink(e.selectFirst("a").attributes().get("href"));
                String image = e.select("img[src][data-src]").attr("data-src");

                section.addMovie(UUID.randomUUID().toString(), tittle, url, image);
            }
            return section.getUncommittedChanges();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private String getValuableLink(String link){
        try {
            Document doc = Jsoup.connect(link).get();
            var linkMovie = doc.selectFirst("div.TPlayerCn.BgA").selectFirst("iframe.no-you").attr("data-src");
            return linkMovie;
        } catch (Exception e) {

        }
        return "";
    }

    private Connection.Response getResponse(String path) throws IOException {
        return Jsoup.connect(URL_BASE+"/reports/"+path)
                .userAgent("Mozilla/5.0")
                .timeout(10 * 1000)
                .method(Connection.Method.POST)
                .followRedirects(true)
                .execute();
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}