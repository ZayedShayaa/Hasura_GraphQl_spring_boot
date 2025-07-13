package com.sofa.graphql.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Student(
        @JsonProperty("id") Long  id,
        @JsonProperty("name") String name,
        @JsonProperty("email") String email,
        @JsonProperty("age") Integer age
) {}