package de.aaronwagner.demo.exceptions;

public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException() {
        super("Not Authorized");
    }

}
