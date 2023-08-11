package com.movieview360.movieview360.services;

import com.movieview360.movieview360.converters.MovieCastingConverter;
import com.movieview360.movieview360.converters.MovieConverter;
import com.movieview360.movieview360.entities.Casting;
import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.MovieCasting;
import com.movieview360.movieview360.entities.MovieCategory;
import com.movieview360.movieview360.repositories.CastingRepository;
import com.movieview360.movieview360.repositories.MovieCastingRepository;
import com.movieview360.movieview360.repositories.MovieCategoryRepository;
import com.movieview360.movieview360.repositories.MovieRepository;
import com.movieview360.movieview360.request.MovieCastingRequest;
import com.movieview360.movieview360.request.MovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieCategoryRepository movieCategoryRepository;
    @Autowired
    private MovieCategoryService movieCategoryService;
    @Autowired
    private CastingService castingService;
    @Autowired
    private MovieCastingService movieCastingService;
    @Autowired
    private MovieConverter movieConverter;
    @Autowired
    private MovieCastingConverter movieCastingConverter;
    @Autowired
    private CastingRepository castingRepository;
    @Autowired
    private MovieCastingRepository movieCastingRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {

        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            return optionalMovie.get();
        } else {
            throw new NoSuchElementException("Movie with ID " + id + " not found");
        }
    }

    public List<Movie> createMovies(List<MovieRequest> movieRequests) {

        List<Movie> createdMovies = new ArrayList<>();


        for (MovieRequest movieRequest : movieRequests) {
            Movie movie = movieConverter.convertToMovie(movieRequest);
            movie.setTitle(movieRequest.getTitle());
            movie.setDescription(movieRequest.getDescription());
            movie.setReleaseDate(movieRequest.getReleaseDate());
            MovieCategory category = movieCategoryService.getMovieCategoryById(movieRequest.getGenderId());
            movie.setGender(category);
            movie.setImgUrl(movieRequest.getImgUrl());
            //movie.setFavorite(movieRequest.isFavorite());

            Movie savedMovie = movieRepository.save(movie);

            List<MovieCastingRequest> castingRequests = movieRequest.getCastings();
            List<MovieCasting> castings = movieCastingService.createMovieCastings(castingRequests, savedMovie);

            movieCastingRepository.saveAll(castings);

            createdMovies.add(savedMovie);

        }
        return createdMovies;
    }
    public Movie updateMovie(Long id, MovieRequest movieRequest) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);

        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();

            movie.setTitle(movieRequest.getTitle());
            movie.setDescription(movieRequest.getDescription());
            movie.setReleaseDate(movieRequest.getReleaseDate());

            if (movieRequest.getGenderId() != null) {
                Optional<MovieCategory> optionalCategory = movieCategoryRepository.findById(movieRequest.getGenderId());
                if (optionalCategory.isPresent()) {
                    movie.setGender(optionalCategory.get());
                } else {
                    return null;
                }
            }

            movie.setImgUrl(movieRequest.getImgUrl());
            //movieToUpdate.setFavorite(movieRequest.isFavorite());

            return movieRepository.save(movie);
        } else {
            return null;
        }
    }
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> getMoviesByCategoryId(Long categoryId) {
        return movieRepository.findByGenderId(categoryId);
    }

}
