package com.management.csit314_project.Exception;

public class ObjNotFoundException extends RuntimeException {

    // Constructor for ObjectNotFoundException
    public ObjNotFoundException(String message) {
        super(message);
    }

    // Constructor for ObjectNotFoundException
    public ObjNotFoundException(String param, String message) {
        super("Error with [" + param + "]: " + message);
    }
}