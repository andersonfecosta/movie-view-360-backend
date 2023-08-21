package com.movieview360.movieview360.converters;

import com.movieview360.movieview360.entities.*;
import com.movieview360.movieview360.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityResponseConverter {

    @Autowired
    private MovieConverter movieConverter;
    @Autowired
    private MovieCategoryConverter movieCategoryConverter;
    @Autowired
    private MovieCastingConverter movieCastingConverter;
    @Autowired
    private CastingConverter castingConverter;
    @Autowired
    private UserConverter userConverter;

    public MovieResponse convertToMovieResponse(Movie movie) {
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setId(movie.getId());
        movieResponse.setTitle(movie.getTitle());
        movieResponse.setDescription(movie.getDescription());
        movieResponse.setReleaseDate(movie.getReleaseDate());
        movieResponse.setGender(convertToMovieCategorySummaryResponse(movie.getGender()));

        List<MovieCastingResponse> castingResponses = new ArrayList<>();
        for (MovieCasting casting: movie.getCastings()) {
            MovieCastingResponse castingResponse = convertToMovieCastingResponse(casting);
            castingResponses.add(castingResponse);
        }

        movieResponse.setCastings(castingResponses);
        movieResponse.setImgUrl(movie.getImgUrl());
        movieResponse.setFavorite(movie.isFavorite());
        return movieResponse;
    }

    public MovieSummaryResponse convertToMovieSummaryResponse(Movie movie) {
        MovieSummaryResponse movieSummaryResponse = new MovieSummaryResponse();
        movieSummaryResponse.setId(movie.getId());
        movieSummaryResponse.setTitle(movie.getTitle());
        movieSummaryResponse.setImgUrl(movie.getImgUrl());
        return movieSummaryResponse;
    }

    public MovieCategoryResponse convertToMovieCategoryResponse(MovieCategory movieCategory) {
        MovieCategoryResponse movieCategoryResponse = new MovieCategoryResponse();
        movieCategoryResponse.setId(movieCategory.getId());
        movieCategoryResponse.setDescription(movieCategory.getDescription());
        return movieCategoryResponse;
    }

    public MovieCategorySummaryResponse convertToMovieCategorySummaryResponse(MovieCategory movieCategory) {
        MovieCategorySummaryResponse movieCategorySummaryResponse = new MovieCategorySummaryResponse();
        movieCategorySummaryResponse.setId(movieCategory.getId());
        movieCategorySummaryResponse.setDescription(movieCategory.getDescription());
        return movieCategorySummaryResponse;
    }

    public MovieCastingResponse convertToMovieCastingResponse(MovieCasting movieCasting) {
        MovieCastingResponse movieCastingResponse = new MovieCastingResponse();
        movieCastingResponse.setId(movieCasting.getCasting().getId());
        movieCastingResponse.setName(movieCasting.getCasting().getName());
        movieCastingResponse.setPhotoUrl(movieCasting.getCasting().getPhotoUrl());
        movieCastingResponse.setRole(movieCasting.getRole());
        return movieCastingResponse;
    }

    public CastingResponse convertoToCastingResponse(Casting casting) {
        CastingResponse castingResponse = new CastingResponse();
        castingResponse.setId(casting.getId());
        castingResponse.setName(casting.getName());
        castingResponse.setPhotoUrl(casting.getPhotoUrl());
        return castingResponse;
    }

    public UserResponse convertToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());

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
