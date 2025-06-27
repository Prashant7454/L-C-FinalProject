package com.server.NewAggrigationServer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "category_keyword")
public class CategoryKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "keyword_id")
    private Integer keywordId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(Integer keywordId) {
        this.keywordId = keywordId;
    }
}
