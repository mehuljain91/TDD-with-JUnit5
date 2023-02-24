package moviestowatch;

import java.time.LocalDate;

/**
 *
 * @author mehul jain
 */
public class Movie implements Comparable<Movie> {
    
    private final String title;
    private final String director;
    private final LocalDate releasedOn;
    
    
    public Movie(String title, String director, LocalDate releasedOn) {
        this.title = title;
        this.director = director;
        this.releasedOn = releasedOn;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public LocalDate getReleasedOn() {
        return releasedOn;
    }

    @Override
    public String toString() {
        return "Movie{" + "title=" + title + ", director=" + director + ", releasedOn=" + releasedOn + '}';
    }
    
    @Override
    public int compareTo(Movie that) {
        return this.title.compareTo(that.title);
    }

}
