package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.GenreRepository;
import es.uma.taw.arkhammovies.dao.KeywordRepository;
import es.uma.taw.arkhammovies.dao.MovieRepository;
import es.uma.taw.arkhammovies.dao.UserRepository;
import es.uma.taw.arkhammovies.dto.StatDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// ================================================================================
// Enrique Ibáñez: 100%
// ================================================================================

@Service
public class StatService {

    @Autowired MovieRepository movieRepository;
    @Autowired GenreRepository genreRepository;
    @Autowired KeywordRepository keywordRepository;
    @Autowired UserRepository userRepository;

    public List<StatDTO> getSortedMovieBudgets() {
        return StatDTOList(movieRepository.getSortedMovieBudgets(), 0);
    }

    public List<StatDTO> getSortedMovieRevenues() {
        return StatDTOList(movieRepository.getSortedMovieRevenues(), 0);
    }

    public List<StatDTO> getSortedMovieProfits() {
        return StatDTOList(movieRepository.getSortedMovieProfits(), 0);
    }

    public List<StatDTO> getSortedMovieDurations() {
        return StatDTOList(movieRepository.getSortedMovieDurations(), 0);
    }

    public List<StatDTO> getSortedMovieScores() {
        return StatDTOList(movieRepository.getSortedMovieScores(), 1);
    }

    public List<StatDTO> getSortedFavouritedMovies() {
        return StatDTOList(movieRepository.getSortedFavouritedMovies(), 0);
    }

    public List<StatDTO> getSortedMoviePopularities() {
        return StatDTOList(movieRepository.getSortedMoviePopularities(), 0);
    }

    public List<StatDTO> getGenresOrderedByFrequency() {
        List<StatDTO> stats = StatDTOList(genreRepository.getGenresOrderedByFrequency(), 1);
        long movieCount = movieRepository.count();

        for (StatDTO stat : stats) {
            stat.setDValue(stat.getDValue() / movieCount);
        }

        return stats;
    }

    public List<StatDTO> getKeywordsOrderedByFrequency() {
        List<StatDTO> stats = StatDTOList(keywordRepository.getKeywordsOrderedByFrequency(), 1);
        long movieCount = movieRepository.count();

        for (StatDTO stat : stats) {
            stat.setDValue(stat.getDValue() / movieCount);
        }

        return stats;
    }

    public List<StatDTO> getSortedUserReviews() {
        return StatDTOList(userRepository.getSortedUserReviews(), 0);
    }

    public List<StatDTO> getSortedUserLikes() {
        return StatDTOList(userRepository.getSortedUserLikes(), 0);
    }

    private List<StatDTO> StatDTOList(List<Object[]> rows, int type) {
        List<StatDTO> stats = new ArrayList<>();

        for (Object[] row : rows) {
            StatDTO stat = new StatDTO();
            stat.setId((Integer)row[0]);
            stat.setKey((String)row[1]);
            if (type == 0)
                stat.setIValue((Integer)row[2]);
            else {
                Object value = row[2];
                if (value instanceof Long) {
                    stat.setDValue(((Long) value).doubleValue());
                } else {
                    stat.setDValue((Double) value);
                }
            }
            stats.add(stat);
        }

        return stats;
    }

}
