package com.sofa.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraphQlApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(GraphQlApiApplication.class, args);
        System.out.println(" Spring Boot Is Running");
    }
}











//package com.sofa.graphql;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.module.kotlin.KotlinModule; // استيراد جديد لـ KotlinModule
//
//import com.sofa.generated.GetAllStudents;
//import com.sofa.generated.getallstudents.student;
//import okhttp3.*;
//import okhttp3.logging.HttpLoggingInterceptor;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//import java.util.Objects;
//import java.util.concurrent.TimeUnit;
//
//@SpringBootApplication
//public class GraphQlApiApplication {
//
//    public static class HasuraService {
//
//        private static final String HASURA_GRAPHQL_ENDPOINT = "http://localhost:8081/v1/graphql";
//
//        private static final OkHttpClient httpClient;
//        private static final ObjectMapper objectMapper; // اجعلها غير نهائية وقم بتهيئتها في الـ static block
//
//        static {
//            // تهيئة OkHttpClient
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//            httpClient = new OkHttpClient.Builder()
//                    .addInterceptor(logging)
//                    .connectTimeout(10, TimeUnit.SECONDS)
//                    .writeTimeout(10, TimeUnit.SECONDS)
//                    .readTimeout(30, TimeUnit.SECONDS)
//                    .build();
//
//            // تهيئة ObjectMapper وتسجيل KotlinModule
//            objectMapper = new ObjectMapper();
//            objectMapper.registerModule(new KotlinModule.Builder().build()); // السطر الجديد هنا
//        }
//
//        // ... (باقي الكود لم يتغير) ...
//        public static List<student> getAllStudents() throws IOException {
//            GetAllStudents getAllStudentsRequest = new GetAllStudents();
//            String requestBodyJson = objectMapper.writeValueAsString(Collections.singletonMap("query", getAllStudentsRequest.getQuery()));
//
//            System.out.println("JSON Request Body being sent to Hasura (pre-OkHttp logging): " + requestBodyJson);
//
//            RequestBody body = RequestBody.create(requestBodyJson, MediaType.get("application/json; charset=utf-8"));
//
//            Request request = new Request.Builder()
//                    .url(HASURA_GRAPHQL_ENDPOINT)
//                    .post(body)
//                    .build();
//
//            try (Response response = httpClient.newCall(request).execute()) {
//                if (!response.isSuccessful()) {
//                    System.err.println("فشل الطلب: " + response.code() + " - " + response.message());
//                    String errorResponseBody = Objects.requireNonNull(response.body()).string();
//                    System.err.println("الاستجابة الخطأ: " + errorResponseBody);
//                    throw new IOException("Failed to fetch students: " + response.message() + " - " + errorResponseBody);
//                }
//
//                String responseBody = Objects.requireNonNull(response.body()).string();
//                System.out.println("الاستجابة الخام من Hasura: " + responseBody);
//
//                GraphQLResponseWrapper responseWrapper = objectMapper.readValue(responseBody, GraphQLResponseWrapper.class);
//
//                if (responseWrapper != null && responseWrapper.getData() != null && responseWrapper.getData().getStudent() != null) {
//                    System.out.println("تم جلب الطلاب بنجاح!");
//                    return responseWrapper.getData().getStudent();
//                } else if (responseWrapper != null && responseWrapper.getErrors() != null && !responseWrapper.getErrors().isEmpty()) {
//                    System.err.println("حدثت أخطاء من Hasura:");
//                    for (GraphQLError error : responseWrapper.getErrors()) {
//                        System.err.println(" - " + error.getMessage());
//                    }
//                    return Collections.emptyList();
//                } else {
//                    System.err.println("لم يتم استلام بيانات أو أخطاء صالحة من Hasura.");
//                    return Collections.emptyList();
//                }
//            }
//        }
//
//        public static class GraphQLResponseWrapper {
//            private Data data;
//            private List<GraphQLError> errors;
//
//            public Data getData() {
//                return data;
//            }
//
//            public void setData(Data data) {
//                this.data = data;
//            }
//
//            public List<GraphQLError> getErrors() {
//                return errors;
//            }
//
//            public void setErrors(List<GraphQLError> errors) {
//                this.errors = errors;
//            }
//        }
//
//        public static class Data {
//            @JsonProperty("student")
//            private List<student> student;
//
//            public Data() {} // تأكد من وجود هذا المنشئ
//
//            public List<student> getStudent() {
//                return student;
//            }
//
//            public void setStudent(List<student> student) {
//                this.student = student;
//            }
//        }
//
//        public static class GraphQLError {
//            private String message;
//
//            public String getMessage() {
//                return message;
//            }
//
//            public void setMessage(String message) {
//                this.message = message;
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(GraphQlApiApplication.class, args);
//        System.out.println("تطبيق Spring Boot يعمل الآن. يمكنك استدعاء خدمة Hasura.");
//        try {
//            List<student> students = HasuraService.getAllStudents();
//
//            if (students != null && !students.isEmpty()) {
//                System.out.println("\nقائمة الطلاب (تم جلبها عند بدء التشغيل):");
//                for (student s : students) {
//                    System.out.println("ID: " + s.getId() + ", الاسم: " + s.getName() +
//                            ", البريد الإلكتروني: " + s.getEmail() + ", العمر: " + s.getAge());
//                }
//            } else {
//                System.out.println("لم يتم العثور على طلاب أو حدث خطأ عند بدء التشغيل.");
//            }
//        } catch (IOException e) {
//            System.err.println("خطأ في الاتصال أو معالجة البيانات عند بدء التشغيل: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}