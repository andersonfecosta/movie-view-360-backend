package com.movieview360.movieview360.converters;

import com.movieview360.movieview360.entities.*;
import com.movieview360.movieview360.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityResponseConverter {

    private final MovieConverter movieConverter;
    private final MovieCategoryConverter movieCategoryConverter;
    private final MovieCastingConverter movieCastingConverter;
    private final CastingConverter castingConverter;

    private final UserConverter userConverter;

    public EntityResponseConverter(MovieConverter movieConverter, MovieCategoryConverter movieCategoryConverter, MovieCastingConverter movieCastingConverter, CastingConverter castingConverter, UserConverter userConverter) {
        this.movieConverter = movieConverter;
        this.movieCategoryConverter = movieCategoryConverter;
        this.movieCastingConverter = movieCastingConverter;
        this.castingConverter = castingConverter;
        this.userConverter = userConverter;
    }

    public MovieResponse convertToMovieResponse(Movie movie) {
        MovieResponse movieResponse = new MovieResponse();

        movieResponse.setId(movie.getId());
        movieResponse.setTitle(movie.getTitle());
        movieResponse.setDescription(movie.getDescription());
        movieResponse.setReleaseDate(movie.getReleaseDate());

        movieResponse.setImgUrl(movie.getImgUrl());
        movieResponse.setFavorite(movie.isFavorite());

        return movieResponse;
    }

    public MovieCategoryResponse convertToMovieCategoryResponse(MovieCategory movieCategory) {
        MovieCategoryResponse movieCategoryResponse = new MovieCategoryResponse();
        movieCategoryResponse.setId(movieCategory.getId());
        movieCategoryResponse.setDescription(movieCategory.getDescription());

        return movieCategoryResponse;
    }

    public MovieCastingResponse convertToMovieCastingResponse(MovieCasting movieCasting) {
        MovieCastingResponse movieCastingResponse = new MovieCastingResponse();

        movieCastingResponse.setId(movieCasting.getId());

        CastingConverter castingConverter = new CastingConverter();
        movieCastingResponse.setCasting(convertoToCastingResponse(movieCasting.getCasting()));

        movieCastingResponse.setMovie(convertToMovieResponse(movieCasting.getMovie()));

        movieCastingResponse.setRole(movieCasting.getRole());

        return movieCastingResponse;
    }

    public CastingResponse convertoToCastingResponse(Casting casting) {
        CastingResponse castingResponse = new CastingResponse();

        castingResponse.setId(casting.getId());
        castingResponse.setName(casting.getName());
        castingResponse.setPhotoUrl(casting.getPhotoUrl());

        List<MovieCastingResponse> list = new ArrayList<>();
        for (MovieCasting movieCasting : casting.getMovieCastings()) {
            MovieCastingResponse movieCastingResponse = new MovieCastingResponse();
            MovieCastingConverter movieCastingConverter = new MovieCastingConverter();
            movieCastingResponse = convertToMovieCastingResponse(movieCasting);
            list.add(movieCastingResponse);
        }

        castingResponse.setMovieCastingResponses(list);

        return castingResponse;
    }

    public UserResponse convertToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setPassword(user.getPassword());

        List<MovieResponse> list = new ArrayList<>();
        for (Movie movie: user.getFavoriteMovies()) {
            MovieResponse movieResponse = new MovieResponse();
            movieResponse = convertToMovieResponse(movie);

            list.add(movieResponse);
        }

        userResponse.setFavoriteMoviesResponse(list);

        return userResponse;
    }

}
