package com.movieview360.movieview360.services;

import com.movieview360.movieview360.converters.MovieConverter;
import com.movieview360.movieview360.entities.Casting;
import com.movieview360.movieview360.entities.Movie;
import com.movieview360.movieview360.entities.MovieCasting;
import com.movieview360.movieview360.entities.MovieCategory;
import com.movieview360.movieview360.repositories.MovieCategoryRepository;
import com.movieview360.movieview360.repositories.MovieRepository;
import com.movieview360.movieview360.request.MovieCastingRequest;
import com.movieview360.movieview360.request.MovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public MovieRequest getMovieRequestById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found with id: " + id));

        MovieRequest movieRequest = new MovieRequest();
        movieRequest.setTitle(movie.getTitle());
        movieRequest.setDescription(movie.getDescription());
        movieRequest.setReleaseDate(movie.getReleaseDate());
        movieRequest.setGenderId(movie.getGender().getId());
        movieRequest.setImgUrl(movie.getImgUrl());
        movieRequest.setFavorite(movie.isFavorite());

        List<MovieCastingRequest> castingRequests = new ArrayList<>();
        for (MovieCasting casting : movie.getCastings()) {
            MovieCastingRequest castingRequest = new MovieCastingRequest();
            castingRequest.setCastingId(casting.getCasting().getId());
            castingRequest.setRole(casting.getRole());
            castingRequests.add(castingRequest);
        }

        movieRequest.setCastings(castingRequests);

        return movieRequest;
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

            List<MovieCasting> castings = new ArrayList<>();
            for (MovieCastingRequest castRequest : movieRequest.getCastings()) {
                Casting casting2 = castingService.getCastingById(castRequest.getCastingId());
                Casting casting = new Casting();
                casting.setName(casting2.getName());
                casting.setPhotoUrl(casting2.getPhotoUrl());
                casting = castingService.createCasting(casting);

                MovieCasting movieCasting = new MovieCasting();
                movieCasting.setMovie(movie);
                movieCasting.setCasting(casting);
                movieCasting.setRole(castRequest.getRole());

                castings.add(movieCasting);
            }

            movie.setCastings(castings);

            createdMovies.add(movie);
        }

        List<Movie> savedMovies = movieRepository.saveAll(createdMovies);

        List<MovieCastingRequest> castingRequests = movieRequests.stream()
                .flatMap(movieRequest -> movieRequest.getCastings().stream())
                .collect(Collectors.toList());

        return savedMovies;
    }
    public Movie updateMovie(Long id, MovieRequest movieRequest) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isEmpty()) {
            return null;
        }

        Movie movieToUpdate = optionalMovie.get();


        movieToUpdate.setTitle(movieRequest.getTitle());
        movieToUpdate.setDescription(movieRequest.getDescription());
        movieToUpdate.setReleaseDate(movieRequest.getReleaseDate());


        if (movieRequest.getGenderId() != null) {
            Optional<MovieCategory> optionalCategory = movieCategoryRepository.findById(movieRequest.getGenderId());
            if (optionalCategory.isPresent()) {
                movieToUpdate.setGender(optionalCategory.get());
            } else {
                return null;
            }
        }

        movieToUpdate.setImgUrl(movieRequest.getImgUrl());
        //movieToUpdate.setFavorite(movieRequest.isFavorite());

        return movieRepository.save(movieToUpdate);
    }
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public List<Movie> getMoviesByCategoryId(Long categoryId) {
        return movieRepository.findByGenderId(categoryId);
    }
}
