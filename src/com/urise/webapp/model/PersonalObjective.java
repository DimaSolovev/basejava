package com.urise.webapp.model;

public class PersonalObjective extends AbstractSection{
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "PersonalObjective{" +
                "text='" + text + '\'' +
                '}';
    }
}
