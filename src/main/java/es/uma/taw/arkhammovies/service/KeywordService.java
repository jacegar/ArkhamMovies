package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.KeywordRepository;
import es.uma.taw.arkhammovies.dto.KeywordDTO;
import es.uma.taw.arkhammovies.entity.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordService extends DTOService<KeywordDTO, Keyword> {

    @Autowired
    protected KeywordRepository keywordRepository;

    public void saveKeyword(KeywordDTO keywordDTO) {
        Keyword keyword = new Keyword();

        keyword.setId(keywordDTO.getId());
        keyword.setName(keywordDTO.getName());

        this.keywordRepository.save(keyword);
    }

    public KeywordDTO findKeywordByName(String name) {
        Keyword keyword = this.keywordRepository.findKeywordByName(name);

        return keyword != null ? this.toDTO(keyword) : null;
    }

    public List<KeywordDTO> findAllKeywords() {
        List<Keyword> keywords = this.keywordRepository.findAll();

        return this.entity2DTO(keywords);
    }

    public void deleteKeywordById(Integer id) { this.keywordRepository.deleteById(id); }
}
