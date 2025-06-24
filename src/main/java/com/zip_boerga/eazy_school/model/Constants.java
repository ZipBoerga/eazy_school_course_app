package com.zip_boerga.eazy_school.model;

public enum Constants {
    ANONYMOUS,
    OPEN,
    CLOSE;

    @Override
    public String toString() {
        String name = this.name();
        return name.substring(0, 1).toUpperCase() +
                name.substring(1).toLowerCase();
    }
}
