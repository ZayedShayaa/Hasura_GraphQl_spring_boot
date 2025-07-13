package com.sofa.graphql;

public class GraphQLRequestException extends RuntimeException {
    public GraphQLRequestException(String message) {
        super(message);
    }

    public GraphQLRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}