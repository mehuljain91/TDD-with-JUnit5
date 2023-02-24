package moviestowatch;

import java.time.Year;
import java.util.Arrays;
import static java.util.Collections.singletonList;
import java.util.Comparator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

/**
 *
 * @author mehul jain
 */

@DisplayName("movie collection")
public class MovieCollectSpec {

    private MovieCollect collect;
    private Movie theRevenant;
    private Movie joker;
    private Movie moonlight;
    private Movie theMartian;

    @BeforeEach
    void init(Map<String, Movie> movies) {
        collect = new MovieCollect();
        this.theRevenant = movies.get("The Revenant");
        this.joker = movies.get("Joker");
        this.moonlight = movies.get("Moonlight");
        this.theMartian = movies.get("The Martian");
    }

    @Nested
    @DisplayName("is empty")
    class IsEmpty {

        @Test
        @DisplayName("movie collection is empty when no movie is adde to it")
        public void emptyMovieCollectWhenNoMovieAdded() {
            List<Movie> movies = collect.movies();
            assertTrue(movies.isEmpty(), () -> "Movies Collection should be empty.");
        }

        @Test
        @DisplayName("empty collection remain empty when add is called without movies")
        public void emptyMovieCollectWhenAddIsCalledWithoutMovies() throws Exception {
            collect.add();
            List<Movie> movies = collect.movies();
            assertTrue(movies.isEmpty(), () -> "Movies collection should be empty");
        }
    }

    @Nested
    @DisplayName("after adding movies")
    class MoviesAreAdded {

        @Test
        @DisplayName("movie collection contains two movies when two movies added")
        void movieCollectContainsTwoMoviesWhenTwoMoviesAdded() {
            collect.add(theRevenant, joker);
            List<Movie> movies = collect.movies();
            assertEquals(2, movies.size(), () -> "Movies Collection should have two movies.");
        }

        @Test
        @DisplayName("movies collection returns an immutable movies collection to client")
        void moviesReturnedFromMovieCollectIsImmutableForClient() {
            collect.add(theRevenant, joker);
            List<Movie> movies = collect.movies();
            try {
                movies.add(moonlight);
                fail(() -> "Should not be able to add movie to movies");
            } catch (Exception e) {
                assertTrue(e instanceof UnsupportedOperationException, ()
                        -> "Should throw unsupportedoperationexception");
            }
        }
    }

    @Nested
    @DisplayName("is arranged")
    class MoviesArranged {

        @Test
        @DisplayName("movie collection is arranged lexographically by movie title")
        void movieCollectArrangedByMovieTitle() {
            collect.add(joker, theRevenant, moonlight);
            List<Movie> movies = collect.arrange();
            assertEquals(Arrays.asList(joker, moonlight, theRevenant), movies, ()
                    -> "Movies in collection should be arranged lexographically by movie title");
        }

        @Test
        @DisplayName("movies in collection are in insertion order after calling arrange")
        void moviesInCollectionAreInInsertionOrderAfterCallingArrange() {
            collect.add(joker, theRevenant, moonlight);
            collect.arrange();
            List<Movie> movies = collect.movies();
            assertEquals(Arrays.asList(joker, theRevenant, moonlight), movies, () -> "Movies in collection are in insertion order");
        }

        @Test
        @DisplayName("movies inside collection are arranged according to user provided criteria")
        void movieCollectArrangedByUserProvidedCriteria() {
            collect.add(joker, theRevenant, moonlight);
            Comparator<Movie> reversed = Comparator.<Movie>naturalOrder().reversed();
            List<Movie> movies = collect.arrange(reversed);
            assertThat(movies).isSortedAccordingTo(reversed);
        }
    }

    @Nested
    @DisplayName("grouped by")
    class MoviesGroupedBy {

        @Test
        @DisplayName("movies inside collection are grouped by released year")
        void groupMoviesInsideMoviesCollectByReleasedYear() {

            collect.add(theRevenant, joker, moonlight, theMartian);
            Map<Year, List<Movie>> moviesByReleasedYear = collect.groupByReleasedYear();

            assertThat(moviesByReleasedYear).containsKey(Year.of(2015)).containsValues(Arrays.asList(theRevenant, theMartian));

            assertThat(moviesByReleasedYear).containsKey(Year.of(2016)).containsValues(singletonList(moonlight));

            assertThat(moviesByReleasedYear).containsKey(Year.of(2019)).containsValues(singletonList(joker));
        }

        @Test
        @DisplayName("movies inside collection are grouped by user provided criteria(group by director name)")
        void groupMoviesByUserProvidedCriteria() {

            collect.add(theRevenant, joker, moonlight, theMartian);
            Map<String, List<Movie>> moviesByDirector = collect.groupBy(Movie::getDirector);

            assertThat(moviesByDirector).containsKey("Alejandro González").containsValues(singletonList(theRevenant));

            assertThat(moviesByDirector).containsKey("Todd Phillips").containsValues(singletonList(joker));

            assertThat(moviesByDirector).containsKey("Barry Jenkins").containsValues(singletonList(moonlight));
        }
    }

}
