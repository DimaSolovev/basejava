package com.urise.webapp.model;

import java.time.LocalDate;

public class Experience extends AbstractSection{
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String nameOrganization;

    public Experience(LocalDate startDate, LocalDate endDate, String description, String nameOrganization) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.nameOrganization = nameOrganization;
    }
}
