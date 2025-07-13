package com.sofa.graphql.service.model;

import java.util.List;
import java.util.Map;

public class GraphQLError {
    private String message;
    private List<Location> locations;
    private List<Object> path;
    private Map<String, Object> extensions;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Object> getPath() {
        return path;
    }

    public void setPath(List<Object> path) {
        this.path = path;
    }

    public Map<String, Object> getExtensions() {
        return extensions;
    }

    public void setExtensions(Map<String, Object> extensions) {
        this.extensions = extensions;
    }

    // فئة مساعدة لـ Location
    //  "locations": [{ "line": 2, "column": 5 }],
    public static class Location {
        private int line;
        private int column;

        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }
    }
}