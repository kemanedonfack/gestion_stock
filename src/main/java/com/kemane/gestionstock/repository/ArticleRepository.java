package com.kemane.gestionstock.repository;

import com.kemane.gestionstock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

  @Query(value = "select * from article where codearticle = :code", nativeQuery = true)
  List<Article> findByCustomNativeQuery(@Param("code") String c);

  List<Article> findByCodeArticleIgnoreCaseAndDesignationIgnoreCase(String codeArtcile, String designation);

  Optional<Article> findArticleByCodeArticle(String codeArticle);

  List<Article> findAllByCategoryId(Integer idCategory);

}
