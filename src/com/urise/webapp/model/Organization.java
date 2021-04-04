package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Organization extends AbstractSection{

    private List<Experience> experienceList = new ArrayList<>();

    public List<Experience> getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(List<Experience> experienceList) {
        this.experienceList = experienceList;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "experienceList=" + experienceList +
                '}';
    }
}
