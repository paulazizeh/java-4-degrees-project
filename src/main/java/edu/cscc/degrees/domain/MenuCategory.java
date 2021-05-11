package edu.cscc.degrees.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MenuCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String categoryTitle;
    private String categoryNotes;
    private Integer sortOrder;

    public MenuCategory(long id, String categoryTitle, String categoryNotes, Integer sortOrder) {
        this.id = id;
        this.categoryTitle = categoryTitle;
        this.categoryNotes = categoryNotes;
        this.sortOrder = sortOrder;
    }

    public MenuCategory() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryNotes() {
        return categoryNotes;
    }

    public void setCategoryNotes(String categoryNotes) {
        this.categoryNotes = categoryNotes;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}

