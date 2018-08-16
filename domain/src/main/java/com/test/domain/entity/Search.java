package com.test.domain.entity;

public class Search {

    private String name;
    private String surname;
    private Boolean sortDir;
    private String selectedSortBy;

    public Search(String name, String surname, Boolean sortDir, String selectedSortBy) {
        this.name = name;
        this.surname = surname;
        this.sortDir = sortDir;
        this.selectedSortBy = selectedSortBy;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Boolean getSortDir() {
        return sortDir;
    }

    public String getSelectedSortBy() {
        return selectedSortBy;
    }
}
