/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moviestowatch;

import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 *
 * @author abc
 */
public class MoviesParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(final ParameterContext parameterContext,
            final ExtensionContext extensionContext) throws ParameterResolutionException {
        Parameter parameter = parameterContext.getParameter();
        return Objects.equals(parameter.getParameterizedType().getTypeName(), "java.util.Map<java.lang.String, moviestowatch.Movie>");
    }

    @Override
    public Object resolveParameter(final ParameterContext parameterContext,
            final ExtensionContext extensionContext) throws ParameterResolutionException {
        Map<String, Movie> movies = new HashMap<>();
        movies.put("The Revenant", new Movie("The Revenant", "Alejandro González", LocalDate.of(2015, Month.DECEMBER, 16)));
        movies.put("Joker", new Movie("Joker", "Todd Phillips", LocalDate.of(2019, Month.OCTOBER, 4)));
        movies.put("Moonlight", new Movie("Moonlight", "Barry Jenkins", LocalDate.of(2016, Month.SEPTEMBER, 2)));
        movies.put("The Martian", new Movie("The Martian", "Ridley Scott", LocalDate.of(2015, Month.OCTOBER, 2)));
        return movies;
    }
}
