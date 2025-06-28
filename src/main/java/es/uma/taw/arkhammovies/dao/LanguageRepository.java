package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.Keyword;
import es.uma.taw.arkhammovies.entity.Language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

    
}
