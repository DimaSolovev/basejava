package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class AchievementQualification extends AbstractSection{
    private List<String> text = new ArrayList<>();

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "AchievementQualification{" +
                "text=" + text +
                '}';
    }
}
