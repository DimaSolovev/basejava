package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Organization {

    private final Link homePage;
    private List<Post> postList;

    public Organization( String name, String url, List<Post> postList) {
//        Objects.requireNonNull(startDate,"startDate must not be null");
//        Objects.requireNonNull(endDate,"endDate must not be null");
//        Objects.requireNonNull(title,"title must not be null");
        Objects.requireNonNull(postList,"postList must not be null");
        this.homePage = new Link(name,url);
        this.postList = postList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homePage.equals(that.homePage) && postList.equals(that.postList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, postList);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", postList=" + postList +
                '}';
    }
}
