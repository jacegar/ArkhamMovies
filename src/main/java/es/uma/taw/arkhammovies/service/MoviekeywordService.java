package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.KeywordRepository;
import es.uma.taw.arkhammovies.dao.MovieRepository;
import es.uma.taw.arkhammovies.dao.MoviekeywordRepository;
import es.uma.taw.arkhammovies.dto.MoviekeywordDTO;
import es.uma.taw.arkhammovies.entity.Moviekeyword;
import es.uma.taw.arkhammovies.entity.MoviekeywordId;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// ================================================================================
// Enrique Ibáñez: 100%
// ================================================================================

@Service
public class MoviekeywordService extends DTOService<MoviekeywordDTO, Moviekeyword> {

    @Autowired
    protected MoviekeywordRepository moviekeywordRepository;

    @Autowired
    protected MovieRepository movieRepository;

    @Autowired
    protected KeywordRepository keywordRepository;

    public void saveMovieKeywords(Integer movieId, List<Integer> keywordIds) {
        for (Integer keywordId : keywordIds) {
            MoviekeywordDTO moviekeywordDTO = new MoviekeywordDTO();
            moviekeywordDTO.setMovieId(movieId);
            moviekeywordDTO.setKeywordId(keywordId);
            this.saveMovieKeyword(moviekeywordDTO);
        }
    }

    private void saveMovieKeyword(MoviekeywordDTO moviekeywordDTO) {
        MoviekeywordId moviekeywordId = new MoviekeywordId();

        moviekeywordId.setKeywordId(moviekeywordDTO.getKeywordId());
        moviekeywordId.setMovieId(moviekeywordDTO.getMovieId());

        Moviekeyword moviekeyword = this.moviekeywordRepository.findById(moviekeywordId).orElse(new Moviekeyword());

        moviekeyword.setId(moviekeywordId);
        moviekeyword.setMovie(this.movieRepository.findById(moviekeywordId.getMovieId()).get());
        moviekeyword.setKeyword(this.keywordRepository.findById(moviekeywordId.getKeywordId()).get());

        this.moviekeywordRepository.save(moviekeyword);
    }

    @Transactional
    public void deleteMovieKeywordsByMovieId(Integer movieId) {
        this.moviekeywordRepository.deleteMovieKeywordsByMovieId(movieId);
    }
}
