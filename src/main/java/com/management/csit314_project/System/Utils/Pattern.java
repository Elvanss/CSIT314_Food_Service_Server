package com.management.csit314_project.System.Utils;

public class Pattern {

    public static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    public static final String PASSWORD_PATTERN = "^(?=.{4,}$).*";
    public static final String NO_DIGITS_PATTERN = "^[^0-9]*$";
    public static final String NOT_FOUND = "Not found";

    private Pattern() {}

    public static String notFoundId(String source, Long id) {
        return "Not found " + source + " with id = " + id;
    }
}
