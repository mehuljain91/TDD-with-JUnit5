package moviestowatch;

import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.groupingBy;

/**
 *
 * @author mehul jain
 */
class MovieCollect {

    private final List<Movie> movies = new ArrayList<>();

    public List<Movie> movies() {
        return Collections.unmodifiableList(movies);
    }

    public void add(Movie... moviesToAdd) {
        Arrays.stream(moviesToAdd).forEach(movies::add);
    }

    public List<Movie> arrange() {
        return arrange(Comparator.naturalOrder());
    }

    public List<Movie> arrange(Comparator<Movie> criteria) {
        return movies.stream().sorted(criteria).collect(Collectors.toList());
    }

    public Map<Year, List<Movie>> groupByReleasedYear() {
        return groupBy(movie -> Year.of(movie.getReleasedOn().getYear()));
    }
    
    public <K> Map<K, List<Movie>> groupBy(Function<Movie, K> fx) {
        return movies.stream().collect(groupingBy(fx));
    }
    
}
