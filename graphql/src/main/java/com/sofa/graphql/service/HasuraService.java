package com.sofa.graphql.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofa.generated.*;
import com.sofa.graphql.exception.DuplicateEmailException;
import com.sofa.graphql.exception.GraphQLRequestException;
import com.sofa.graphql.service.model.GraphQLError;
import com.sofa.graphql.service.model.GraphQLResponseWrapper;
import com.sofa.graphql.service.model.Student;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class HasuraService {

    private final OkHttpClient client;
    private final ObjectMapper mapper;

    @Value("${hasura.graphql.endpoint}")
    private String endpoint;

    public HasuraService(OkHttpClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    private String executeGraphQLQuery(String query, Object variables) {
        try {
            String jsonBody;
            if (variables != null) {
                jsonBody = mapper.writeValueAsString(new GraphQLRequestWrapper(query, variables));
            } else {
                jsonBody = mapper.writeValueAsString(Collections.singletonMap("query", query));
            }

            RequestBody body = RequestBody.create(jsonBody, MediaType.get("application/json; charset=utf-8"));
            Request request = new Request.Builder().url(endpoint).post(body).build();

            try (Response response = client.newCall(request).execute()) {
                String responseBody = Objects.requireNonNull(response.body()).string();
                if (!response.isSuccessful()) {
                    System.err.println("HTTP Error: " + response.code() + " - " + response.message());
                }
                return responseBody;
            }
        } catch (Exception e) {
            throw new GraphQLRequestException("Failed to execute GraphQL query", e);
        }
    }

    public List<Student> getAllStudents() {
        GetAllStudents query = new GetAllStudents();
        String responseBody = executeGraphQLQuery(query.getQuery(), null);

        GraphQLResponseWrapper<GetAllStudents.Result> wrapper = deserializeResponse(responseBody, new TypeReference<>() {});
        return wrapper.getData().getStudent().stream()
                .map(StudentMapper::from)
                .collect(Collectors.toList());
    }

    public Student getStudentById(Long id) {
        GetStudentById.Variables vars = new GetStudentById.Variables(id);
        GetStudentById query = new GetStudentById(vars);
        String responseBody = executeGraphQLQuery(query.getQuery(), vars);

        GraphQLResponseWrapper<GetStudentById.Result> wrapper = deserializeResponse(responseBody, new TypeReference<>() {});
        if (wrapper.getData().getStudent_by_pk() == null) {
            throw new GraphQLRequestException("Student with ID " + id + " not found");
        }
        return StudentMapper.fromGetStudent(wrapper.getData().getStudent_by_pk());
    }

    public Student insertNewStudent(String name, String email, int age) {
        InsertNewStudent.Variables vars = new InsertNewStudent.Variables(name, email, age);
        InsertNewStudent mutation = new InsertNewStudent(vars);
        String responseBody = executeGraphQLQuery(mutation.getQuery(), vars);

        GraphQLResponseWrapper<InsertNewStudent.Result> wrapper = deserializeResponse(responseBody, new TypeReference<>() {});
        return StudentMapper.fromInsertStudent(wrapper.getData().getInsert_student_one());
    }

    public Student updateStudent(Long id, String name, String email, Integer age) {
        UpdateStudentById.Variables vars = new UpdateStudentById.Variables(id, name, email, age);
        UpdateStudentById mutation = new UpdateStudentById(vars);
        String responseBody = executeGraphQLQuery(mutation.getQuery(), vars);

        GraphQLResponseWrapper<UpdateStudentById.Result> wrapper = deserializeResponse(responseBody, new TypeReference<>() {});
        if (wrapper.getData().getUpdate_student_by_pk() == null) {
            throw new GraphQLRequestException("Student with ID " + id + " not found");
        }
        return StudentMapper.fromUpdateStudent(wrapper.getData().getUpdate_student_by_pk());
    }

    public Student deleteStudent(Long id) {
        DeleteStudentById.Variables vars = new DeleteStudentById.Variables(id);
        DeleteStudentById mutation = new DeleteStudentById(vars);
        String responseBody = executeGraphQLQuery(mutation.getQuery(), vars);

        GraphQLResponseWrapper<DeleteStudentById.Result> wrapper = deserializeResponse(responseBody, new TypeReference<>() {});
        if (wrapper.getData().getDelete_student_by_pk() == null) {
            throw new GraphQLRequestException("Student with ID " + id + " not found");
        }
        return StudentMapper.fromDeleteStudent(wrapper.getData().getDelete_student_by_pk());
    }

    private <T> GraphQLResponseWrapper<T> deserializeResponse(String responseBody, TypeReference<GraphQLResponseWrapper<T>> typeRef) {
        try {
            GraphQLResponseWrapper<T> wrapper = mapper.readValue(responseBody, typeRef);

            if (wrapper.getErrors() != null && !wrapper.getErrors().isEmpty()) {
                GraphQLError error = wrapper.getErrors().get(0);
                String code = error.getExtensions() != null ? error.getExtensions().get("code").toString() : "";

                if ("constraint-violation".equals(code)) {
                    throw new DuplicateEmailException("Email already exists! Please use a different email.");
                }

                throw new GraphQLRequestException(error.getMessage());
            }

            return wrapper;

        } catch (Exception e) {
            throw new GraphQLRequestException("Failed to parse GraphQL response", e);
        }
    }

    private static class GraphQLRequestWrapper {
        public String query;
        public Object variables;

        public GraphQLRequestWrapper(String query, Object variables) {
            this.query = query;
            this.variables = variables;
        }
    }
}


















//package com.sofa.graphql.service;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sofa.generated.*;
//import com.sofa.graphql.DuplicateEmailException;
//import com.sofa.graphql.service.model.GraphQLError;
//import com.sofa.graphql.service.model.GraphQLResponseWrapper;
//import com.sofa.graphql.service.model.Student;
//import okhttp3.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//@Service
//public class HasuraService {
//
//    private final OkHttpClient client;
//    private final ObjectMapper mapper;
//
//    @Value("${hasura.graphql.endpoint}")
//    private String endpoint;
//
//    public HasuraService(OkHttpClient client, ObjectMapper mapper) {
//        this.client = client;
//        this.mapper = mapper;
//    }
//
//    private String executeGraphQLQuery(String query, Object variables) throws IOException {
//        String jsonBody;
//        if (variables != null) {
//            jsonBody = mapper.writeValueAsString(new GraphQLRequestWrapper(query, variables));
//        } else {
//            jsonBody = mapper.writeValueAsString(Collections.singletonMap("query", query));
//        }
//
//        RequestBody body = RequestBody.create(jsonBody, MediaType.get("application/json; charset=utf-8"));
//        Request request = new Request.Builder().url(endpoint).post(body).build();
//
//        try (Response response = client.newCall(request).execute()) {
//            String responseBody = Objects.requireNonNull(response.body()).string();
//            if (!response.isSuccessful()) {
//                System.err.println("HTTP Error: " + response.code() + " - " + response.message() + "\n" + responseBody);
//            }
//            return responseBody;
//        }
//    }
//
//    public List<Student> getAllStudents() throws IOException {
//        GetAllStudents query = new GetAllStudents();
//        String responseBody = executeGraphQLQuery(query.getQuery(), null);
//
//        GraphQLResponseWrapper<GetAllStudents.Result> wrapper =
//                mapper.readValue(responseBody, new TypeReference<>() {});
//
//        if (wrapper.getErrors() != null && !wrapper.getErrors().isEmpty()) {
//            throw new IOException(wrapper.getErrors().get(0).getMessage());
//        }
//
//        return wrapper.getData().getStudent().stream()
//                .map(StudentMapper::from)
//                .collect(Collectors.toList());
//    }
//
//    public Student getStudentById(Long  id) throws IOException {
//        GetStudentById.Variables vars = new GetStudentById.Variables(id);
//        GetStudentById query = new GetStudentById(vars);
//        String responseBody = executeGraphQLQuery(query.getQuery(), vars);
//
//        GraphQLResponseWrapper<GetStudentById.Result> wrapper =
//                mapper.readValue(responseBody, new TypeReference<>() {});
//
//        if (wrapper.getErrors() != null && !wrapper.getErrors().isEmpty()) {
//            throw new IOException(wrapper.getErrors().get(0).getMessage());
//        }
//
//        return StudentMapper.fromGetStudent(wrapper.getData().getStudent_by_pk());
//    }
//
//    public Student insertNewStudent(String name, String email, int age) throws IOException {
//        InsertNewStudent.Variables vars = new InsertNewStudent.Variables(name, email, age);
//        InsertNewStudent mutation = new InsertNewStudent(vars);
//        String responseBody = executeGraphQLQuery(mutation.getQuery(), vars);
//
//        GraphQLResponseWrapper<InsertNewStudent.Result> wrapper =
//                mapper.readValue(responseBody, new TypeReference<>() {});
//
//        if (wrapper.getErrors() != null && !wrapper.getErrors().isEmpty()) {
//            GraphQLError error = wrapper.getErrors().get(0);
//            String code = error.getExtensions() != null ? error.getExtensions().get("code").toString() : "";
//            if ("constraint-violation".equals(code)) {
//                throw new DuplicateEmailException("Email already exists! Please use a different email.");
//            }
//            throw new IOException(error.getMessage());
//        }
//
//        return StudentMapper.fromInsertStudent(wrapper.getData().getInsert_student_one());
//    }
//
//    public Student updateStudent(Long  id, String name, String email, Integer age) throws IOException {
//        UpdateStudentById.Variables vars = new UpdateStudentById.Variables(id, name, email, age);
//        UpdateStudentById mutation = new UpdateStudentById(vars);
//        String responseBody = executeGraphQLQuery(mutation.getQuery(), vars);
//
//        GraphQLResponseWrapper<UpdateStudentById.Result> wrapper =
//                mapper.readValue(responseBody, new TypeReference<>() {});
//
//        if (wrapper.getErrors() != null && !wrapper.getErrors().isEmpty()) {
//            throw new IOException(wrapper.getErrors().get(0).getMessage());
//        }
//
//        return StudentMapper.fromUpdateStudent(wrapper.getData().getUpdate_student_by_pk());
//    }
//
//    public Student deleteStudent(Long id) throws IOException {
//        DeleteStudentById.Variables vars = new DeleteStudentById.Variables(id); // لا تستخدم Math.toIntExact
//        DeleteStudentById mutation = new DeleteStudentById(vars);
//        String responseBody = executeGraphQLQuery(mutation.getQuery(), vars);
//
//        GraphQLResponseWrapper<DeleteStudentById.Result> wrapper =
//                mapper.readValue(responseBody, new TypeReference<>() {});
//
//        if (wrapper.getErrors() != null && !wrapper.getErrors().isEmpty()) {
//            throw new IOException(wrapper.getErrors().get(0).getMessage());
//        }
//
//        return StudentMapper.fromDeleteStudent(wrapper.getData().getDelete_student_by_pk());
//    }
//
//
//    private static class GraphQLRequestWrapper {
//        public String query;
//        public Object variables;
//
//        public GraphQLRequestWrapper(String query, Object variables) {
//            this.query = query;
//            this.variables = variables;
//        }
//    }
//}
