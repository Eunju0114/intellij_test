package com.app;

import java.util.List;

public interface ArticleDao {

    void write(Article article);

    void modify(Article article);

    void delete(int id);

    List<Article> findAll();

    Article findById(int id);


}
