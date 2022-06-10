package com.kemane.gestionstock.service;

import com.kemane.gestionstock.dto.ArticleDto;
import com.kemane.gestionstock.exception.EntityNotFoundException;
import com.kemane.gestionstock.exception.ErrorCodes;
import com.kemane.gestionstock.exception.InvalidEntityException;
import com.kemane.gestionstock.model.Article;
import com.kemane.gestionstock.repository.ArticleRepository;
import com.kemane.gestionstock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public ArticleDto save(ArticleDto articleDto){
        List<String> errors = ArticleValidator.validate(articleDto);
        if (!errors.isEmpty()){
            log.error("Article is not valid", articleDto);
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }

        Article saveArticle = articleRepository.save(ArticleDto.toEntity(articleDto));

        return ArticleDto.fromEntity(saveArticle);
    }

    public ArticleDto findById(Integer id){
        if (id == null){
            log.error("Article ID is null");
            return null;
        }

        Optional<Article> article = articleRepository.findById(id);
        ArticleDto articleDto = ArticleDto.fromEntity(article.get());

        return Optional.of(articleDto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun article avec l'ID "+id+" trouvé", ErrorCodes.ARTICLE_NOT_FOUND
                )
        );
    }

    public ArticleDto findByCodeArticle(String codeArticle){

        if (!StringUtils.hasLength(codeArticle)){
                log.error("Article CODE is null");
                return null;
        }

        Optional<Article> article = articleRepository.findArticleByCodeArticle(codeArticle);
        ArticleDto articleDto = ArticleDto.fromEntity(article.get());

        return Optional.of(articleDto).orElseThrow(() ->
                new EntityNotFoundException(
                        "Aucun article avec le code "+codeArticle+" trouvé", ErrorCodes.ARTICLE_NOT_FOUND
                )
        );

    }

    public List<ArticleDto> findAll(){
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    public void delete(Integer id){
        if (id == null){
            log.error("Article ID is null");
            return;
        }
        articleRepository.deleteById(id);
    }
}
