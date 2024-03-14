package com.james.api.article;

import java.sql.SQLException;
import java.util.List;

public class ArticleController {

    private ArticleServiceImpl service;

    public ArticleController() {
        this.service = ArticleServiceImpl.getInstnace();
    }

    public List<?> findAll() throws SQLException {
        return service.findAll();
    }
}
