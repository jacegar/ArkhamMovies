package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.GenreRepository;
import es.uma.taw.arkhammovies.dao.MovieRepository;
import es.uma.taw.arkhammovies.dao.UserRepository;

import es.uma.taw.arkhammovies.dto.MovieDTO;
import es.uma.taw.arkhammovies.dto.ReviewDTO;
import es.uma.taw.arkhammovies.dto.UserDTO;
import es.uma.taw.arkhammovies.entity.Genre;
import es.uma.taw.arkhammovies.entity.Movie;
import es.uma.taw.arkhammovies.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Autor: Juan Acevedo García 75%

@Service
public class MovieService extends DTOService<MovieDTO, Movie> {
    @Autowired protected MovieRepository movieRepository;
    @Autowired protected UserRepository userRepository;
    @Autowired protected GenreRepository genreRepository;
    protected ReviewService reviewService;

    public List<MovieDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        return this.entity2DTO(movies);
    }

    public List<MovieDTO> getMoviesById(List<Integer> movieIds) {
        List<Movie> movies = movieRepository.findAllById(movieIds);
        return this.entity2DTO(movies);
     }

     public List<MovieDTO> getMoviesSortedByAverageScore(String title) {
        List<Movie> movies = movieRepository.getMoviesSortedByAverageScore(title);
        return this.entity2DTO(movies);
    }

    public List<MovieDTO> getMoviesSortedByPopularity(String title) {
        List<Movie> movies = movieRepository.getMoviesSortedByPopularity(title);

        return this.entity2DTO(movies);
    }

    public List<MovieDTO> getMoviesSortedByReleaseDate(String title) {
        List<Movie> movies = movieRepository.getMoviesSortedByReleaseDate(title);

        return this.entity2DTO(movies);
    }

    public Double getAverageScore(Integer movieId) {
        List<ReviewDTO> reviews = reviewService.findByMovieId(movieId);

        if (reviews == null || reviews.isEmpty()) {
            return null;
        }

        double sum = 0;
        for (ReviewDTO review : reviews) {
            sum += review.getScore();
        }
        return sum / reviews.size();
    }

    public List<MovieDTO> getRecommendedMovies(UserDTO user, String title) {
        List<Movie> movies;

        List<Genre> likedGenres = genreRepository.getLikedGenresOrderedByFrequency(user.getId());
        if(likedGenres.size() > 3){
            likedGenres = likedGenres.subList(0, 3);
        }

        if(likedGenres.isEmpty()){
            movies = movieRepository.getMoviesByTitle(title);
            movies.removeAll(movieRepository.getLikedMoviesByUser(user.getId()));
            Collections.shuffle(movies);
        }else {
            List<Integer> likedGenresIds = new ArrayList<>();

            likedGenres.forEach((final Genre genre) -> likedGenresIds.add(genre.getId()));

            movies = movieRepository.getRecommendedMoviesByUserAndGenres(user.getId(), likedGenresIds, title);
        }

        return this.entity2DTO(movies);
    }

    public List<MovieDTO> getMoviesWherePersonIsCrew(Integer personId) {
        List<Movie> movies = movieRepository.getMoviesWherePersonIsCrew(personId);

        return this.entity2DTO(movies);
    }

    public List<MovieDTO> getMoviesByTitle(String title) {
        List<Movie> movies = movieRepository.getMoviesByTitle(title);

        return this.entity2DTO(movies);
    }

    public MovieDTO findMovie(Integer id) {
        Movie movie = movieRepository.findById(id).get();

        return this.toDTO(movie);
    }

    public List<MovieDTO> findMoviesById(List<Integer> movieIds) {
        List<Movie> movies = movieRepository.findAllById(movieIds);

        return this.entity2DTO(movies);
    }

    public UserDTO flipLike(Integer movieId, Integer userId) {
        User user = userRepository.findById(userId).get();
        Movie movie = movieRepository.findById(movieId).get();

        if(user.getMoviesLiked() == null) {
            user.setMoviesLiked(new ArrayList<Movie>());
            userRepository.save(user);
        }

        List<Movie> moviesLiked = user.getMoviesLiked();
        if(moviesLiked.contains(movie)) {
            moviesLiked.remove(movie);
            user.setMoviesLiked(moviesLiked);
        }else{
            moviesLiked.add(movie);
            user.setMoviesLiked(moviesLiked);
        }

        userRepository.save(user);
        return user.toDTO();
    }

    public UserDTO flipSave(Integer movieId, Integer userId) {
        User user = userRepository.findById(userId).get();
        Movie movie = movieRepository.findById(movieId).get();

        if(user.getMoviesSaved() == null) {
            user.setMoviesSaved(new ArrayList<Movie>());
            userRepository.save(user);
        }

        List<Movie> moviesSaved = user.getMoviesSaved();
        if(moviesSaved.contains(movie)) {
            moviesSaved.remove(movie);
            user.setMoviesSaved(moviesSaved);
        }else{
            moviesSaved.add(movie);
            user.setMoviesSaved(moviesSaved);
        }

        userRepository.save(user);
        return user.toDTO();
    }

    public void saveMovie(MovieDTO movieDTO) {
        Movie movie = new Movie();

        movie.setId(movieDTO.getId());
        movie.setTitle(movieDTO.getTitle());
        movie.setPopularity(movieDTO.getPopularity());
        movie.setRuntime(movieDTO.getRuntime());
        movie.setBudget(movieDTO.getBudget());
        movie.setRevenue(movieDTO.getRevenue());
        movie.setOverview(movieDTO.getOverview());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setStatus(movieDTO.getStatus());
        movie.setHomepage(movieDTO.getHomepage());
        movie.setTagline(movieDTO.getTagline());
        movie.setPhotoUrl(movieDTO.getPhotoUrl());

        List<Genre> genres = genreRepository.findAllById(movieDTO.getGenres());
        movie.setGenres(genres);

        this.movieRepository.save(movie);
    }

    public void deleteMovieById(Integer id) {
        this.movieRepository.deleteById(id);
    }
}
