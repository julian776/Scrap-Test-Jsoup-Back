package co.com.sofka.scraping.domain.section.command;

import co.com.sofka.scraping.domain.generic.Command;

public class AddMovieCommand extends Command {

    private String sectionId;
    private String movieId;
    private String url;
    private String tittle;

    public AddMovieCommand() {
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }


}
